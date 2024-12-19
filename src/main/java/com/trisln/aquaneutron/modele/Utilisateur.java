package com.trisln.aquaneutron.modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import io.github.cdimascio.dotenv.Dotenv;

import com.trisln.aquaneutron.modele.exceptions.NoSuchUserException;
import com.trisln.aquaneutron.vue.TriSLN;

import java.security.SecureRandom;
import java.math.BigInteger;

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

    public String genererTokenReinitialisation() {
        // Génère un token sécurisé de 32 caractères
        SecureRandom random = new SecureRandom();
        String token = new BigInteger(130, random).toString(32);
        System.out.println("Token fait");
        return token;
    }

    public void envoyerEmailDeReinitialisation(String emailTo, String token) {
        Dotenv dotenv = Dotenv.configure().filename("src/main/java/com/trisln/aquaneutron/modele/.env").load();
        String password = dotenv.get("EMAIL_PASSWORD");
        String emailFrom = "noreply.trislnaquaneutron@gmail.com";
        System.out.println("Envoie d'email");
        String sujet = "Réinitialisation de votre mot de passe";
        String contenu = "Entrez le token suivant dans votre application pour modifier votre mot de passe : " + token;

        // Propriétés pour l'email
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailFrom, password);
            }
        });

        System.out.println("Created session");

        // Identifiants de ton email
        Message message = new MimeMessage(session);
        System.out.println("Message made");


        try {
            // Création du message
            message.setFrom(new InternetAddress(emailFrom));  // Adresse d'envoi
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));  // Destinataire
            message.setSubject(sujet);  // Sujet
            message.setText(contenu);  // Contenu du message
            System.out.println("Attribute of message made");

            // Envoi de l'email
            Transport.send(message);
            System.out.println("Email de réinitialisation envoyé !");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
