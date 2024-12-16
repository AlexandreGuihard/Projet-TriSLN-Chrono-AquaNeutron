package src.controleurs;

import javafx.event.EventHandler;

import java.io.IOException;

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


public class ControleurBoutonsCompte extends ControleurBoutons implements EventHandler<ActionEvent> {
    private TriSLN vue;

    @FXML
    private Button btnAccueil;
    @FXML
    private Button btnRetour;
    @FXML
    private Button btnDeconnexion;

    public ControleurBoutonsCompte(TriSLN vue){
        super();
        this.setBoutons(vue);
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
            Button btn=(Button)event.getSource();
            super.handleBtnsMouseEntered(btn);
            //switch(btn.getId()){
            //    case "btnAccueil":
            //        this.vue.changeButtonColor(this.btnAccueil, "#949494", "-fx-background-radius: 15");
            //        break;
            //    case "btnDeconnexion":
            //        this.vue.changeButtonColor(this.btnDeconnexion, "#949494", "-fx-background-radius: 15");
            //        break;
            //    case "btnRetour":
            //        this.vue.changeButtonColor(this.btnRetour, "lightgrey", "-fx-background-radius: 15");
            //        break;
            //}
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
            super.handleBtnsMouseExited(btn);
            //switch(btn.getId()){
            //    case "btnAccueil":
            //        this.vue.changeButtonColor(this.btnAccueil, "lightgrey", "-fx-background-radius: 15");
            //        break;
            //    case "btnDeconnexion":
            //        this.vue.changeButtonColor(this.btnDeconnexion, "lightgrey", "-fx-background-radius: 15");
            //        break;
            //    case "btnRetour":
            //        this.vue.changeButtonColor(this.btnRetour, "white", "-fx-background-radius: 15");
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
        try {
            Button btn=(Button) event.getSource();
            super.handle(btn);
            //switch(btn.getId()){
            //    case "btnRetour":
            //        this.vue.afficheAccueilConnecte();
            //        break;
            //    case "btnAccueil":
            //        this.vue.afficheAccueilConnecte();
            //        break;
            //    case "btnDeconnexion":
            //        this.vue.afficheAccueil();
            //        break;
            //}
        } 
        catch (Exception e){
            e.printStackTrace();
        } 
    }
}