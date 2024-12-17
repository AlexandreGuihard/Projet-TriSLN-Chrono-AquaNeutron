package com.trisln.aquaneutron.controleurs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import com.trisln.aquaneutron.vue.TriSLN;

import java.io.IOException;


public class ControleurBoutonsCompte implements EventHandler<ActionEvent> {
    private TriSLN vue;

    @FXML
    private Button btnAccueil;
    @FXML
    private Button btnRetour;
    @FXML
    private Button btnDeconnexion;

    public ControleurBoutonsCompte(TriSLN vue){
        this.vue = vue;
    }

    @FXML
    public void handleBtnCompteMouseEntered(MouseEvent event){
        try{
            Button btn=(Button)event.getSource();
            switch(btn.getId()){
                case "btnAccueil":
                    this.vue.changeButtonColor(this.btnAccueil, "#949494", "-fx-background-radius: 15");
                    break;
                case "btnDeconnexion":
                    this.vue.changeButtonColor(this.btnDeconnexion, "#949494", "-fx-background-radius: 15");
                    break;
                case "btnRetour":
                    this.vue.changeButtonColor(this.btnRetour, "lightgrey", "-fx-background-radius: 15");
                    break;
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
            Button btn=(Button)event.getSource();
            switch(btn.getId()){
                case "btnAccueil":
                    this.vue.changeButtonColor(this.btnAccueil, "lightgrey", "-fx-background-radius: 15");
                    break;
                case "btnDeconnexion":
                    this.vue.changeButtonColor(this.btnDeconnexion, "lightgrey", "-fx-background-radius: 15");
                    break;
                case "btnRetour":
                    this.vue.changeButtonColor(this.btnRetour, "white", "-fx-background-radius: 15");
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
                case "btnRetour":
                case "btnAccueil":
                    this.vue.afficheAccueilConnecte();
                    break;
                case "btnDeconnexion":
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