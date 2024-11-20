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

public class ControleurBoutonsLogin implements EventHandler<ActionEvent>{
    private TriSLN vue;

    @FXML
    private Button btnConnecter;
    @FXML
    private Button btnAccueil;
    @FXML
    private Button btnRetour;

    public ControleurBoutonsLogin(TriSLN vue){
        this.vue = vue;
        this.vue.setBConnexion(this.btnConnecter);
        this.vue.setBDeconnexion(this.btnAccueil);
        this.vue.setBDeconnexion(this.btnRetour);
    }

    @FXML
    public void handleBtnLoginMouseEntered(MouseEvent event){
        try{
            Button btn=(Button)event.getSource();
            switch(btn.getId()){
                case "btnConnecter":
                    this.vue.changeButtonColor(this.btnConnecter, "#105c74", "-fx-background-radius: 15");
                    break;
                case "btnAccueil":
                    this.vue.changeButtonColor(this.btnAccueil, "#949494", "-fx-background-radius: 15");
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
    public void handleBtnLoginMouseExited(MouseEvent event){
        try{
            Button btn=(Button)event.getSource();
            switch(btn.getId()){
                case "btnConnecter":
                    this.vue.changeButtonColor(this.btnConnecter, "#2596BE", "-fx-background-radius: 15");
                    break;
                case "btnAccueil":
                    this.vue.changeButtonColor(this.btnAccueil, "lightgrey", "-fx-background-radius: 15");
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
    public void handle(ActionEvent event) {
        try{
            Button btn=(Button) event.getSource();
            switch(btn.getId()){
                case "btnConnecter":
                    this.vue.afficheAccueilConnecte();
                    break;
                case "btnRetour":
                    this.vue.afficheAccueil();
                    break;
                case "btnAccueil":
                    this.vue.afficheAccueil();
                    break;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}