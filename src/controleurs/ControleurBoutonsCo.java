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
    private Button btnDeconnexion;

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
                case "btnParticipants":
                    this.vue.changeButtonColor(this.btnParticipants, "#105c74", null);
                    break;
                case "btnCourses":
                    this.vue.changeButtonColor(this.btnCourses, "#105c74", null);    
                    break;
                case "btnConnexion":
                    this.vue.changeButtonColor(this.btnConnexion, "#949494", "-fx-background-radius: 15");
                    break;
                case "btnDeconnexion":
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
                case "btnParticipants":
                    this.vue.changeButtonColor(this.btnParticipants, "#2596BE", null);
                    break;
                case "btnCourses":
                    this.vue.changeButtonColor(this.btnCourses, "#2596BE", null);    
                    break;
                case "btnConnexion":
                    this.vue.changeButtonColor(this.btnConnexion, "lightgrey", "-fx-background-radius: 15");
                    break;
                case "btnDeconnexion":
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
        try {
            Button btn=(Button) event.getSource();
            switch(btn.getId()){
                case "btnClassements":
                    System.out.println("btnClassements");
                    this.vue.afficheClassements();
                    break;
                case "btnConnexion":
                    System.out.println("Connexion");
                    this.vue.afficheLogin();
                    break;
                case "btnCourses":
                    System.out.println("Les courses");
                    this.vue.afficheCourses();
                    break;
                case "btnDeconnexion":
                    System.out.println("Déconnexion");
                    this.vue.afficheAccueil();
                    break;
                }
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        } 
    }
}