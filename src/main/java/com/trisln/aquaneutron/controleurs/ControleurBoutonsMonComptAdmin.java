package com.trisln.aquaneutron.controleurs;

import javafx.event.EventHandler;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import com.trisln.aquaneutron.vue.TriSLN;

public class ControleurBoutonsMonComptAdmin extends ControleurBoutons implements EventHandler<ActionEvent>{
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
    private Button Bbenevole;
    @FXML
    private Button changer_image;
    

    public ControleurBoutonsMonComptAdmin(TriSLN vue){
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
    public void handleBtnMonComptAdminMouseEntered(MouseEvent event){
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
                    case "Bbenevole":
                        changedButton=this.Bbenevole;
                        newBtnColor="#105c74";
                        break;
                    case "changer_image":
                        changedButton=this.changer_image;
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

    @FXML
    public void handleBtnMonComptAdminMouseExited(MouseEvent event){
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
                    case "Bbenevole":
                    changedButton=this.Bbenevole;
                    newBtnColor="#2596BE";
                        break;
                    case "changer_image":
                    changedButton=this.changer_image;
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

    @Override
    public void handle(ActionEvent event){

        try {Button btn=(Button)event.getSource();
            if(btn.getId().equals("btnAccueil") || btn.getId().equals("btnRetour") || btn.getId().equals("btnCompte") || btn.getId().equals("btnDeconnexion")|| btn.getId().equals("btnConnexion")){
                    super.handle(btn);
            }
            else{
                switch(btn.getId()){
                    case "Bbenevole":
                        System.out.println("ajout benevole x");
                    break;
                    case "changer_image":
                        System.out.println("change image");
                        break;
                    default:
                        super.getVue().afficheModifierP();
                        
            }
                
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        } 
    }
}


