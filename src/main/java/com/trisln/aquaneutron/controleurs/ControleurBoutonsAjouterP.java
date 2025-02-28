package com.trisln.aquaneutron.controleurs;

import javafx.event.EventHandler;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import com.trisln.aquaneutron.vue.TriSLN;

public class ControleurBoutonsAjouterP extends ControleurBoutons implements EventHandler<ActionEvent>{
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

    

    /**
     * Constructeur du controleur
     * @param vue la classe de la vue
     */
    public ControleurBoutonsAjouterP(TriSLN vue){
        super();
        this.setBoutons(vue);
    }

    /**
     * Setter des boutons de la classe parente
     * @param vue la vue
     */
    private void setBoutons(TriSLN vue){
        super.setBCompte(btnCompte);
        super.setBAccueil(btnAccueil);
        super.setBRetour(btnRetour);
        super.setBDeconnexion(btnDeconnexion);
        super.setVue(vue);
    }

    /**
     * Méthode appelée lorsqu'on passe la souris sur un des boutons
     * @param event l'évènement qui se déclenche avec la souris
     */
    @FXML
    public void handleBtnAjouterPMouseEntered(MouseEvent event){
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
                    super.getVue().changeButtonColor(changedButton, newBtnColor, otherStyle);
                }
            
        }
        catch(Exception e){
            System.err.println("Erreur");
            e.printStackTrace();
        }
    }

    /**
     * Méthode appelée lorsqu'on enlève la souris sur un des boutons
     * @param event l'évènement qui se déclenche avec la souris
     */
    @FXML
    public void handleBtnAjouterPMouseExited(MouseEvent event){
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
                super.getVue().changeButtonColor(changedButton, newBtnColor, otherStyle);
            }

        }
        catch(Exception e){
            System.err.println("Erreur");
            e.printStackTrace();
        }
    }

    /**
     * Méthode appelée lorsqu'un bouton est appuyé
     * @param event l'évènement déclenché à l'appui
     */
    @Override
    public void handle(ActionEvent event){

        try {Button btn=(Button)event.getSource();
            if(btn.getId().equals("btnAccueil") || btn.getId().equals("btnCompte") || btn.getId().equals("btnDeconnexion")|| btn.getId().equals("btnConnexion")){
                    super.handle(btn);
            }
            else{
                switch(btn.getId()){
                    case "btnRetour":
                        super.getVue().afficheParticipants();
                        break;
                    case "btnImporterCSV":
                        System.out.println("importation en cours");
                        File file = super.getVue().getFenetreParticipants().reccupererParticipant();
                        if (file != null) {
                            System.out.println(file);
                            System.out.println("lancement");
                            super.getVue().getBd().lectureFichier(file);
                            System.out.println("fin");
                            
                        }
                        else{System.out.println("le fichier est null");}
                        

                        break;
                    case "btnSuprimerParticipant":
                        super.getVue().afficheSupprimerP();
                        break;
                    case "btnAjouterParticipant":
                        super.getVue().afficheAjouterP();
                        break;
                    case "btnModifierParticipant":
                        super.getVue().afficheModifierP();
                        break;
                        
                    default:
                        super.getVue().afficheAjouterP();
                        
            }
                
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        } 
    }
}

