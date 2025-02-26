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
import javafx.scene.text.Text;
import javafx.application.Platform;


public class ControleurBoutonsCompte extends ControleurBoutons implements EventHandler<ActionEvent> {
    @FXML
    private Button btnAccueil;
    @FXML
    private Button btnRetour;
    @FXML
    private Button btnDeconnexion;
    @FXML
    private Text textRole;
    @FXML
    private Text textNom;
    @FXML
    private Text textPrenom;
    @FXML
    private Text textEmail;
    @FXML
    private Text textIdentifiant;


    public ControleurBoutonsCompte(TriSLN vue){
        super();
        this.setBoutons(vue);
        Platform.runLater(()->textIdentifiant.setText(vue.getUtilisateur().getIdentifiant()));
        Platform.runLater(()->textRole.setText(vue.getUtilisateur().getRole()));
        Platform.runLater(()->textNom.setText(vue.getUtilisateur().getNom()));
        Platform.runLater(()->textPrenom.setText(vue.getUtilisateur().getPrenom()));
        Platform.runLater(()->textEmail.setText(vue.getUtilisateur().getEmail()));
    }

    private void setBoutons(TriSLN vue){
        super.setVue(vue);
        super.setBRetour(btnRetour);
        super.setBDeconnexion(btnDeconnexion);
        super.setBAccueil(btnAccueil);
    }

    @FXML
    public void handleBtnCompteMouseEntered(MouseEvent event){
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
                super.handleBtnsMouseExited(btn);
            }

        }
        catch(Exception e){
            System.err.println("Erreur");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleBtnCompteMouseExited(MouseEvent event){
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
            boolean superButton=false;
            Button changedButton=null;
            String newBtnColor="";
            String otherStyle="";
            Button btn=(Button)event.getSource();
            System.out.println(btn.getId());
            if(btn.getId().equals("btnAccueil") || btn.getId().equals("btnRetour") || btn.getId().equals("btnCompte") || btn.getId().equals("btnDeconnexion")|| btn.getId().equals("btnConnexion")){
                super.handle(btn);
            }
            else{
                System.out.println("Btn PP");
            }
            

        } 
        catch (Exception e){
            e.printStackTrace();
        } 
    }
}