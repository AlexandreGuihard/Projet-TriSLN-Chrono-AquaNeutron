package com.trisln.aquaneutron.controleurs;

import com.trisln.aquaneutron.bd.BdTriSLN;
import com.trisln.aquaneutron.vue.TriSLN;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class ControleurBoutonsPopUpLogin implements EventHandler<ActionEvent> {
    private TriSLN vue;

    @FXML
    private TextField verifEmailField;
    @FXML
    private Label infoEmailLabel;
    @FXML
    private Button btnValiderEmail;

    public ControleurBoutonsPopUpLogin(TriSLN vue) {
        this.vue = vue;
    }

    @Override
    public void handle(ActionEvent event) {
        Button btn = (Button) event.getSource();
        BdTriSLN bd = TriSLN.getBd();
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

                            // Générer un token pour l'email de réinitialisation
                            String token = "";

                            // Appeler la méthode pour envoyer l'email avec le token
                            this.vue.getUtilisateur().envoyerEmailDeReinitialisation(fieldEmail, token);
                        } else {
                            this.infoEmailLabel.setText("L'email n'existe pas dans la base de données.");
                        }
                    } catch (SQLException e) {
                        this.infoEmailLabel.setText("Un problème est survenu dans la base de données.");
                        e.printStackTrace(); // Loguer l'exception pour le débogage
                    }
                }
                break;
        }
    }
}
