package com.trisln.aquaneutron.controleurs;

import com.trisln.aquaneutron.bd.BdTriSLN;
import com.trisln.aquaneutron.modele.Exceptions.NoSuchUserException;
import com.trisln.aquaneutron.modele.Utilisateur;
import com.trisln.aquaneutron.vue.TriSLN;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;

public class ControleurBoutonsPopUpLogin implements EventHandler<ActionEvent> {
    private TriSLN vue;

    @FXML
    private TextField verifEmailField;
    @FXML
    private Label infoEmailLabel;
    @FXML
    private Button btnValiderEmail;

    @FXML
    private TextField verifCodeField;
    @FXML
    private Label infoCodeLabel;
    @FXML
    private Button btnValiderCode;

    @FXML
    private PasswordField newMDPField;
    @FXML
    private Button btnViewNewMDP;
    @FXML
    private PasswordField confirmMDPField;
    @FXML
    private Button btnViewConfMDP;
    @FXML
    private ImageView checkmark8char;
    @FXML
    private ImageView checkmarkMaj;
    @FXML
    private ImageView checkmarkMin;
    @FXML
    private ImageView checkmarkSpec;
    @FXML
    private ImageView checkmarkNum;
    @FXML
    private Label infoMDPLabel;
    @FXML
    private Button btnValiderNewMDP;

    private String email;

    private String token;
    private int nbEssai;

    private int requirements;

    private boolean isNewVisible;
    private boolean isConfVisible;

    public ControleurBoutonsPopUpLogin(TriSLN vue) {
        this.vue = vue;
        this.nbEssai = 3;
        this.requirements = 0;
        this.token = "";
        this.email = "";

    }

    public void initialize() {
        // Vérifier la connexion
        try {
            URL url = new URL("https://www.google.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(1500);
            connection.setReadTimeout(1500);
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) throw new IOException();
        } catch (IOException e) {
            this.btnValiderEmail.setDisable(true);
            this.infoEmailLabel.setText("Aucune connexion à internet");
        }

    }

    @FXML
    private void onKeyTyped(KeyEvent event) {
        PasswordField field = (PasswordField) event.getSource();
        if (field.getId().equals("newMDPField")) {
            this.requirements = 0;
            String newMDP = field.getText();
            String urlCheckmarkGood = "/com/trisln/aquaneutron/trislnaquaneutron/img/checkmark_good.png";
            String urlCheckmarkBad = "/com/trisln/aquaneutron/trislnaquaneutron/img/checkmark_bad.png";
            Utilisateur utilisateur = this.vue.getUtilisateur();
            if (newMDP.length() >= 8) {
                System.out.println("taille supérieur ou égal à 8");
                this.requirements += 1;
                this.checkmark8char.setImage(new Image(Objects.requireNonNull(getClass().getResource(
                        urlCheckmarkGood)).toExternalForm()));
            } else {
                System.out.println("taille inférieur à 8");
                this.requirements -= 1;
                this.checkmark8char.setImage(new Image(Objects.requireNonNull(getClass().getResource(
                        urlCheckmarkBad)).toExternalForm()));
            }

            if (utilisateur.verifierMDPMajuscule(newMDP)) {
                System.out.println("a une majuscule");
                this.requirements += 1;
                this.checkmarkMaj.setImage(new Image(Objects.requireNonNull(getClass().getResource(
                        urlCheckmarkGood)).toExternalForm()));
            } else {
                System.out.println("n'a pas de majuscule");
                this.requirements -= 1;
                this.checkmarkMaj.setImage(new Image(Objects.requireNonNull(getClass().getResource(
                        urlCheckmarkBad)).toExternalForm()));
            }

            if (utilisateur.verifierMDPMinuscule(newMDP)) {
                System.out.println("a une minuscule");
                this.requirements += 1;
                this.checkmarkMin.setImage(new Image(Objects.requireNonNull(getClass().getResource(
                        urlCheckmarkGood)).toExternalForm()));
            } else {
                System.out.println("n'a pas de minuscule");
                this.requirements -= 1;
                this.checkmarkMin.setImage(new Image(Objects.requireNonNull(getClass().getResource(
                        urlCheckmarkBad)).toExternalForm()));
            }

            if (utilisateur.verifierMDPSpecial(newMDP)) {
                System.out.println("a un character special");
                this.requirements += 1;
                this.checkmarkSpec.setImage(new Image(Objects.requireNonNull(getClass().getResource(
                        urlCheckmarkGood)).toExternalForm()));
            } else {
                System.out.println("n'a pas de character special");
                this.requirements -= 1;
                this.checkmarkSpec.setImage(new Image(Objects.requireNonNull(getClass().getResource(
                        urlCheckmarkBad)).toExternalForm()));
            }

            if (utilisateur.verifierMDPNombre(newMDP)) {
                System.out.println("a un nombre");
                this.requirements += 1;
                this.checkmarkNum.setImage(new Image(Objects.requireNonNull(getClass().getResource(
                        urlCheckmarkGood)).toExternalForm()));
            } else {
                System.out.println("n'a pas de nombre");
                this.requirements -= 1;
                this.checkmarkNum.setImage(new Image(Objects.requireNonNull(getClass().getResource(
                        urlCheckmarkBad)).toExternalForm()));
            }
        }
    }

