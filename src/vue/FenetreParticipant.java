package src.vue;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.controleurs.ControleurBoutonsParticipants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import java.io.File;
import java.io.IOException;

public class FenetreParticipant{
    private Stage stage;
    private Stage popUpStage;
    private BorderPane root;
    private List<Button> categories;
    private ComboBox<String> sousCategories;
    private String categorieChoisie;
    private FXMLLoader loader;

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

    public List<Button> getCategories() {
        return categories;
    }

    public void setCategories(List<Button> categories) {
        this.categories = categories;
    }

    public ComboBox<String> getSousCategories() {
        return sousCategories;
    }

    public void setSousCategories(ComboBox<String> sousCategories) {
        this.sousCategories = sousCategories;
    }

    public String getCategorieChoisie() {
        return categorieChoisie;
    }

    public void setCategorieChoisie(String categorieChoisie) {
        this.categorieChoisie = categorieChoisie;
    }

    public Stage getStage(){
        return stage;
    }

    public Stage getPopUpStage(){
        return popUpStage;
    }

    public FXMLLoader getLoader(){
        return loader;
    }

    public void setLoader(FXMLLoader loader){
        this.loader=loader;
    }

    private void afficheCategories(){
        try{
            this.root=(BorderPane)this.loader.load();
            this.stage.setScene(new Scene(this.root));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void popUpSeniors(FXMLLoader loader){
        try {
            this.root = (BorderPane)loader.load();
            this.popUpStage.setScene(new Scene(this.root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void popUpVeterans(FXMLLoader loader){
        try {
            this.root = (BorderPane)loader.load();
            this.popUpStage.setScene(new Scene(this.root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void afficheParticipants(FXMLLoader loader){
        //TODO: Afficher selon la catégorie choisie
        try{
            this.root=(BorderPane)loader.load();
            this.stage.setScene(new Scene(this.root));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}