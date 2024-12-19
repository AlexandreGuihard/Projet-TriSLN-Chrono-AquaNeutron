package src.controleurs;

import javafx.event.EventHandler;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import src.vue.TriSLN;

public class ControleurBoutonsAccueil implements EventHandler<ActionEvent>{
    private TriSLN vue;

    // log accueil et AccConnecter
    @FXML
    private Button btnClassements;
    @FXML
    private Button btnClassements2;
    @FXML
    private Button btnConnexion;
    @FXML
    private Button btnParticipants;
    @FXML
    private Button btnCourses;
    @FXML
    private Button deconnecter;
    @FXML
    private Button btnAccueil;
    @FXML
    private Button btnCompte;

    public ControleurBoutonsAccueil(TriSLN vue){
        this.vue = vue;
        this.vue.setBClassements(this.btnClassements);
        this.vue.setBClassements(this.btnClassements2);
        this.vue.setBConnexion(this.btnConnexion);
        this.vue.setBParticipants(this.btnParticipants);
        this.vue.setBCourses(this.btnCourses);
        this.vue.setBDeconnexion(this.deconnecter);
        this.vue.setBAccueil(this.btnAccueil);
    }

    @FXML
    public void handleBtnAccueilMouseEntered(MouseEvent event){
        try{
            Button btn=(Button)event.getSource();
            switch(btn.getId()){
                case "btnClassements":
                    this.vue.changeButtonColor(this.btnClassements, "#105c74", "");
                    break;
                case "btnClassements2":
                    this.vue.changeButtonColor(this.btnClassements2, "#105c74", "");
                    break;
                case "btnParticipants":
                    this.vue.changeButtonColor(this.btnParticipants, "#105c74", "");
                    break;
                case "btnCourses":
                    this.vue.changeButtonColor(this.btnCourses, "#105c74", "");    
                    break;
                case "btnConnexion":
                    this.vue.changeButtonColor(this.btnConnexion, "#949494", "-fx-background-radius: 15");
                    break;
                case "deconnecter":
                    this.vue.changeButtonColor(this.deconnecter, "#949494", "-fx-background-radius: 15");
                    break;
                case "btnCompte":
                    this.vue.changeButtonColor(this.btnCompte, "#949494", "-fx-background-radius: 15");
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
                    this.vue.changeButtonColor(this.btnClassements, "#2596BE", "");
                    break;
                case "btnClassements2":
                    this.vue.changeButtonColor(this.btnClassements2, "#2596BE", "");
                    break;
                case "btnParticipants":
                    this.vue.changeButtonColor(this.btnParticipants, "#2596BE", "");
                    break;
                case "btnCourses":
                    this.vue.changeButtonColor(this.btnCourses, "#2596BE", "");    
                    break;
                case "btnConnexion":
                    this.vue.changeButtonColor(this.btnConnexion, "lightgrey", "-fx-background-radius: 15");
                    break;
                case "deconnecter":
                    this.vue.changeButtonColor(this.deconnecter, "lightgrey", "-fx-background-radius: 15");
                    break;
                case "btnCompte":
                    this.vue.changeButtonColor(this.btnCompte, "lightgrey", "-fx-background-radius: 15");
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
        try {
            Button btn=(Button) event.getSource();
            switch(btn.getId()){
                case "btnClassements":
                    this.vue.afficheClassementsDisconnected();
                    break;
                case "btnConnexion":
                    this.vue.afficheLogin();
                    break;
                case "btnClassements2":
                    this.vue.afficheClassements();
                    break;
                case "btnParticipants":
                    this.vue.afficheParticipants();
                    break;
                case "btnCourses":
                    this.vue.afficheCourses();
                    break;
                case "deconnecter":
                    this.vue.afficheAccueil();
                    break;
                case "btnCompte":
                    this.vue.afficheMonCompte();
                    break;
            }
            
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        } 
    }
}