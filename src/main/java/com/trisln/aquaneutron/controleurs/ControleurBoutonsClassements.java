package com.trisln.aquaneutron.controleurs;

import com.trisln.aquaneutron.vue.TriSLN;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;

import javafx.scene.control.TableColumn;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import com.trisln.aquaneutron.modele.Classement;
import com.trisln.aquaneutron.modele.Participant;

import javafx.collections.FXCollections;

import java.util.List;

import java.sql.SQLException;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import com.trisln.aquaneutron.bd.BdTriSLN;

public class ControleurBoutonsClassements implements EventHandler<ActionEvent> {
    private TriSLN vue;

    private BdTriSLN bdTriSLN;
    private ObservableList<Classement> listeClassements;

    @FXML
    private TableView<Classement> tableViewClassements;
    @FXML
    private Button btnConnexion;
    @FXML
    private Button btnAccueil;
    @FXML
    private Button btnRetour;
    @FXML
    private Button btnAccueil2;
    @FXML
    private Button btnRetour2;
    @FXML
    private Button deconnecter;
    @FXML
    private Button btnCompte;
    @FXML
    private Button genpdf;
    @FXML
    private ComboBox<String> idSC;
    @FXML
    private ComboBox<String> idG;
    @FXML
    private Button btnValider;

    public ControleurBoutonsClassements(TriSLN vue) {
        this.vue = vue;
        this.bdTriSLN = vue.getBd();
    }

    @FXML
    public void initialize() {
        if (this.tableViewClassements != null) {
            TableColumn<Classement, Integer> colPositions = new TableColumn<>("Positions");
            colPositions.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPosGeneral()).asObject());

