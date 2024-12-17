package com.trisln.aquaneutron.modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.trisln.aquaneutron.modele.exceptions.NoSuchUserException;
import com.trisln.aquaneutron.vue.TriSLN;

import java.security.SecureRandom;
import java.math.BigInteger;
import java.net.Authenticator;
import java.net.PasswordAuthentication;

// Classes nécessaires pour les mails
import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;

public class Utilisateur {
    private String identifiant;
    private String role;
    private String email;

    public Utilisateur(){
        this.identifiant = "spectateur";
        this.role = "spectateur";
        this.email = "voivenelromain@gmail.com";
    }

    public String getRole(){
        return this.role;
    }

    public String getEmail(){
        return this.email;
    }

    public void connecter(String identifiant, String mdp) throws SQLException, NoSuchUserException{
        if (TriSLN.getBd().verifConnexion(identifiant, mdp)){
            this.identifiant = identifiant;
            this.role = TriSLN.getBd().getRoleUtilisateur(identifiant);
        } else {
            throw new NoSuchUserException();
        }
    }

    public boolean estAdmin(){
        return this.role == "admin";
    }

    public boolean estBenevol(){
        return this.role == "benevol";
    }

    public boolean estSpectateur(){
        return this.role == "spectateur";
    }

    public void changePassword(String newPassword) throws SQLException{
        TriSLN.getBd().changePassword(this.identifiant, newPassword);
    }

    public String genererTokenReinitialisation(String email) {
        // Génère un token sécurisé de 32 caractères
        SecureRandom random = new SecureRandom();
        String token = new BigInteger(130, random).toString(32);

        // Sauvegarde le token et l'email dans la base de données avec une expiration
        // éventuelle
        // Exemple : cette partie dépend de la manière dont tu gères les tokens dans ta
        // base de données.
        // Tu pourrais sauvegarder ce token avec une date d'expiration, associée à
        // l'email dans la table User.

        // Assurons-nous que le token est valide et unique dans ta base de données (code
        // à adapter selon ta BD).
        try {
            // Cette méthode doit être implémentée dans ton modèle Utilisateur pour
            // enregistrer le token
            this.enregistrerToken(email, token);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return token;
    }

    public void enregistrerToken(String email, String token) throws SQLException {
        TriSLN.getBd().enregistrerToken(email, token);
    }

    // TODO modifier les info ainsi que creer une adresse email
    public void envoyerEmailDeReinitialisation(String email, String token) {
        String lienReinitialisation = "http://localhost:8080/reset-password?token=" + token;
        String sujet = "Réinitialisation de votre mot de passe";
        String contenu = "Cliquez sur le lien suivant pour réinitialiser votre mot de passe : " + lienReinitialisation;

        // Propriétés pour l'email
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Identifiants de ton email
        String username = "noreply.trislnaquaneutron@gmail.com@gmail.com";
        String password = "L:Ub3[7viH4^8hU$";

        // Création de la session
        Session session = Session.getInstance(properties);

        try {
            // Création du message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("noreply.trislnaquaneutron@gmail.com@gmail.com"));  // Adresse d'envoi
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));  // Destinataire
            message.setSubject(sujet);  // Sujet
            message.setText(contenu);  // Contenu du message

            // Envoi de l'email
            Transport.send(message);
            System.out.println("Email de réinitialisation envoyé !");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
