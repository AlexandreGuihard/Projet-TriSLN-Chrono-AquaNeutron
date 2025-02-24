package com.trisln.aquaneutron.controleurs;

import com.trisln.aquaneutron.bd.BdTriSLN;
import com.trisln.aquaneutron.modele.Exceptions.NoSuchUserException;
import com.trisln.aquaneutron.modele.Utilisateur;
import com.trisln.aquaneutron.vue.TriSLN;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    private Label infoMDPLabel;
    @FXML
    private Button btnValiderNewMDP;

    private String email;

    private String token;
    private int nbEssai = 3;

    private boolean isNewVisible;
    private boolean isConfVisible;

    public ControleurBoutonsPopUpLogin(TriSLN vue) {
        this.vue = vue;
        this.token = "";
        this.email = "";
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
                boolean testfieldnewMDP = fieldNewMDP.isEmpty();
                boolean testfieldconfMDP = fieldConfirmMDP.isEmpty();
                // TODO : Vérifier si le mot de passe est conforme : minimum 8 char, 1 maj, 1 min, 1 caractère special, 1 nombre
                if (!(fieldNewMDP.isEmpty()) || !(fieldConfirmMDP.isEmpty())) {
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
                        this.infoMDPLabel.setText("Le contenu de nouveau mot de passe et de la confirmation sont différents.");
                    }
                } else {
                    this.infoMDPLabel.setText("Le contenu de nouveau mot de passe ou de la confirmation est vide.");
                }
                break;
        }
    }
}
