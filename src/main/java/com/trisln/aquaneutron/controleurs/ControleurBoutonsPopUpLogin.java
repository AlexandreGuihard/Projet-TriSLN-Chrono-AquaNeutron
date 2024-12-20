package com.trisln.aquaneutron.controleurs;

import com.trisln.aquaneutron.bd.BdTriSLN;
import com.trisln.aquaneutron.modele.Utilisateur;
import com.trisln.aquaneutron.vue.TriSLN;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

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
    private TextField newMDPField;
    @FXML
    private Button btnViewNewMDP;
    @FXML
    private TextField confirmMDPField;
    @FXML
    private Button btnViewConfMDP;
    @FXML
    private Label infoMDPLabel;
    @FXML
    private Button btnValiderNewMDP;

    private String token;
    private int nbEssai = 3;

    private boolean isNewVisible;
    private boolean isConfVisible;

    public ControleurBoutonsPopUpLogin(TriSLN vue) {
        this.vue = vue;
        this.token = null;
    }

    @Override
    public void handle(ActionEvent event) {
        Button btn = (Button) event.getSource();
        BdTriSLN bd = TriSLN.getBd();
        System.out.println(btn.getText());
        switch (btn.getId()) {
            case "btnValiderEmail":
                System.out.println("Clique valider email");
                String fieldEmail = verifEmailField.getText();

                // Vérifier si l'email est vide
                if (fieldEmail.isEmpty()) {
                    this.infoEmailLabel.setText("Veuillez entrer un email.");
                }
                // Vérifier si l'email a un format valide
                else if (!fieldEmail.matches("(?:[a-z0-9!#$%&'*+\\=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+\\=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
                    this.infoEmailLabel.setText("Veuillez entrer un email valide.");
                }
                else {
                    try {
                        // Vérifier si l'email existe dans la base de données
                        if (bd.getEmailAdresses().contains(fieldEmail)) {
                            this.infoEmailLabel.setText("Un email vous a été envoyé.");
                            Utilisateur u = this.vue.getUtilisateur();

                            // Générer un token pour l'email de réinitialisation
                            this.token = u.genererTokenReinitialisation();

                            // Appeler la méthode pour envoyer l'email avec le token
                            u.envoyerEmailDeReinitialisation(fieldEmail, token);
                            this.btnValiderEmail.setDisable(true);
                            this.vue.affichePopUpCode(token, this);
                        } else {
                            this.infoEmailLabel.setText("L'email n'existe pas dans la base de données.");
                        }
                    } catch (SQLException e) {
                        this.infoEmailLabel.setText("Un problème est survenu dans la base de données.");
                        e.printStackTrace(); // Loguer l'exception pour le débogage
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
                    } else if (token == null || token.isEmpty()) {
                        this.infoCodeLabel.setText("/!\\Problème : Aucun token. Veuillez recommencer le processus");
                    } else {
                        System.out.println("Code bon");
                    }
                } else {
                    this.token = null;
                    this.infoCodeLabel.setText("Vous n'avez plus de tentative. Veuillez recommencer le processus.");
                }

            case "btnValiderNewMDP":
                System.out.println("Clique valider mdp");
                String fieldNewMDP = newMDPField.getText();
                String fieldConfirmMDP = confirmMDPField.getText();
                if (fieldNewMDP.isEmpty() || fieldConfirmMDP.isEmpty()) {
                    if (fieldNewMDP.equals(fieldConfirmMDP)) {
                        System.out.println("OK");
                    }
                }
        }
    }
}
