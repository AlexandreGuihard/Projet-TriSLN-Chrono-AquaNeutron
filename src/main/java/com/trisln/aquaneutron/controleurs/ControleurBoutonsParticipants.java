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
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.trisln.aquaneutron.modele.*;
import java.util.List;
import java.util.ArrayList;
import javafx.scene.control.TableColumn;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;



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
    // TableViews
    @FXML
    private TableView<Participant> tableViewMasculin;
    @FXML
    private TableView<Participant> tableViewFeminin;

    public ControleurBoutonsParticipants(TriSLN vue){
        super();
        this.setBoutons(vue);
    }

    private List<Participant> getParticipantsDUnSexe(List<Participant> participantsRelais, List<Participant> participantLicenceCourseIndiv, List<Participant> participantsNonLicenceCourseIndividuelles, char sexe){
        List<Participant> participantsFiltres = new ArrayList<>();
        System.out.println("Participants relais:");
        for(Participant p:participantsRelais){
            System.out.println(p);
            System.out.println(p.getSexe());
            System.out.println(sexe);
            if(p.getSexe()==sexe){
                System.out.println("------------------");
                System.out.println(p);
                System.out.println("------------------");
                participantsFiltres.add(p);
            }
        }
        System.out.println("Participants licence:");
        for(Participant p:participantLicenceCourseIndiv){
            System.out.println(p);
            if(p.getSexe()==sexe){
                participantsFiltres.add(p);
            }
        }
        System.out.println("Participants non licence:");
        for(Participant p:participantsNonLicenceCourseIndividuelles){
            System.out.println(p);
            if(p.getSexe()==sexe){
                participantsFiltres.add(p);
            }
        }
        return participantsFiltres;
    }

    @FXML
    public void initialize() {
        if(tableViewMasculin!=null && tableViewFeminin!=null){
            // Colonnes pour la tableViewMasculin
            TableColumn<Participant, String> colNomM = new TableColumn<>("Nom");
            colNomM.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
                    
            TableColumn<Participant, String> colPrenomM = new TableColumn<>("Prénom");
            colPrenomM.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrenom()));
                    
            TableColumn<Participant, String> colClubM = new TableColumn<>("Club");
            colClubM.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClub()));
                    
            TableColumn<Participant, Boolean> colLicenceM = new TableColumn<>("Licence");
            colLicenceM.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().getLicence()));
                    
            TableColumn<Participant, Integer> colDossardM = new TableColumn<>("Dossard");
            colDossardM.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
                    
            // Colonnes pour la tableViewFeminin
            TableColumn<Participant, String> colNomF = new TableColumn<>("Nom");
            colNomF.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
                    
            TableColumn<Participant, String> colPrenomF = new TableColumn<>("Prénom");
            colPrenomF.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPrenom()));
                    
            TableColumn<Participant, String> colClubF = new TableColumn<>("Club");
            colClubF.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClub()));
                    
            TableColumn<Participant, Boolean> colLicenceF = new TableColumn<>("Licence");
            colLicenceF.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().getLicence()));
                    
            TableColumn<Participant, Integer> colDossardF = new TableColumn<>("Dossard");
            colDossardF.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
                    
            tableViewMasculin.getColumns().setAll(colNomM, colPrenomM, colClubM, colLicenceM, colDossardM);
            tableViewFeminin.getColumns().setAll(colNomF, colPrenomF, colClubF, colLicenceF, colDossardF);

            try {
                tableViewMasculin.getItems().clear();
                tableViewFeminin.getItems().clear();
                // Récupération des participants depuis la base de données
                List<Participant> participantsRelais=TriSLN.getBd().getParticipantsCourseRelais();
                List<Participant> participantsLicence=TriSLN.getBd().getParticipantsLicenceCourseIndividuelles();
                List<Participant> participantsNonLicence = TriSLN.getBd().getParticipantsNonLicenceCourseIndividuelles();

                List<Participant> participantsMasculin=getParticipantsDUnSexe(participantsRelais, participantsLicence, participantsNonLicence, 'M');
                List<Participant> participantsFeminin=getParticipantsDUnSexe(participantsRelais, participantsLicence, participantsNonLicence, 'F');
                System.out.println(participantsMasculin);
                System.out.println(participantsFeminin);
                ObservableList<Participant> masculinList = FXCollections.observableArrayList(participantsMasculin);
                ObservableList<Participant> femininList = FXCollections.observableArrayList(participantsFeminin);

                tableViewMasculin.setItems(masculinList);
                tableViewFeminin.setItems(femininList);

                System.out.println(tableViewFeminin);
                System.out.println(tableViewMasculin);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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