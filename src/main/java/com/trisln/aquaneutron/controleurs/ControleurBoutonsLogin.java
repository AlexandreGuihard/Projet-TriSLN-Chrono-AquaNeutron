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

public class ControleurBoutonsLogin extends ControleurBoutons implements EventHandler<ActionEvent>{
    private TriSLN vue;

    @FXML
    private Button btnConnecter;
    @FXML
    private Button btnAccueil;
    @FXML
    private Button btnRetour;

    public ControleurBoutonsLogin(TriSLN vue){
        super();
        this.setBoutons(vue);
    }

    private void setBoutons(TriSLN vue){
        super.setVue(vue);
        super.setBRetour(btnRetour);
        super.setBAccueil(btnAccueil);
    }

    @FXML
    public void handleBtnLoginMouseEntered(MouseEvent event){
        try{
            Button btn=(Button)event.getSource();
            if(btn.getId().equals("btnConnecter")){
                super.getVue().changeButtonColor(this.btnConnecter, "#105c74", "");
            }
            else{
                super.handleBtnsMouseEntered(btn);
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
            if(btn.getId().equals("btnConnecter")){
                super.getVue().changeButtonColor(this.btnConnecter, "#2596BE", "");
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

    @Override
    public void handle(ActionEvent event) {
        try{
            Button btn=(Button) event.getSource();
            if(btn.getId().equals("btnConnecter")){
                super.getVue().afficheAccueilConnecte();
            }
            else{
                super.handle(btn);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}