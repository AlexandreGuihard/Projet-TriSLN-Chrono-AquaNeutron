package com.trisln.aquaneutron.controleurs;

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
import com.trisln.aquaneutron.vue.TriSLN;


public class ControleurBoutonsNouvelleCourses extends ControleurBoutons implements EventHandler<ActionEvent> {
    private TriSLN vue;

    @FXML
    private Button btnAjoutCourse;
    @FXML
    private Button btnAccueil;
    @FXML
    private Button btnRetour;
    @FXML
    private Button btnDeconnexion;
    @FXML
    private Button btnCompte;





    public ControleurBoutonsNouvelleCourses(TriSLN vue){
        super();
        this.setBoutons(vue);
    }

    private void setBoutons(TriSLN vue){
        super.setVue(vue);
        super.setBAccueil(btnAccueil);
        super.setBCompte(btnCompte);
        super.setBDeconnexion(btnDeconnexion);
        super.setBRetour(btnRetour);


        
    }

    @FXML
    public void handleBtnCoursesMouseEntered(MouseEvent event){
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

                    case "btnAjoutCourse":
                        changedButton=this.btnAjoutCourse;
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
    public void handleBtnCoursesMouseExited(MouseEvent event){
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
                    case "btnAjoutCourse":
                        changedButton=this.btnAjoutCourse;
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
            boolean superButton=false;
            Button changedButton=null;
            String newBtnColor="";
            String otherStyle="";
            Button btn=(Button)event.getSource();
            if(btn.getId().equals("btnAccueil") || btn.getId().equals("btnRetour") || btn.getId().equals("btnCompte") || btn.getId().equals("btnDeconnexion")|| btn.getId().equals("btnConnexion")){
                super.handle(btn);
            }
            else{
                switch(btn.getId()){
                    default:
                        superButton=true;
                        break;
                }
            }
            
        } 

}

