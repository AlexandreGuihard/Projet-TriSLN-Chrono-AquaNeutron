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
import javafx.scene.input.MouseEvent;
import src.vue.TriSLN;


public class ControleurBoutons implements EventHandler<ActionEvent>{
    private TriSLN vue;

    @FXML
    private Button btnClassements;

//Chono
    @FXML
    private Button btnAJtCourse;
    @FXML
    private Button btnAJtCourse1;
    @FXML
    private Button btnAJtCourse3;
    @FXML
    private Button btnCompte;
    @FXML
    private Button btnAccueil;
    @FXML
    private TextField idNom;
    @FXML
    private TextField idDate;
//Connexion

    @FXML
    private TextField idIdentifiant;
    @FXML
    private TextField idMdp;
    @FXML
    private Button btnConnecter;
// gerer_course
    @FXML
    private Button btnNvlCourse;
// no data
    @FXML
    private Button btnImporterExcel;
// nouvelle course
    @FXML
    private MenuButton idFormat;
    @FXML
    private MenuItem btnJeune;
    @FXML
    private MenuItem btnXS;
    @FXML
    private MenuItem btnS;
    @FXML
    private MenuItem btnM;
    @FXML
    private TextField idHeure;
    @FXML
    private CheckBox idMinPoussins;
    @FXML
    private CheckBox idBenjamins;
    @FXML
    private CheckBox idPoussins;
    @FXML
    private CheckBox idMinimes;    
    @FXML
    private CheckBox idPupilles;    
    @FXML
    private CheckBox idCadets;
    @FXML
    private CheckBox idJunoirs;
    @FXML
    private CheckBox idSeniors;
    @FXML
    private CheckBox idVetran;
//
//participer acc
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
    private Button tbnS;
//
//participercategorie
    @FXML
    private Button btnRetour;
//

    public ControleurBoutons(TriSLN vue){
        this.vue=vue;
        this.vue.setBClassements(this.btnClassements);
    }

    @FXML
    public void handleBtnAccueil(MouseEvent event){
        try{
            this.vue.changeButtonColor(this.btnClassements);
        }
        catch(Exception e){
            System.err.println("Erreur");
        }
    }

    @Override
    public void handle(ActionEvent event){
        Button btn=(Button) event.getSource();
        switch(btn.getText()){
            case "Classements":
                System.out.println("Classement");
                break;
            default:
                System.out.println("Accueil");
                break;
        }
    }
}