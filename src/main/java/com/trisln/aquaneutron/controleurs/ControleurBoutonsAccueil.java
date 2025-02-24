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

public class ControleurBoutonsAccueil extends ControleurBoutons implements EventHandler<ActionEvent>{
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
    private Button btnDeconnexion;
    @FXML
    private Button btnAccueil;
    @FXML
    private Button btnCompte;

    public ControleurBoutonsAccueil(TriSLN vue){
        super();
        this.setBoutons(vue);
    }

    private void setBoutons(TriSLN vue){
        super.setBCompte(btnCompte);
        super.setBAccueil(btnAccueil);
        super.setBDeconnexion(btnDeconnexion);
        super.setVue(vue);
    }

    @FXML
    public void handleBtnAccueilMouseEntered(MouseEvent event){
        try{
            boolean superButton=false;
            Button changedButton=null;
            String newBtnColor="";
            String otherStyle="";
            Button btn=(Button)event.getSource();
            if(btn.getId().equals("btnAccueil") || btn.getId().equals("btnRetour") || btn.getId().equals("btnCompte") || btn.getId().equals("btnDeconnexion")|| btn.getId().equals("btnConnexion")){
                super.handleBtnsMouseEntered(btn);
            }
            else{
                switch(btn.getId()){
                case "btnClassements":
                changedButton=this.btnClassements;
                newBtnColor="#105c74";
                    break;
                case "btnClassements2":
                changedButton=this.btnClassements2;
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
                newBtnColor="#105c74";
                    break;
                case "btnDeconnexion":
                changedButton=this.btnDeconnexion;
                newBtnColor="#105c74";
                    break;
                case "btnCompte":
                changedButton=this.btnCompte;
                newBtnColor="#105c74";
                    break;
                default:
                    superButton=true;
                    break;
                }
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
        boolean superButton=false;
        Button changedButton=null;
        String newBtnColor="";
        String otherStyle="";
        Button btn=(Button)event.getSource();
        if(btn.getId().equals("btnAccueil") || btn.getId().equals("btnRetour") || btn.getId().equals("btnCompte") || btn.getId().equals("btnDeconnexion")|| btn.getId().equals("btnConnexion")){
            super.handleBtnsMouseExited(btn);
        }
        else{
            switch(btn.getId()){
                case "btnClassements":
                changedButton=this.btnClassements;
                newBtnColor="#2596BE";
                    break;
                case "btnClassements2":
                changedButton=this.btnClassements2;
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
                newBtnColor="#2596BE";
                    break;
                case "btnDeconnexion":
                changedButton=this.btnDeconnexion;
                newBtnColor="#2596BE";
                    break;
                case "btnCompte":
                changedButton=this.btnCompte;
                newBtnColor="#2596BE";
                    break;
                default:
                    superButton=true;
                    break;
                }
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
                default:
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