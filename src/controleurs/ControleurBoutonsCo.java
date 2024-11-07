package src.controleurs;

import javafx.event.EventHandler;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import src.modele.Exceptions.NoSuchUserException;
import src.vue.TriSLN;

public class ControleurBoutonsCo implements EventHandler<ActionEvent>{
    private TriSLN vue;

    // log accueil et AccConnecter
    @FXML
    private Button btnClassements;
    @FXML
    private Button btnConnexion;
    @FXML
    private Button btnParticipants;
    @FXML
    private Button btnCourses;
    @FXML
    private Button btnConnecter;
    @FXML
    private Button btnDeconnexion;
    @FXML
    private TextField idIdentifiant;
    @FXML
    private TextField idMdp;
    @FXML
    private Label idInfoLabel;

    public ControleurBoutonsCo(TriSLN vue){
        this.vue = vue;
        this.vue.setBClassements(this.btnClassements);
        this.vue.setBConnexion(this.btnConnexion);
        this.vue.setBParticipants(this.btnParticipants);
        this.vue.setBCourses(this.btnCourses);
        this.vue.setBDeconnexion(this.btnDeconnexion);
    }

    @FXML
    public void handleBtnAccueilMouseEntered(MouseEvent event){
        try{
            Button btn=(Button)event.getSource();
            switch(btn.getId()){
                case "btnClassements":
                    this.vue.changeButtonColor(this.btnClassements, "#105c74", null);
                    break;
                case "btnPaticipants":
                    this.vue.changeButtonColor(this.btnParticipants, "#105c74", null);
                    break;
                case "btnCourses":
                    this.vue.changeButtonColor(this.btnCourses, "#105c74", null);    
                    break;
                case "btnConnecter":
                    System.out.println("Entered connection");
                    this.vue.changeButtonColor(this.btnConnecter, "#105c74", null);
                    break;
                case "btnCompte":
                    System.out.println("Entered se connecter");
                    this.vue.changeButtonColor(this.btnConnexion, "#949494", "-fx-background-radius: 15");
                    break;
                case "Deconnecter":
                    this.vue.changeButtonColor(this.btnDeconnexion, "#949494", "-fx-background-radius: 15");
                    break;
            }
            
        }
        catch(Exception e){
            System.err.println("Erreur");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleBtnAccueilMouseExited(MouseEvent event){
        try{
            Button btn=(Button)event.getSource();
            switch(btn.getId()){
                case "btnClassements":
                    this.vue.changeButtonColor(this.btnClassements, "#2596BE", null);
                    break;
                case "btnPaticipants":
                    this.vue.changeButtonColor(this.btnParticipants, "#2596BE", null);
                    break;
                case "btnCourses":
                    this.vue.changeButtonColor(this.btnCourses, "#2596BE", null);    
                    break;
                case "btnConnecter":
                    System.out.println("Exited connection");
                    this.vue.changeButtonColor(this.btnConnecter, "#2596BE", null);
                    break;
                case "btnCompte":
                    System.out.println("Exited se connecter");
                    this.vue.changeButtonColor(this.btnConnexion, "lightgrey", "-fx-background-radius: 15");
                    break;
                case "Deconnecter":
                    this.vue.changeButtonColor(this.btnDeconnexion, "lightgrey", "-fx-background-radius: 15");
                    break;
            }
        }
        catch(Exception e){
            System.err.println("Erreur");
            e.printStackTrace();
        }
    }

    @Override
    public void handle(ActionEvent event){
        Button btn=(Button) event.getSource();
        switch(btn.getId()){
            case "btnClassements":
                try {
                    System.out.println("Clique Classement");
                    this.vue.afficheClassements();
                } catch (IOException e) {
                    e.printStackTrace();
                } 
                break;
            case "btnConnexion":
                try {
                    System.out.println("Clique se connecter");
                    this.vue.afficheLogin();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "btnConnecter":
                System.out.println("Clique connection");
                this.vue.getUtilisateur().getRole();
                try {
                    this.vue.getUtilisateur().connecter(this.idIdentifiant.getText(), this.idMdp.getText());
                    idInfoLabel.setText("Identifié en tant que " + this.idIdentifiant.getText());
                } 
                catch (SQLException e) {
                    e.printStackTrace();
                } 
                catch (NoSuchUserException e){
                    idInfoLabel.setText("Cette utilisateur n'existe pas");
                }
                break;
            default:
                System.out.println("Accueil");
                break;
        }
    }
}