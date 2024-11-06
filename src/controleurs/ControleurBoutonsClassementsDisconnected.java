package src.controleurs;

import src.vue.TriSLN;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import src.vue.*;


public class ControleurBoutonsClassementsDisconnected implements EventHandler<ActionEvent>{
    private TriSLN vue;

    @FXML
    private Button btnConnexion;
    @FXML
    private Button btnAccueil;
    @FXML
    private Button btnRetour;

    public ControleurBoutonsClassementsDisconnected(TriSLN vue){
        this.vue=vue;
        this.vue.setBClassements(this.btnConnexion);
    }

    @FXML
    public void handleBtnClassementDisconnectedMouseEntered(MouseEvent event){
        try{
            Button btn=(Button)event.getSource();
            switch(btn.getId()){
                case "btnConnexion":
                    this.vue.changeButtonColor(this.btnConnexion, "#949494", "-fx-background-radius: 15");
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
    public void handleBtnClassementDisconnectedMouseExited(MouseEvent event){
        try{
            Button btn=(Button)event.getSource();
            switch(btn.getId()){
                case "btnConnexion":
                    this.vue.changeButtonColor(this.btnConnexion, "lightgrey", "-fx-background-radius: 15");
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
    public void handle(ActionEvent event){
        try{
            Button btn=(Button) event.getSource();
            switch(btn.getId()){
                case "btnAccueil":
                    this.vue.afficheAccueil();
                    break;
                case "btnRetour":
                    this.vue.afficheAccueil();
                    break;
                case "btnConnexion":
                    this.vue.afficheLogin();
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}