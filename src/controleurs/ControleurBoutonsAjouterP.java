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

public class ControleurBoutonsAjouterP extends ControleurBoutons implements EventHandler<ActionEvent>{
    private TriSLN vue;

    // log accueil et AccConnecter

    @FXML
    private Button btnConnexion;
    @FXML
    private Button btnRetour;
    @FXML
    private Button btnDeconnexion;
    @FXML
    private Button btnAccueil;
    @FXML
    private Button btnCompte;

    @FXML
    private Button btnAjouterParticipant;
    @FXML
    private Button btnSuprimerParticipant;
    @FXML
    private Button btnModifierParticipant;

    @FXML
    private Button btnImporterCSV;

    

    public ControleurBoutonsAjouterP(TriSLN vue){
        super();
        this.setBoutons(vue);
    }


    private void setBoutons(TriSLN vue){
        super.setBCompte(btnCompte);
        super.setBAccueil(btnAccueil);
        super.setBRetour(btnRetour);
        super.setBDeconnexion(btnDeconnexion);
        super.setVue(vue);
    }

    @FXML
    public void handleBtnAjouterPMouseEntered(MouseEvent event){
        try{
            boolean superButton=false;
            Button changedButton=null;
            String newBtnColor="";
            String otherStyle="";
            Button btn=(Button)event.getSource();
            if(btn.getId().equals("btnAccueil") || btn.getId().equals("btnRetour") || btn.getId().equals("btnCompte") || btn.getId().equals("btnDeconnexion")){
                super.handle(btn);
            }
            else{
                switch(btn.getId()){
                    case "btnAjouterParticipant":
                        changedButton=this.btnAjouterParticipant;
                        newBtnColor="#105c74";
                        break;
                    case "btnSuprimerParticipant":
                        changedButton=this.btnSuprimerParticipant;
                        newBtnColor="#105c74";
                        break;
                    case "btnModifierParticipant":
                        changedButton=this.btnModifierParticipant;
                        newBtnColor="#105c74";
                        break;

                    case "btnImporterCSV":
                        changedButton=this.btnImporterCSV;
                        newBtnColor="#105c74";
                        break;
                    default:
                        superButton=true;
                        break;
                    }
                }
            if(superButton){
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

    @FXML
    public void handleBtnAjouterPMouseExited(MouseEvent event){
        try{
            boolean superButton=false;
            Button changedButton=null;
            String newBtnColor="";
            String otherStyle="";
            Button btn=(Button)event.getSource();
            if(btn.getId().equals("btnAccueil") || btn.getId().equals("btnRetour") || btn.getId().equals("btnCompte") || btn.getId().equals("btnDeconnexion")){
                super.handle(btn);
            }
            else{
                switch(btn.getId()){
                    case "btnAjouterParticipant":
                    changedButton=this.btnAjouterParticipant;
                    newBtnColor="#2596BE";
                        break;
                    case "btnSuprimerParticipant":
                    changedButton=this.btnSuprimerParticipant;
                    newBtnColor="#2596BE";
                        break;
                    case "btnModifierParticipant":
                    changedButton=this.btnModifierParticipant;
                    newBtnColor="#2596BE";
                        break;
                    case "btnImporterCSV":
                        changedButton=this.btnImporterCSV;
                        newBtnColor="#2596BE";
                        break;
                    default:
                        superButton=true;
                        break;
                }
            }
            if(superButton){
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

        try {Button btn=(Button)event.getSource();
        if(btn.getId().equals("btnAccueil") || btn.getId().equals("btnRetour") || btn.getId().equals("btnCompte") || btn.getId().equals("btnDeconnexion")){
                super.handle(btn);
        }
        else{
            switch(btn.getId()){
                case "btnRetour":
                    super.getVue().super.getVue().afficheLesParticipants();
                    break;
                default:
                    super.getVue().super.getVue().afficheLesParticipants();
                    
        }
            
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        } 
    }
}