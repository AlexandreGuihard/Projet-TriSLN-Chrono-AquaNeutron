package com.trisln.aquaneutron.controleurs;

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
import com.trisln.aquaneutron.vue.TriSLN;

public class ControleurBoutonsCo extends ControleurBoutons implements EventHandler<ActionEvent>{
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
    @FXML
    private Button btnClassementsDeco;

    public ControleurBoutonsCo(TriSLN vue){
        super();
        this.setBoutons(vue);
    }

    private void setBoutons(TriSLN vue){
        super.setVue(vue);
        super.setBCompte(btnCompte);
        super.setBDeconnexion(btnDeconnexion);
    }

    @FXML
    public void handleBtnAccueilMouseEntered(MouseEvent event){
        try{
            boolean otherButton=false;
            Button changedButton=null;
            String newBtnColor="";
            String otherStyle="";
            Button btn=(Button)event.getSource();
            switch(btn.getId()){
                case "btnClassements":
                    changedButton=this.btnClassements;
                    newBtnColor="#105c74";
                    break;
                case "btnClassementsDeco":
                    changedButton=this.btnClassementsDeco;
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
                default:
                    otherButton=true;    
                    break;
            }
            if(otherButton){
                super.handleBtnsMouseEntered(btn);
            }
            else{
                super.getVue().changeButtonColor(changedButton, newBtnColor, otherStyle);
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
            boolean otherButton=false;
            Button changedButton=null;
            String newBtnColor="";
            String otherStyle="";
            Button btn=(Button)event.getSource();
            switch(btn.getId()){
                case "btnClassements":
                    changedButton=this.btnClassements;
                    newBtnColor="#2596BE";
                    break;
                case "btnClassementsDeco":
                    changedButton=this.btnClassementsDeco;
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
                default:
                    otherButton=true;
                    break;
            }
            if(otherButton){
                super.handleBtnsMouseExited(btn);
            }
            else{
                super.getVue().changeButtonColor(changedButton, newBtnColor, otherStyle);
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
                    super.getVue().afficheClassements();
                    break;  
                case "btnClassementsDeco":
                    System.out.println("btnClassementsDeco");
                    super.getVue().afficheClassementsDisconnected();
                    break;
                case "btnParticipants":
                    super.getVue().afficheParticipants();
                    break;
                case "btnCourses":
                    super.getVue().afficheCourses();
                    break;
                case "btnConnexion":
                    System.out.println("Clique se connecter");
                    super.getVue().afficheLogin();
                    break;
                default:
                    super.handle(btn);
                    break;
            }
                 
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        } 
    }
}