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

public class ControleurBoutonsCo extends ControleurBoutons implements EventHandler<ActionEvent>{
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
    @FXML
    private Button btnCompte;

    public ControleurBoutonsCo(TriSLN vue){
        super();
        this.vue = vue;
        this.vue.setBClassements(this.btnClassements);
        this.vue.setBConnexion(this.btnConnexion);
        this.vue.setBParticipants(this.btnParticipants);
        this.vue.setBCourses(this.btnCourses);
        this.vue.setBDeconnexion(this.btnDeconnexion);
        this.setBoutons();
    }

    private void setBoutons(){
        super.setBCompte(btnCompte);
        super.setBDeconnexion(btnDeconnexion);
    }

    @FXML
    public void handleBtnAccueilMouseEntered(MouseEvent event){
        try{
            Button changedButton=null;
            String newBtnColor="";
            String otherStyle="";
            Button btn=(Button)event.getSource();
            switch(btn.getId()){
                case "btnClassements":
                    changedButton=this.btnClassements;
                    newBtnColor="#105c74";
                    break;
                case "btnParticipants":
                    changedButton=this.btnParticipants;
                    newBtnColor="#105c74";
                    break;
                case "btnCourses":
                    changedButton=this.btnCourses;
                    newBtnColor="#105c74"; 
                    break;
                case "btnConnexion":
                    changedButton=this.btnConnexion;
                    newBtnColor="#949494";
                    otherStyle="-fx-background-radius: 15";
                    break;
                case "btnDeconnexion":
                    changedButton=this.btnDeconnexion;
                    newBtnColor="#949494";
                    otherStyle="-fx-background-radius: 15";
                    break;
            }
            this.vue.changeButtonColor(changedButton, newBtnColor, otherStyle);
        }
        catch(Exception e){
            System.err.println("Erreur");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleBtnAccueilMouseExited(MouseEvent event){
        try{
            Button changedButton=null;
            String newBtnColor="";
            String otherStyle="";
            Button btn=(Button)event.getSource();
            switch(btn.getId()){
                case "btnClassements":
                    changedButton=this.btnClassements;
                    newBtnColor="#2596BE";
                    break;
                case "btnParticipants":
                    changedButton=this.btnParticipants;
                    newBtnColor="#2596BE";
                    break;
                case "btnCourses":
                    changedButton=this.btnCourses;
                    newBtnColor="#2596BE"; 
                    break;
                case "btnConnexion":
                    changedButton=this.btnConnexion;
                    newBtnColor="lightgrey";
                    otherStyle="-fx-background-radius: 15";
                    break;
                case "btnDeconnexion":
                    changedButton=this.btnDeconnexion;
                    newBtnColor="lightgrey";
                    otherStyle="-fx-background-radius: 15";
                    break;
            }
            this.vue.changeButtonColor(changedButton, newBtnColor, otherStyle);
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