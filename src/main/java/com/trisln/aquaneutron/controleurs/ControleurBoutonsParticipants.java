package com.trisln.aquaneutron.controleurs;

import com.trisln.aquaneutron.vue.TriSLN;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import java.io.File;
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

    private String categorieChoisie;

    private String sousCategorieChoisie;

    /**
     * Constructeur de la classe appelée lorsqu'on vient de l'accueil
     * @param vue la vue
     */
    public ControleurBoutonsParticipants(TriSLN vue){
        super();
        this.setBoutons(vue);
        this.categorieChoisie=null;
        this.sousCategorieChoisie=null;
    }

    /**
     * Constructeur de la classe appelée lorsqu'on vient de la page d'accueil des participants
     * @param vue la vue
     * @param categorieChoisie la catégorie choisie sur la page d'accueil des participants
     * @param sousCategorieChoisie la sous catégorie choisie sur la page d'accueil des participants
     */
    public ControleurBoutonsParticipants(TriSLN vue, String categorieChoisie, String sousCategorieChoisie){
        super();
        this.setBoutons(vue);
        switch(categorieChoisie){
            case "btnMP":
                this.categorieChoisie="MP";
                break;
            case "btnPO":
                this.categorieChoisie="PO";
                break;
            case "btnPU":
                this.categorieChoisie="PU";
                break;
            case "btnBE":
                this.categorieChoisie="BE";
                break;
            case "btnMI":
                this.categorieChoisie="MI";
                break;
            case "btnCA":
                this.categorieChoisie="CA";
                break;
            case "btnJU":
                this.categorieChoisie="JU";
                break;
            default:
                this.categorieChoisie=categorieChoisie;
                this.sousCategorieChoisie=sousCategorieChoisie;
                break;                           
        }
    }

    /**
     * Getter des participants du sexe précisé en paramètre
     * @param participantsRelais les participants relais
     * @param participantLicenceCourseIndiv les participants ayant une licence
     * @param participantsNonLicenceCourseIndividuelles les participants n'ayant pas de licence
     * @param sexe le sexe qui filtre les listes de participants
     * @return la liste des participants du sexe précisé en paramètre
     */
    private List<Participant> getParticipantsDUnSexe(List<Participant> participantsRelais, List<Participant> participantLicenceCourseIndiv, List<Participant> participantsNonLicenceCourseIndividuelles, char sexe){
        List<Participant> participantsFiltres = new ArrayList<>();
        for(Participant p:participantsRelais){
            if(p.getSexe()==sexe){
                participantsFiltres.add(p);
            }
        }
        for(Participant p:participantLicenceCourseIndiv){
            if(p.getSexe()==sexe){
                participantsFiltres.add(p);
            }
        }
        for(Participant p:participantsNonLicenceCourseIndividuelles){
            if(p.getSexe()==sexe){
                participantsFiltres.add(p);
            }
        }
        return participantsFiltres;
    }

    /**
     * Getter des participants de l'attribut de la sous catégorie
     * @param participantsFiltresParSexe la liste des participants au départ
     * @return la liste des participants filtrés par la sous catégorie
     */
    private List<Participant> getParticipantsDUneCategorie(List<Participant> participantsFiltresParSexe){
        List<Participant> participantsFiltresParCategorie=new ArrayList<>();
        for(Participant p:participantsFiltresParSexe){
            if(sousCategorieChoisie==null){
                if(p.getCategorie().equals(categorieChoisie)){
                    participantsFiltresParCategorie.add(p);
                }
            }
            else if(p.getCategorie().equals(categorieChoisie) && p.getSousCategorie().equals(sousCategorieChoisie)){
                participantsFiltresParCategorie.add(p);
            }
        }
        return participantsFiltresParCategorie;
    }

    /**
     * Getter de la catégorie choisie
     * @return la catégorie choisie
     */
    public String getCategorieChoisie(){
        return categorieChoisie;
    }

    /**
     * Méthode appelée après la fin du constructeur. Initialisation des tableviews
     * @throws Exception
     */
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

                List<Participant> participantsMasculinCategorie=getParticipantsDUneCategorie(getParticipantsDUnSexe(participantsRelais, participantsLicence, participantsNonLicence, 'M'));
                List<Participant> participantsFemininCategorie=getParticipantsDUneCategorie(getParticipantsDUnSexe(participantsRelais, participantsLicence, participantsNonLicence, 'F'));
                
                ObservableList<Participant> masculinList = FXCollections.observableArrayList(participantsMasculinCategorie);
                ObservableList<Participant> femininList = FXCollections.observableArrayList(participantsFemininCategorie);
                tableViewMasculin.setItems(masculinList);
                tableViewFeminin.setItems(femininList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Setter des boutons de classe parente
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
     * Méthode appelée lorsque la souris passe sur un bouton
     * @param event l'évènement déclenché par la souris
     */
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

    /**
     * Méthode appelée lorsque la souris quitte sur un bouton
     * @param event l'évènement déclenché par la souris
     */
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

    /**
     * Méthode appelée lorsqu'un bouton est appuyé
     * @param event l'évènement déclenché
     * @throws Exception
     */
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
                        // Bouton d'une des catégorie sauf btnS et btnV
                        super.getVue().afficheLesParticipants(btn.getId(), null);
                        break;

                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}