    @FXML
    public void onKeyPressed(KeyEvent event) {
        TextInputControl field = (TextInputControl) event.getSource();
        switch (field.getId()) {
            case "verifEmailField":
                if (event.getCode() == KeyCode.ENTER) {
                    btnValiderEmail.fire();
                }
                break;
            case "verifCodeField":
                if (event.getCode() == KeyCode.ENTER) {
                    btnValiderCode.fire();
                }
                break;
        }
    }

    @Override
    public void handle(ActionEvent event) {
        Button btn = (Button) event.getSource();
        BdTriSLN bd = TriSLN.getBd();
        System.out.println(btn.getText());
        switch (btn.getId()) {
            case "btnValiderEmail":
                System.out.println("Clique valider email");
                this.email = verifEmailField.getText();

                // Vérifier si l'email est vide
                if (this.email.isEmpty()) {
                    this.infoEmailLabel.setText("Veuillez entrer un email.");
                }
                // Vérifier si l'email a un format valide
                else if (!this.email.matches("(?:[a-z0-9!#$%&'*+\\=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+\\=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
                    this.infoEmailLabel.setText("Veuillez entrer un email valide.");
                }
                else {
                    try {
                        // Vérifier si l'email existe dans la base de données
                        if (bd.getEmailAdresses().contains(this.email)) {
                            this.infoEmailLabel.setText("Un email vous a été envoyé.");
                            Utilisateur u = this.vue.getUtilisateur();

                            // Générer un token pour l'email de réinitialisation
                            this.token = u.genererTokenReinitialisation();

                            // Appeler la méthode pour envoyer l'email avec le token
                            u.envoyerEmailDeReinitialisation(this.email, token);
                            this.btnValiderEmail.setDisable(true);
                            this.vue.affichePopUpCode(token, this);
                        } else {
                            this.infoEmailLabel.setText("L'email n'existe pas dans la base de données.");
                        }
                    } catch (SQLException e) {
                        this.infoEmailLabel.setText("Un problème est survenu dans la base de données.");
                        e.printStackTrace();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;

            case "btnValiderCode":
                System.out.println("Clique valider code");
                String fieldCode = verifCodeField.getText();
                if (nbEssai != 0) {
                    if (!token.equals(fieldCode)) {
                        this.nbEssai--;
                        this.infoCodeLabel.setText("Mauvais code. Tentatives restantes : " + this.nbEssai);
                        System.out.println(nbEssai);
                    } else if (token.isEmpty()) {
                        this.infoCodeLabel.setText("/!\\Problème : Aucun token. Veuillez recommencer le processus");
                    } else {
                        System.out.println("Code bon");
                        try {
                            this.vue.affichePopUpMDP(this);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else {
                    this.token = null;
                    this.infoCodeLabel.setText("Vous n'avez plus de tentative. Veuillez recommencer le processus.");
                }
                break;

            // page changer mot de passe
            case "btnValiderNewMDP":
                System.out.println("Clique valider mdp");
                String fieldNewMDP = newMDPField.getText();
                String fieldConfirmMDP = confirmMDPField.getText();
                if (this.requirements == 5) {
                    if (fieldNewMDP.equals(fieldConfirmMDP)) {
                        System.out.println("OK");
                        try {
                            this.vue.getUtilisateur().changePassword(this.email, fieldNewMDP);
                            this.infoMDPLabel.setText("Votre mot de passe à été modifié. Vous pouvez quitter cette pop-up.");
                        } catch (SQLException e) {
                            this.infoMDPLabel.setText(
                                    "Une erreur est survenue dans la base de donnée.\n" +
                                    "Votre mot de passe n'a pas pu être modifié.");
                            e.printStackTrace();
                        } catch (NoSuchUserException e) {
                            this.infoMDPLabel.setText(
                                    "Une erreur est survenue et l'utilisateur associé à l'email entré précédemment n'a pas été trouvé."
                            );
                        }
                    } else {
                        this.infoMDPLabel.setText(
                                "Le contenu de nouveau mot de passe et de la confirmation sont différents.");
                    }
                } else {
                    this.infoMDPLabel.setText(
                            "Le contenu de nouveau mot de passe n'est pas conforme au recommendations listées ci dessus.");
                }
                break;
        }
    }
}