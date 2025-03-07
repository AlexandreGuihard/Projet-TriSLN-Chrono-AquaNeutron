package com.trisln.aquaneutron.modele;

import java.sql.SQLException;
import java.util.Properties;
import io.github.cdimascio.dotenv.Dotenv;
import com.trisln.aquaneutron.modele.Exceptions.NoSuchUserException;
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
    private String nom;
    private String prenom;

    public Utilisateur(){
        this.identifiant = "spectateur";
        this.role = "spectateur";
        this.email = "voivenelromain@gmail.com";
        this.nom="Spectateur";
        this.prenom="Spectateur";
    }

    public Utilisateur(String identifiant, String email, String role, String nom, String prenom){
        this.identifiant=identifiant;
        this.role=role;
        this.email=email;
        this.nom=nom;
        this.prenom=prenom;
    }

    public String getRole(){
        return this.role;
    }

    public String getEmail(){
        return this.email;
    }

    public String getIdentifiant(){
        return identifiant;
    }

    public String getNom(){
        return nom;
    }

    public String getPrenom(){
        return prenom;
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
        return this.role.equals("admin");
    }

    public boolean estBenevol(){
        return this.role.equals("benevol");
    }

    public boolean estSpectateur(){
        return this.role.equals("spectateur");
    }

    public boolean verifierMDPMinuscule(String mdp){
        String minuscules = "abcdefghijklmnopqrstuvwxyz";
        for (char c : minuscules.toCharArray()) {
            if (mdp.contains(String.valueOf(c))) {
                return true;
            }
        }
        return false;
    }

    public boolean verifierMDPMajuscule(String mdp){
        String majuscules = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (char c : majuscules.toCharArray()) {
            if (mdp.contains(String.valueOf(c))) {
                return true;
            }
        }
        return false;
    }

    public boolean verifierMDPSpecial(String mdp){
        String specials = "&é\"'(-è_çà)=~#{[|`\\^@]}°+^¨$£¤%ùµ*,;:!?./§";
        for (char c : specials.toCharArray()) {
            if (mdp.contains(String.valueOf(c))) {
                return true;
            }
        }
        return false;
    }

    public boolean verifierMDPNombre(String mdp){
        String nombres = "0123456789";
        for (char c : nombres.toCharArray()) {
            if (mdp.contains(String.valueOf(c))) {
                return true;
            }
        }
        return false;
    }

    public void changePassword(String email, String newPassword) throws SQLException, NoSuchUserException {
        TriSLN.getBd().changePassword(TriSLN.getBd().getIdentifiantByEmail(email), newPassword);
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

    @Override
    public String toString(){
        return "Identifiant:"+identifiant+" Email:"+email+" Role:"+role+" Prenom/Nom:"+prenom+" "+nom;
    }
}
