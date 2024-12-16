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


public class ControleurBoutonsClassements extends ControleurBoutons implements EventHandler<ActionEvent>{
    private TriSLN vue;

    @FXML
    private Button btnConnexion;
    @FXML
    private Button btnAccueil;
    @FXML
    private Button btnRetour;
    @FXML
    private Button btnAccueilDisconnected;
    @FXML
    private Button btnDeconnexion;
    @FXML
    private Button btnCompte;

    public ControleurBoutonsClassements(TriSLN vue){
        super();
        this.setBoutons(vue);
    }

    private void setBoutons(TriSLN vue){
        super.setVue(vue);
        super.setBAccueil(btnAccueil);
        super.setBDeconnexion(btnDeconnexion);
        super.setBCompte(btnCompte);
        super.setBRetour(btnRetour);
    }

    @FXML
    public void handleBtnClassementMouseEntered(MouseEvent event){
        try{
            Button btn=(Button)event.getSource();
            if(btn.getId().equals("btnAccueilDisconnected")){
                super.getVue().changeButtonColor(btn, "#949494", "-fx-background-radius: 15");
            }
            else{
                super.handleBtnsMouseEntered(btn);
            }
            //switch(btn.getId()){
            //    case "btnConnexion":
            //        this.vue.changeButtonColor(this.btnConnexion, "#949494", "-fx-background-radius: 15");
            //        break;
            //    case "btnAccueil":
            //        this.vue.changeButtonColor(this.btnAccueil, "#949494", "-fx-background-radius: 15");
            //        break;
            //    case "btnRetour":
            //        this.vue.changeButtonColor(this.btnRetour, "lightgrey", "-fx-background-radius: 15");
            //        break;
            //    case "btnAccueil2":
            //        this.vue.changeButtonColor(this.btnAccueilDisconnected, "#949494", "-fx-background-radius: 15");
            //        break;
            //    case "btnRetour2":
            //        this.vue.changeButtonColor(this.btnRetour2, "lightgrey", "-fx-background-radius: 15");
            //        break;
            //    case "deconnecter":
            //        this.vue.changeButtonColor(this.deconnecter, "#949494", "-fx-background-radius: 15");
            //        break;
            //    case "btnCompte":
            //        this.vue.changeButtonColor(this.btnCompte, "#949494", "-fx-background-radius: 15");
            //        break;
            //}
            
        }
        catch(Exception e){
            System.err.println("Erreur");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleBtnClassementMouseExited(MouseEvent event){
        try{
            Button btn=(Button)event.getSource();
            if(btn.getId().equals("btnAccueilDisconnected")){
                super.getVue().changeButtonColor(this.btnAccueilDisconnected, "lightgrey", "-fx-background-radius: 15");
            }
            else{
                super.handleBtnsMouseExited(btn);
            }
            //switch(btn.getId()){
            //    case "btnConnexion":
            //        this.vue.changeButtonColor(this.btnConnexion, "lightgrey", "-fx-background-radius: 15");
            //        break;
            //    case "btnAccueil":
            //        this.vue.changeButtonColor(this.btnAccueil, "lightgrey", "-fx-background-radius: 15");
            //        break;
            //    case "btnRetour":
            //        this.vue.changeButtonColor(this.btnRetour, "white", "-fx-background-radius: 15");
            //        break;
            //    case "btnAccueil2":
            //        this.vue.changeButtonColor(this.btnAccueil2, "lightgrey", "-fx-background-radius: 15");
            //        break;
            //    case "btnRetour2":
            //        this.vue.changeButtonColor(this.btnRetour2, "white", "-fx-background-radius: 15");
            //        break;
            //    case "deconnecter":
            //        this.vue.changeButtonColor(this.deconnecter, "lightgrey", "-fx-background-radius: 15");
            //        break;
            //    case "btnCompte":
            //        this.vue.changeButtonColor(this.btnCompte, "lightgrey", "-fx-background-radius: 15");
            //        break;
            //}
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
            if(btn.getId().equals("btnAccueilDisconnected")){
                super.getVue().afficheAccueil();
            }
            else{
                super.handle(btn);
            }
            //switch(btn.getId()){
            //    case "btnAccueil":
            //        this.vue.afficheAccueil();
            //        break;
            //    case "btnRetour":
            //        this.vue.afficheAccueil();
            //        break;
            //    case "btnAccueil2":
            //        this.vue.afficheAccueilConnecte();
            //        break;
            //    case "btnRetour2":
            //        this.vue.afficheAccueilConnecte();
            //        break;   
            //    case "btnConnexion":
            //        this.vue.afficheLogin();
            //        break;
            //    case "deconnecter":
            //        this.vue.afficheAccueil();
            //        break;
            //    case "btnCompte":
            //        this.vue.afficheMonCompte();
            //        break;
            //}
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}