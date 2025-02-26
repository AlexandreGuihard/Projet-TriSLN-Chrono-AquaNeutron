package com.trisln.aquaneutron.vue;

import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.Set;
import javafx.fxml.FXMLLoader;

import com.trisln.aquaneutron.modele.*;
import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import com.trisln.aquaneutron.bd.BdTriSLN;
import com.trisln.aquaneutron.bd.ConnexionMySQL;

public class FenetreClassements {

    private TableView<Classement> classement;
    private ComboBox<String> souscategories;
    private String categorieChoisie;
    private ComboBox<String> genre;
    private String genreChoisie;
    private Stage stage;

    public FenetreClassements(FXMLLoader loader, Stage stage){
        this.classement = new TableView<>();

        // Ajouter les colonnes au TableView
        TableColumn<Classement, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Classement, Integer> colPosGeneral = new TableColumn<>("Position Générale");
        colPosGeneral.setCellValueFactory(new PropertyValueFactory<>("posGeneral"));

        TableColumn<Classement, String> colPosCategorie = new TableColumn<>("Position Catégorie");
        colPosCategorie.setCellValueFactory(new PropertyValueFactory<>("posCategorie"));

        TableColumn<Classement, Integer> colPosClub = new TableColumn<>("Position Club");
        colPosClub.setCellValueFactory(new PropertyValueFactory<>("posClub"));

        TableColumn<Classement, String> colTemps = new TableColumn<>("Temps");
        colTemps.setCellValueFactory(new PropertyValueFactory<>("temps"));

        TableColumn<Classement, Participant> colParticipant = new TableColumn<>("Participant");
        colParticipant.setCellValueFactory(new PropertyValueFactory<>("participant"));

        this.classement.getColumns().addAll(colId, colPosGeneral, colPosCategorie, colPosClub, colTemps, colParticipant);

        this.souscategories = new ComboBox<>();
        this.categorieChoisie = "";
        this.genre = new ComboBox<>();
        this.genreChoisie = "";
        this.stage = stage;
        this.afficheClassement(loader);
    }

    public TableView<Classement> getClassement(){
        return this.classement;
    }

    public void setClassement(TableView<Classement> nouveauClassement){
        this.classement = nouveauClassement;
    }

    public ComboBox<String> getSousCategorie() {
        return this.souscategories;
    }

    public void setCategories(ComboBox<String> nouvellesSousCategories) {
        this.souscategories = nouvellesSousCategories;
    }

    public String getCategorieChoisie(){
        return this.categorieChoisie;
    }

    public void setCategoriesChoisies(String nouvellesCategoriesChoisies){
        this.categorieChoisie = nouvellesCategoriesChoisies;
    }

    public ComboBox<String> getGenre(){
        return this.genre;
    }

    public void setGenre(ComboBox<String> nouveauGenre){
        this.genre = nouveauGenre;
    }

    public String getGenreChoisie(){
        return this.genreChoisie;
    }

    public void setGenreChosisie(String nouveauGenreChoisie){
        this.genreChoisie = nouveauGenreChoisie;
    }

    public Stage getWindow(){
        return this.stage;
    }

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

    public void updateClassement(String categorie, String genre){
        this.categorieChoisie = categorie;
        this.genreChoisie = genre;
    }
}