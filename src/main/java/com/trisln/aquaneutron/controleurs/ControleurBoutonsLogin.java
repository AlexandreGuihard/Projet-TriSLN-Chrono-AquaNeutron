package com.trisln.aquaneutron.controleurs;

import java.io.IOException;
import java.sql.SQLException;

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
                    System.out.println("Entered connection");
                    this.vue.changeButtonColor(this.btnConnecter, "#105c74", null);
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
                    System.out.println("Exited connection");
                    this.vue.changeButtonColor(this.btnConnecter, "#2596BE", null);
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
            case "btnAccueil":
                System.out.println("Clique accueil");
                try {
                    this.vue.afficheAccueil();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "btnConnecter":
                System.out.println("Clique connection");
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
                try {
                    this.vue.affichePopUpLogin();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
    
}
