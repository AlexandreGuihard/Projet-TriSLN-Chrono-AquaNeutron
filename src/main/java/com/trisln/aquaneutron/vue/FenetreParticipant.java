package com.trisln.aquaneutron.vue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import com.trisln.aquaneutron.controleurs.ControleurBoutonsParticipants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;

public class FenetreParticipant{
    private Stage stage;
    private Stage popUpStage;
    private BorderPane root;
    private List<Button> categories;
    private ComboBox<String> sousCategories;
    private String categorieChoisie;
    private FXMLLoader loader;

    /**
     * Constructeur de la classe
     * @param loader le loader du fichier fxml
     * @param stage la fenêtre
     */
    public FenetreParticipant(FXMLLoader loader, Stage stage){
        this.stage=stage;
        this.popUpStage=new Stage();
        this.root=new BorderPane();
        this.categories = new ArrayList<>();
        this.sousCategories = new ComboBox<>();
        this.categorieChoisie = "";
        this.loader=loader;
        this.afficheCategories();
    }

    /**
     * Getter des boutons des catégories
     * @return la liste des boutons des catégories
     */
    public List<Button> getCategories() {
        return categories;
    }

    /**
     * Setter des boutons des catégories
     * @param categories la liste des catégories
     */
    public void setCategories(List<Button> categories) {
        this.categories = categories;
    }

    /**
     * Getter des sous catégories
     * @return le combobox des sous catégories
     */
    public ComboBox<String> getSousCategories() {
        return sousCategories;
    }

    /**
     * Setter des sous catégories
     * @param sousCategories le combobox des sous catégories
     */
    public void setSousCategories(ComboBox<String> sousCategories) {
        this.sousCategories = sousCategories;
    }

    /**
     * Getter de la catégorie choisie
     * @return la catégorie choisie
     */
    public String getCategorieChoisie() {
        return categorieChoisie;
    }

    /**
     * Setter de la catégorie choisie
     * @param categorieChoisie la catégorie choisie
     */
    public void setCategorieChoisie(String categorieChoisie) {
        this.categorieChoisie = categorieChoisie;
    }

    /**
     * Getter de la fenêtre
     * @return la fenêtre
     */
    public Stage getStage(){
        return stage;
    }

    /**
     * Getter de la fenêtre des popup
     * @return la fenêtre des popup
     */
    public Stage getPopUpStage(){
        return popUpStage;
    }

    /**
     * Getter du loader du fxml
     * @return le loader
     */
    public FXMLLoader getLoader(){
        return loader;
    }

    /**
     * Setter du loader
     * @param loader le loader
     */
    public void setLoader(FXMLLoader loader){
        this.loader=loader;
    }

    /**
     * Affiche la page des catégories
     * @throws Exception
     */
    private void afficheCategories(){
        try{
            this.root=(BorderPane)this.loader.load();
            this.stage.setScene(new Scene(this.root));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Affiche la popup des séniors
     * @param loader le loader utilisé
     * @throws Exception
     */
    public void popUpSeniors(FXMLLoader loader){
        try {
            this.root = (BorderPane)loader.load();
            this.popUpStage.setScene(new Scene(this.root));
            this.popUpStage.setTitle("TriSLN");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Affiche la popup des vétérant
     * @param loader le loader utilisé
     * @throws Exception
     */
    public void popUpVeterans(FXMLLoader loader){
        try {
            this.root = (BorderPane)loader.load();
            this.popUpStage.setScene(new Scene(this.root));
            this.popUpStage.setTitle("TriSLN");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Affiche la page des participants
     * @param loader le loader
     * @throws Exception
     */
    public void afficheParticipants(FXMLLoader loader){
        try{
            this.root=(BorderPane)loader.load();
            this.stage.setScene(new Scene(this.root));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Configure le choix de fichiers lors de l'importation des participants
     * @param fileChooser le file chooser
     */
    private static void configureFileChooser(final FileChooser fileChooser){                           
        fileChooser.setTitle("liste des pariticpants");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.setInitialDirectory(new File("./data"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All", "*.csv","*.xls","*.xlsx"),
                new FileChooser.ExtensionFilter("csv", "*.csv"),
                new FileChooser.ExtensionFilter("xls", "*.xls"),
                new FileChooser.ExtensionFilter("xlsx", "*.xlsx")
            );
    }

    /**
     * Getter du participant du file chooser
     * @return le fichier
     */
    public File reccupererParticipant(){
        FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        
        return fileChooser.showOpenDialog(stage);
          
    }


    

}
