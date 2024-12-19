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
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import src.vue.*;
import java.io.File;
import java.io.IOException;

public class ControleurBoutonsNoData extends ControleurBoutons implements EventHandler<ActionEvent>{
    private TriSLN vue;
// bouton classique
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
private Button btnImporterExcel;
@FXML
private Button Bbenevole;


public ControleurBoutonsNoData(TriSLN vue){
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
public void handleBtnNoDataMouseEntered(MouseEvent event){
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
            case "btnImporterExcel":
                changedButton=this.btnImporterExcel;
                newBtnColor="#105c74";
                break;
            case "Bbenevole":
                changedButton=this.Bbenevole;
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
public void handleBtnNoDataMouseExited(MouseEvent event){
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
            case "btnImporterExcel":
                changedButton=this.btnImporterExcel;
                newBtnColor="#2596BE";
                break;
            case "Bbenevole":
                changedButton=this.Bbenevole;
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
                case "btnImporterExcel":
                System.out.println("importation exel");
            break;
            case "Bbenevole":
                super.getVue().afficheSupprimerP();
                break;
            default:
                super.getVue().afficheModifierP();

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
}
