package src.vue;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;

public class FenetreParticipant{
    private Stage stage;
    private BorderPane root;
    private List<Button> categories;
    private ComboBox<String> sousCategories;
    private String categorieChoisie;

    public FenetreParticipant(FXMLLoader loader, Stage stage){
        this.stage=stage;
        this.root=new BorderPane();
        this.categories = new ArrayList<>();
        this.sousCategories = new ComboBox<>();
        this.categorieChoisie = "";
        this.afficheCategories(loader);
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

    private void afficheCategories(FXMLLoader loader){
        try{
            this.root=(BorderPane)loader.load();
            this.stage.setScene(new Scene(this.root));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void popUpSeniors(){
        try {
            Stage StageVue = new Stage();
            BorderPane root = FXMLLoader.load(getClass().getResource("SAEprojetPopUpSenior.fxml"));
            Scene popUp;
            popUp = new Scene(root);
            StageVue.setScene(popUp);
            StageVue.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void popUpVeterans(){
        try {
            Stage StageVue = new Stage();
            BorderPane root = FXMLLoader.load(getClass().getResource("SAEprojetPopUpVeteran.fxml"));
            Scene popUp;
            popUp = new Scene(root);
            StageVue.setScene(popUp);
            StageVue.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void afficheParticipants(){
        if ("Seniors".equals(categorieChoisie)) {
            popUpSeniors();
        } else if ("Veterans".equals(categorieChoisie)) {
            popUpVeterans();
        }
    }
}