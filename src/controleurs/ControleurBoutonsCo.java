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

    public ControleurBoutonsCo(TriSLN vue){
        super();
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
            super.getVue().changeButtonColor(changedButton, newBtnColor, otherStyle);
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
            super.getVue().changeButtonColor(changedButton, newBtnColor, otherStyle);
        }
        catch(Exception e){
            System.err.println("Erreur");
            e.printStackTrace();
        }
    }

    @Override
    public void handle(ActionEvent event){
        Button btn=(Button) event.getSource();
        switch(btn.getText()){
            case "Classements":
                try {
                    this.vue.afficheClassements();
                } 
                catch (IOException e)
                {
                    e.printStackTrace();
                } 
                break;
            case "Les participants":
                super.getVue().afficheParticipants();
                break;
            default:
                break;
        }
    }
}