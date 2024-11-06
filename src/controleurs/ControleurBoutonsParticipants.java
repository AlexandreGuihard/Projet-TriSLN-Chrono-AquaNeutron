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


public class ControleurBoutonsParticipants implements EventHandler<ActionEvent>{
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

    public ControleurBoutonsParticipants(TriSLN vue){
        this.vue=vue;
    }

    @FXML
    public void handleBtnParticipantsMouseEntered(MouseEvent event){
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
            case "btnCompte":
                changedButton=this.btnCompte;
                newBtnColor="#949494";
                otherStyle="-fx-background-radius: 15;";
                break;
            case "btnAccueil":
                changedButton=this.btnAccueil;
                newBtnColor="#949494";
                otherStyle="-fx-background-radius: 15;";
                break;
            default:
                // Bouton Retour
                changedButton=this.btnRetour;
                                       

        }
        this.vue.changeButtonColor(changedButton, newBtnColor, otherStyle);
        
    }

    @FXML
    public void handleBtnCategorieMouseExited(MouseEvent event){
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
            case "btnCompte":
                changedButton=this.btnCompte;
                newBtnColor="lightgrey";
                otherStyle="-fx-background-radius: 15;";
                break;
            case "btnAccueil":
                changedButton=this.btnAccueil;
                newBtnColor="lightgrey";
                otherStyle="-fx-background-radius: 15;";
                break;
        }
        this.vue.changeButtonColor(changedButton, newBtnColor, otherStyle);
    }

    @Override
    public void handle(ActionEvent event){
        Button btn=(Button)event.getSource();
        if(btn.getId().equals("btnAccueil") || btn.getId().equals("btnRetour")){
            this.vue.afficheAccueilConnecte();
        }
        else{
            try{
                File file=null;
                FXMLLoader loader=null;
                switch(btn.getId()){
                    case "btnV":
                        file=new File("src/vue/fxml/SAEprojetPopUpVeteran.fxml");
                        loader=new FXMLLoader(file.toURI().toURL());
                        this.vue.affichePopUp(loader, "V");
                        break;
                    case "btnS":
                        file=new File("src/vue/fxml/SAEprojetPopUpSenior.fxml");
                        loader=new FXMLLoader(file.toURI().toURL());
                        this.vue.affichePopUp(loader, "S");
                        break;
                    case "btnCompte":
                        System.out.println("Compte (Non fait)");
                        break;
                    case "btnRetourCategorie":
                        this.vue.afficheParticipants();
                        break;    
                    default:
                        this.vue.afficheLesParticipants();
                        break;          
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}