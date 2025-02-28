package com.trisln.aquaneutron.vue;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXMLLoader;
import com.trisln.aquaneutron.modele.*;
import com.trisln.aquaneutron.bd.BdTriSLN;
import com.trisln.aquaneutron.bd.ConnexionMySQL;

/**
 * Classe FenetreClassements.
 * Permet d'afficher les classements des participants.
 */
public class FenetreClassements {

    private TableView<Classement> classement;
    private ComboBox<String> souscategories;
    private String categorieChoisie;
    private ComboBox<String> genre;
    private String genreChoisie;
    private Stage stage;


    /**
     * Constructeur de la classe FenetreClassements.
     * Initialise les composants de l'interface et affiche les classements.
     *
     * @param loader Le FXMLLoader utilisé pour charger la vue.
     * @param stage La fenêtre principale de l'application.
     */
    public FenetreClassements(FXMLLoader loader, Stage stage){
        this.classement = new TableView<>();
    
        TableColumn<Classement, Integer> colPosGeneral = new TableColumn<>("Positions");
        colPosGeneral.setCellValueFactory(new PropertyValueFactory<>("posGeneral"));
    
        TableColumn<Classement, String> colTemps = new TableColumn<>("Temps");
        colTemps.setCellValueFactory(new PropertyValueFactory<>("temps"));
    
        TableColumn<Classement, String> colNomPrenom = new TableColumn<>("Nom/Prénom");
        colNomPrenom.setCellValueFactory(new PropertyValueFactory<>("participant.nomPrenom"));
    
        TableColumn<Classement, String> colClubEquipe = new TableColumn<>("Club/Equipe");
        colClubEquipe.setCellValueFactory(new PropertyValueFactory<>("participant.club"));
    
        TableColumn<Classement, Integer> colLicence = new TableColumn<>("Licence");
        colLicence.setCellValueFactory(new PropertyValueFactory<>("participant.numLicence"));
    
        TableColumn<Classement, Integer> colDossard = new TableColumn<>("Dossard");
        colDossard.setCellValueFactory(new PropertyValueFactory<>("participant.dossard"));
    
        TableColumn<Classement, String> colCategorie = new TableColumn<>("Catégorie");
        colCategorie.setCellValueFactory(new PropertyValueFactory<>("participant.categorie"));
    
        TableColumn<Classement, String> colClassementsCategorie = new TableColumn<>("Classements Catégories");
        colClassementsCategorie.setCellValueFactory(new PropertyValueFactory<>("posCategorie"));
    
        this.classement.getColumns().addAll(colPosGeneral, colTemps, colNomPrenom, colClubEquipe, colLicence, colDossard, colCategorie, colClassementsCategorie);
    
        this.souscategories = new ComboBox<>();
        this.categorieChoisie = "";
        this.genre = new ComboBox<>();
        this.genreChoisie = "";
        this.stage = stage;
        this.afficheClassement(loader);
    }

    /**
     * Retourne le TableView des classements.
     *
     * @return Le TableView des classements.
     */
    public TableView<Classement> getClassement(){
        return this.classement;
    }

    /**
     * Modifie le TableView des classements.
     *
     * @param nouveauClassement Le nouveau TableView des classements.
     */
    public void setClassement(TableView<Classement> nouveauClassement){
        this.classement = nouveauClassement;
    }

    /**
     * Retourne la ComboBox des sous-catégories.
     *
     * @return La ComboBox des sous-catégories.
     */
    public ComboBox<String> getSousCategorie() {
        return this.souscategories;
    }

    /**
     * Modifie la ComboBox des sous-catégories.
     *
     * @param nouvellesSousCategories La nouvelle ComboBox des sous-catégories.
     */
    public void setCategories(ComboBox<String> nouvellesSousCategories) {
        this.souscategories = nouvellesSousCategories;
    }

    /**
     * Retourne la catégorie choisie.
     *
     * @return La catégorie choisie.
     */
    public String getCategorieChoisie(){
        return this.categorieChoisie;
    }

    /**
     * Modifie la catégorie choisie.
     *
     * @param nouvellesCategoriesChoisies La nouvelle catégorie choisie.
     */
    public void setCategoriesChoisies(String nouvellesCategoriesChoisies){
        this.categorieChoisie = nouvellesCategoriesChoisies;
    }

    /**
     * Retourne la ComboBox des genres.
     *
     * @return La ComboBox des genres.
     */
    public ComboBox<String> getGenre(){
        return this.genre;
    }

    /**
     * Modifie la ComboBox des genres.
     *
     * @param nouveauGenre La nouvelle ComboBox des genres.
     */
    public void setGenre(ComboBox<String> nouveauGenre){
        this.genre = nouveauGenre;
    }

    /**
     * Retourne le genre choisi.
     *
     * @return Le genre choisi.
     */
    public String getGenreChoisie(){
        return this.genreChoisie;
    }

    /**
     * Modifie le genre choisi.
     *
     * @param nouveauGenreChoisie Le nouveau genre choisi.
     */
    public void setGenreChosisie(String nouveauGenreChoisie){
        this.genreChoisie = nouveauGenreChoisie;
    }

    /**
     * Retourne la fenêtre principale de l'application.
     *
     * @return La fenêtre principale de l'application.
     */
    public Stage getWindow(){
        return this.stage;
    }

    /**
     * Modifie la fenêtre principale de l'application.
     *
     * @param nouvelleFenetre La nouvelle fenêtre principale de l'application.
     * @throws Exception Si la fenêtre n'est pas valide.
     */
    private void afficheClassement(FXMLLoader loader) {
        try {
            BorderPane root = (BorderPane) loader.load();
            Scene page = new Scene(root);
            this.stage.setScene(page);
            this.stage.show();

            // Récupérer les classements depuis la base de données
            BdTriSLN bd = new BdTriSLN(new ConnexionMySQL("servinfo-maria", "DBguihard", "guihard", "guihard"));
            //List<Classement> classements = bd.getClassements(this.categorieChoisie, this.genreChoisie);

            // Ajouter les classements au TableView
            //ObservableList<Classement> data = FXCollections.observableArrayList(classements);
            //this.classement.setItems(data);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Met à jour les classements affichés.
     *
     * @param categorie La catégorie choisie.
     * @param genre Le genre choisi.
     */
    public void updateClassement(String categorie, String genre){
        this.categorieChoisie = categorie;
        this.genreChoisie = genre;
    }
}