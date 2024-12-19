package com.trisln.aquaneutron.controleurs;

import java.sql.SQLException;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import com.trisln.aquaneutron.modele.Utilisateur;
import com.trisln.aquaneutron.modele.exceptions.NoSuchUserException;
import com.trisln.aquaneutron.vue.TriSLN;

public class ControleurBoutonsLogin implements EventHandler<ActionEvent>{
    private TriSLN vue;

    @FXML
    private Button btnAccueil;
    @FXML
    private TextField idIdentifiant;
    @FXML
    private PasswordField idMdp;
    @FXML
    private Label idInfoLabel;
    @FXML
    private Button btnConnecter;
    @FXML
    private Button btnForgotPassword;
    @FXML
    private Button btnRetour;

    public ControleurBoutonsLogin(TriSLN vue){
        this.vue = vue;
        this.vue.setBAccueil(this.btnAccueil);
    }

    @FXML
    public void handleBtnLoginMouseEntered(MouseEvent event){
        try {
            Button btn = (Button) event.getSource();
            switch (btn.getId()) {
                case "btnConnecter":
                    this.vue.changeButtonColor(this.btnConnecter, "#105c74", "-fx-background-radius: 15");
                    break;
                case "btnAccueil":
                    this.vue.changeButtonColor(this.btnAccueil, "#949494", "-fx-background-radius: 15");
                    break;
                case "btnRetour":
                    this.vue.changeButtonColor(this.btnRetour, "lightgrey", "-fx-background-radius: 15");
                    break;
            }
        } catch (Exception e) {
            System.err.println("Erreur");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleBtnLoginMouseExited(MouseEvent event){
        try {
            Button btn = (Button) event.getSource();
            switch (btn.getId()) {
                case "btnConnecter":
                    this.vue.changeButtonColor(this.btnConnecter, "#2596BE", "-fx-background-radius: 15");
                    break;
                case "btnAccueil":
                    this.vue.changeButtonColor(this.btnAccueil, "lightgrey", "-fx-background-radius: 15");
                    break;
                case "btnRetour":
                    this.vue.changeButtonColor(this.btnRetour, "white", "-fx-background-radius: 15");
                    break;
            }
        } catch (Exception e) {
            System.err.println("Erreur");
            e.printStackTrace();
        }
    }

    @Override
    public void handle(ActionEvent event) {
        Button btn = (Button) event.getSource();
        switch (btn.getId()) {
            case "btnRetour":
                try {
                    this.vue.afficheAccueil();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "btnAccueil":
                try {
                    this.vue.afficheAccueil();
                }
                catch (IOException e) {
                    e.printStackTrace();
                } 
                break;
            case "btnConnecter":
                try {
                    this.vue.getUtilisateur().connecter(this.idIdentifiant.getText(), this.idMdp.getText());
                    idInfoLabel.setText("Identifié en tant que " + this.vue.getUtilisateur().getRole());
                    this.vue.afficheAccueilConnecte();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                } 
                catch (NoSuchUserException e){
                    idInfoLabel.setText("Cette utilisateur n'existe pas");
                }
                break;
            case "btnForgotPassword":
                System.out.println("Clique mot de passe oublié");
                Utilisateur utilisateur = this.vue.getUtilisateur();
                String email = utilisateur.getEmail();
                //String token = this.vue.getUtilisateur().genererTokenReinitialisation(email);
                String token = "testToken";
                this.vue.getUtilisateur().envoyerEmailDeReinitialisation(email, token);
                idInfoLabel.setText("Un email de réinitialisation a été envoyé.");
                break;
            default:
                break;
        }
    }
    
}
