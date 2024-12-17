package src.controleurs;

import javafx.event.EventHandler;

import java.io.IOException;
import java.util.ResourceBundle.Control;

import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;
import javax.swing.plaf.synth.SynthStyle;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import src.vue.TriSLN;

public abstract class ControleurBoutons {
    private TriSLN vue;
    @FXML
    private Button btnAccueil;
    @FXML
    private Button btnRetour;
    @FXML
    private Button btnDeconnexion;
    @FXML
    private Button btnCompte;
    @FXML
    private Button btnConnexion;
    @FXML
    private Button btnAccueilDisconnected;

    public ControleurBoutons(){
        this.vue=null;
        this.btnAccueil=null;
        this.btnRetour=null;
        this.btnDeconnexion=null;
        this.btnCompte=null;
        this.btnConnexion=null;
        this.btnAccueilDisconnected=null;
    }

    public TriSLN getVue(){
        return this.vue;
    }

    public void setVue(TriSLN vue){
        this.vue = vue;
    }

    public Button getBDeconnexion(){
        return this.btnDeconnexion;
    }

    public Button getBAccueilDisconnected(){
        return this.btnAccueilDisconnected;
    }

    public Button getBAccueil(){
        return this.btnAccueil;
    }

    public Button getBRetour(){
        return this.btnRetour;
    }

    public Button getBCompte(){
        return this.btnCompte;
    }

    public Button getBConnexion(){
        return this.btnConnexion;
    }

    public void setBConnexion(Button btnConnexion){
        this.btnConnexion=btnConnexion;
    }

    public void setBDeconnexion(Button btnDeconnexion){
        this.btnDeconnexion=btnDeconnexion;
    }

    public void setBAccueilDisconnected(Button btnAccueilDisconnected){
        this.btnAccueilDisconnected = btnAccueilDisconnected;
    }

    public void setBAccueil(Button btnAccueil){
        this.btnAccueil=btnAccueil;
    }

    public void setBRetour(Button btnRetour){
        this.btnRetour=btnRetour;
    }
    
    public void setBCompte(Button btnCompte){
        this.btnCompte=btnCompte;
    }

    public void handleBtnsMouseEntered(Button button){
        Button changedButton=null;
        String newBtnColor="#949494";
        String otherStyle="-fx-background-radius: 15;";
        switch(button.getId()){
            case "btnCompte":
                changedButton=this.btnCompte;
                break;
            case "btnAccueil":
                changedButton=this.btnAccueil;
                break;
            case "btnAccueilDisconnected":
                changedButton=this.btnAccueilDisconnected;
                break;
            case "btnConnexion":
                changedButton=this.btnConnexion;
                break;
            case "btnDeconnexion":
                changedButton=this.btnDeconnexion;
                break;
            default:
                changedButton=this.btnRetour;
                newBtnColor="";
                otherStyle="";
                break;             
        }
        this.vue.changeButtonColor(changedButton, newBtnColor, otherStyle);
    }

    public void handleBtnsMouseExited(Button button){
        Button changedButton=null;
        String newBtnColor="lightgrey";
        String otherStyle="-fx-background-radius: 15;";
        switch(button.getId()){
            case "btnCompte":
                changedButton=this.btnCompte;
                break;
            case "btnAccueil":
                changedButton=this.btnAccueil;
                break;
            case "btnAccueilDisconnected":
                changedButton=this.btnAccueilDisconnected;
                break;
            case "btnConnexion":
                changedButton=this.btnConnexion;
                break;
            case "btnDeconnexion":
                changedButton=this.btnDeconnexion;
                break;
            default:
                changedButton=this.btnRetour;
                newBtnColor="";
                otherStyle="";
                break;             
        }
        this.vue.changeButtonColor(changedButton, newBtnColor, otherStyle);
    }

    public void handle(Button pressedButton){
        try{
            switch(pressedButton.getId()){
                case "btnCompte":
                    this.vue.afficheMonCompte();
                    break;
                case "btnConnexion":
                    this.vue.afficheLogin();
                    break;    
                case "btnDeconnexion" :
                    this.vue.afficheAccueil();
                    break;
                
                case "btnAccueil":
                    this.vue.afficheAccueilConnecte();
                    break;

                case "btnAccueilDisconnected":
                    this.vue.afficheAccueil();
                    break;

                default:
                    this.vue.afficheRetour(); 
                    break;           
            }
        }catch (IOException e){
            e.printStackTrace();
        } 
    }
}
