package vue;

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

public class FenetreParticipant extends Application {
    private List<Button> categories;
    private ComboBox<String> sousCategories;
    private String categorieChoisie;

    public FenetreParticipant(){
        this.categories = new ArrayList<>();
        this.sousCategories = new ComboBox<String>();
        this.categorieChoisie = "";
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

    private void afficheCategories(){
        categories.add(new Button("Seniors"));
        categories.add(new Button("Veterans"));
        
        for (Button category : categories) {
            category.setOnAction(e -> {
                categorieChoisie = category.getText();
                afficheParticipants();
            });
        }
    }

    public void popUpSeniors(){
        try {
            Stage StageVue = new Stage();
            BorderPane root = FXMLLoader.load(getClass().getResource("SAEprojetPopUpSenior.fxml"));
            Scene popUp;
            popUp = new Scene(root);
            StageVue.setScene(new Scene(popUp));
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
            StageVue.setScene(new Scene(popUp));
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

    @Override
    public void start(Stage primaryStage) {
        afficheCategories();
        VBox layout = new VBox(10);
        layout.getChildren().addAll(categories);
        layout.getChildren().add(sousCategories);
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
