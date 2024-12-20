package com.trisln.aquaneutron.controleurs;

import com.trisln.aquaneutron.vue.TriSLN;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import com.trisln.aquaneutron.vue.*;
import java.io.File;
import java.io.IOException;


public class ControleurBoutonsParticipants extends ControleurBoutons implements EventHandler<ActionEvent>{
    private TriSLN vue;
    // Catégories
    @FXML
    private Button btnMP;
    @FXML
    private Button btnPO;
    @FXML
    private Button btnPU;
    @FXML
    private Button btnBE;
    @FXML
    private Button btnMI;
    @FXML
    private Button btnCA;
    @FXML
    private Button btnJU;
    @FXML
    private Button btnV;
    @FXML
    private Button btnS;
    // Autres boutons
    @FXML
    private Button btnCompte;
    @FXML
    private Button btnAccueil;
    @FXML
    private Button btnRetour;
    @FXML
    private Button btnRetourCategorie;
    @FXML
    private Button btnDeconnexion;
    // boutons annexes
    @FXML
    private Button btnAjouterParticipant;
    @FXML
    private Button btnSuprimerParticipant;
    @FXML
    private Button btnModifierParticipant;

    public ControleurBoutonsParticipants(TriSLN vue){
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
    public void handleBtnCategorieMouseEntered(MouseEvent event){
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
                case "btnMP":
                    changedButton=this.btnMP;
                    newBtnColor="#105c74";
                    break;
                case "btnPO":
                    changedButton=this.btnPO;
                    newBtnColor="#105c74";
                    break;
                case "btnPU":
                    changedButton=this.btnPU;
                    newBtnColor="#105c74";
                    break;
                case "btnBE":
                    changedButton=this.btnBE;
                    newBtnColor="#105c74";
                    break;
                case "btnMI":
                    changedButton=this.btnMI;
                    newBtnColor="#105c74";
                    break;
                case "btnCA":
                    changedButton=this.btnCA;
                    newBtnColor="#105c74";
                    break;
                case "btnJU":
                    changedButton=this.btnJU;
                    newBtnColor="#105c74";
                    break;
                case "btnV":
                    changedButton=this.btnV;
                    newBtnColor="#105c74";
                    break;
                case "btnS":
                    changedButton=this.btnS;
                    newBtnColor="#105c74";
                    break;   
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
                default:
                    superButton=true;
                    break;   
            }  
            super.getVue().changeButtonColor(changedButton, newBtnColor, otherStyle);
        }            
        }

    @FXML
    public void handleBtnCategorieMouseExited(MouseEvent event){
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
                case "btnMP":
                    changedButton=this.btnMP;
                    newBtnColor="#2596BE";
                    break;
                case "btnPO":
                    changedButton=this.btnPO;
                    newBtnColor="#2596BE";
                    break;
                case "btnPU":
                    changedButton=this.btnPU;
                    newBtnColor="#2596BE";
                    break;
                case "btnBE":
                    changedButton=this.btnBE;
                    newBtnColor="#2596BE";
                    break;
                case "btnMI":
                    changedButton=this.btnMI;
                    newBtnColor="#2596BE";
                    break;
                case "btnCA":
                    changedButton=this.btnCA;
                    newBtnColor="#2596BE";
                    break;
                case "btnJU":
                    changedButton=this.btnJU;
                    newBtnColor="#2596BE";
                    break;
                case "btnV":
                    changedButton=this.btnV;
                    newBtnColor="#2596BE";
                    break;
                case "btnS":
                    changedButton=this.btnS;
                    newBtnColor="#2596BE";
                    break;

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
                default:
                    superButton=true;
                    break;
            }
            super.getVue().changeButtonColor(changedButton, newBtnColor, otherStyle);
        }
    }


    @Override
    public void handle(ActionEvent event){
        Button btn=(Button)event.getSource();
        if(btn.getId().equals("btnAccueil") || btn.getId().equals("btnRetour") || btn.getId().equals("btnCompte") || btn.getId().equals("btnDeconnexion")|| btn.getId().equals("btnConnexion")){
            super.handle(btn);
        }
        else{
            try{
                File file=null;
                FXMLLoader loader=null;
                switch(btn.getId()){
                    case "btnV":
                        file=new File("src/main/resources/com/trisln/aquaneutron/trislnaquaneutron/SAEprojetPopUpVeteran.fxml");
                        loader=new FXMLLoader(file.toURI().toURL());
                        super.getVue().affichePopUp(loader, "V");
                        break;
                    case "btnS":
                        file=new File("src/main/resources/com/trisln/aquaneutron/trislnaquaneutron/SAEprojetPopUpSenior.fxml");
                        loader=new FXMLLoader(file.toURI().toURL());
                        super.getVue().affichePopUp(loader, "S");
                        break;
                    case "btnRetourCategorie":
                        super.getVue().afficheParticipants();
                        break;
     
                    case "btnCompte":
                        this.vue.afficheMonCompte();
                        break;
                    case "btnConnexion":
                        this.vue.afficheLogin();
                        break;    
                    case "btnDeconnexion":
                        this.vue.afficheAccueil();
                        break;

                    case "btnAjouterParticipant":
                        super.getVue().afficheAjouterP();
                        break;
                    case "btnSuprimerParticipant":
                        super.getVue().afficheSupprimerP();
                        break;
                    case "btnModifierParticipant":
                        super.getVue().afficheModifierP();
                        break;    
   
                    default:
                        super.getVue().afficheLesParticipants();

                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}