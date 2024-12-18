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
import src.vue.*;
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

    public ControleurBoutonsParticipants(TriSLN vue){
        super();
        this.setBoutons();
    }

    private void setBoutons(){
        super.setBCompte(btnCompte);
        super.setBAccueil(btnAccueil);
        super.setBRetour(btnRetour);
        super.setBDeconnexion(btnDeconnexion);
        super.setVue(vue);
    }

    @FXML
    public void handleBtnParticipantsMouseEntered(MouseEvent event){
        boolean superButton=false;
        Button changedButton=null;
        String newBtnColor="";
        String otherStyle="";
        Button btn=(Button)event.getSource();
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
            default:
                superButton=true;
                break;                 

        }
        if(superButton){
            super.handleBtnsMouseEntered(btn);
        }
        else{
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
            default:
                superButton=true;
                break;
        }
        if(superButton){
            super.handleBtnsMouseExited(btn);
        }
        else{
            super.getVue().changeButtonColor(changedButton, newBtnColor, otherStyle);
        }
    }

    @Override
    public void handle(ActionEvent event){
        Button btn=(Button)event.getSource();
        if(btn.getId().equals("btnAccueil") || btn.getId().equals("btnRetour") || btn.getId().equals("btnCompte") || btn.getId().equals("btnDeconnexion")){
            super.handle(btn);
        }
        else{
            try{
                File file=null;
                FXMLLoader loader=null;
                switch(btn.getId()){
                    case "btnV":
                        file=new File("src/vue/fxml/SAEprojetPopUpVeteran.fxml");
                        loader=new FXMLLoader(file.toURI().toURL());
                        super.getVue().affichePopUp(loader, "V");
                        break;
                    case "btnS":
                        file=new File("src/vue/fxml/SAEprojetPopUpSenior.fxml");
                        loader=new FXMLLoader(file.toURI().toURL());
                        super.getVue().affichePopUp(loader, "S");
                        break;
                    case "btnRetourCategorie":
                        super.getVue().afficheParticipants();
                        break;    
                    default:
                        super.getVue().afficheLesParticipants();
                        break;          
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}