            TableColumn<Classement, String> colTemps = new TableColumn<>("Temps");
            colTemps.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTemps()));

            TableColumn<Classement, String> colNomPrenom = new TableColumn<>("Nom/Prénom");
            colNomPrenom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getParticipant().getNom() + " " + cellData.getValue().getParticipant().getPrenom()));

            TableColumn<Classement, String> colClubEquipe = new TableColumn<>("Club/Equipe");
            colClubEquipe.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getParticipant().getClub()));

            TableColumn<Classement, Integer> colDossard = new TableColumn<>("Dossard");
            colDossard.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getParticipant().getDossard()).asObject());

            TableColumn<Classement, String> colCategorie = new TableColumn<>("Catégorie");
            colCategorie.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getParticipant().getCategorie()));

            TableColumn<Classement, String> colClassementCategorie = new TableColumn<>("Classements Catégories");
            colClassementCategorie.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPosCategorie()));

            TableColumn<Classement, String> colLicence = new TableColumn<>("Licence");
            colLicence.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getParticipant().getLicence()));

            tableViewClassements.getColumns().setAll(colPositions, colTemps, colNomPrenom, colClubEquipe, colDossard, colCategorie, colClassementCategorie, colLicence);
        }
    }
    
    public void handleComboBoxCategorie(ActionEvent event){
        if(!this.idSC.getValue().equals("-- Choisir une sous-catégorie --")){
            this.btnValider.setDisable(false);
        }
        else{
            this.btnValider.setDisable(true);
        }
    }

    public void handleComboBoxGenre(ActionEvent event){
        if(!this.idG.getValue().equals("-- Choisir un genre --")){
            this.btnValider.setDisable(false);
        }
        else{
            this.btnValider.setDisable(true);
        }
    }

    @FXML
    public void handleBtnClassementMouseEntered(MouseEvent event) {
        try {
            Button btn = (Button) event.getSource();
            switch (btn.getId()) {
                case "btnConnexion":
                    this.vue.changeButtonColor(this.btnConnexion, "#949494", "-fx-background-radius: 15");
                    break;
                case "btnAccueil":
                    this.vue.changeButtonColor(this.btnAccueil, "#949494", "-fx-background-radius: 15");
                    break;
                case "btnRetour":
                    this.vue.changeButtonColor(this.btnRetour, "white", "");
                    break;
                case "btnAccueil2":
                    this.vue.changeButtonColor(this.btnAccueil2, "#949494", "-fx-background-radius: 15");
                    break;
                case "btnRetour2":
                    this.vue.changeButtonColor(this.btnRetour2, "white", "");
                    break;
                case "deconnecter":
                    this.vue.changeButtonColor(this.deconnecter, "#949494", "-fx-background-radius: 15");
                    break;
                case "btnCompte":
                    this.vue.changeButtonColor(this.btnCompte, "#949494", "-fx-background-radius: 15");
                    break;
                case "genpdf":
                    this.vue.changeButtonColor(this.genpdf, "#949494", "-fx-background-radius: 15");
                    break;
                case "btnValider":
                    this.vue.changeButtonColor(this.btnValider, "#949494", "-fx-background-radius: 15");
                    break;
            }
        } catch (Exception e) {
            System.err.println("Erreur lors du survol du bouton (handleBtnClassementMouseEntered): " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void handleBtnClassementMouseExited(MouseEvent event) {
        try {
            Button btn = (Button) event.getSource();
            switch (btn.getId()) {
                case "btnConnexion":
                    this.vue.changeButtonColor(this.btnConnexion, "lightgrey", "-fx-background-radius: 15");
                    break;
                case "btnAccueil":
                    this.vue.changeButtonColor(this.btnAccueil, "lightgrey", "-fx-background-radius: 15");
                    break;
                case "btnRetour":
                    this.vue.changeButtonColor(this.btnRetour, "white", "-fx-background-radius: 15");
                    break;
                case "btnAccueil2":
                    this.vue.changeButtonColor(this.btnAccueil2, "lightgrey", "-fx-background-radius: 15");
                    break;
                case "btnRetour2":
                    this.vue.changeButtonColor(this.btnRetour2, "white", "-fx-background-radius: 15");
                    break;
                case "deconnecter":
                    this.vue.changeButtonColor(this.deconnecter, "lightgrey", "-fx-background-radius: 15");
                    break;
                case "btnCompte":
                    this.vue.changeButtonColor(this.btnCompte, "lightgrey", "-fx-background-radius: 15");
                    break;
                case "genpdf":
                    this.vue.changeButtonColor(this.genpdf, "lightgrey", "-fx-background-radius: 15");
                    break;
                case "btnValider":
                    this.vue.changeButtonColor(this.btnValider, "lightgrey", "-fx-background-radius: 15");
                    break;
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la sortie du survol du bouton (handleBtnClassementMouseExited): " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            Button btn = (Button) event.getSource();
            switch (btn.getId()) {
                case "btnAccueil":
                    this.vue.afficheAccueil();
                    break;
                case "btnRetour":
                    this.vue.afficheAccueil();
                    break;
                case "btnAccueil2":
                    this.vue.afficheAccueilConnecte();
                    break;
                case "btnRetour2":
                    this.vue.afficheAccueilConnecte();
                    break;
                case "btnConnexion":
                    this.vue.afficheLogin();
                    break;
                case "deconnecter":
                    this.vue.afficheAccueil();
                    break;
                case "btnCompte":
                    this.vue.afficheMonCompte();
                    break;
                case "genpdf":
                    if (this.idSC.getValue() != null && !this.idSC.getValue().equals("-- Choisir une sous-catégorie --")) {
                        if (this.idG.getValue() != null && !this.idG.getValue().equals("-- Choisir un genre --")) {
                            this.vue.affichePDF("servinfo-maria", "guihard", "guihard", "DBguihard", this.idSC.getValue(), this.idG.getValue());
                        } else {
                            System.err.println("Aucune sélection de genre.");
                        }
                    } else {
                        System.err.println("Aucune sélection de sous-catégorie.");
                    }
                    break;
                case "btnValider":
                    String sousCategorie = idSC.getValue();
                    String genre = idG.getValue();
                    if (sousCategorie != null && genre != null) {
                        List<Classement> classements = bdTriSLN.getClassements(sousCategorie, genre);
                        tableViewClassements.setItems(FXCollections.observableArrayList(classements));
                    } else {
                        System.err.println("Veuillez sélectionner une sous-catégorie et un genre.");
                    }
                    break;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}