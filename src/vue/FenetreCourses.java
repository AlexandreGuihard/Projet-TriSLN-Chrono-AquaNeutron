import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CheckBox;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;

public class FenetreCourses{

    private TextField tfDate;
    private TableView<Course> prochainesCourses;
    private Button bNouvelleCourse;
    private Button bAjouterCourse;
    private Button bDepart;
    private Button bFinCourse;
    private ComboBox<String> formats;
    private Set<CheckBox> categories;
    private Set<CheckBox> categoriesChoisies;
    private TextField nom;
    private TextField date;
    private TextField heure;
    private List<Integer> dossards;
    private List<Integer> dossardsPartis;
    private List<Integer> dossardsArrives;

    Scene mainScene;

    FXMLLoader loader;

    BorderPane acceuill;
    
    @FXML
    private Button btnHello;



    
    public FenetreCourses(){

     
        this.dossards = new ArrayList<>();
        this.dossardsPartis = new ArrayList<>();
        this.dossardsArrives = new ArrayList<>();
    }

    public TextField getTfDate(){
        return this.tfDate;
    }

    public void setTfDate(TextField date){
        this.tfDate = date;
    }

    public TableView<> getProchainesCourses(){
        return this.prochainesCourses;
    }

    public void SetProchainesCourses(TableView<> courses){
        this.prochainesCourses = courses;
    }
    
    public Button getBNouvelleCourse(){
        return this.bNouvelleCourse;
    }

    public void setBNouvelleCourse(Button nouvelleCourse){
        this.bNouvelleCourse = nouvelleCourse;
    }

    public Button getBAjouterCourse(){
        return this.bAjouterCourse;
    }

    public void setBAjouterCourse(Button courseAjoute){
        this.bAjouterCourse = courseAjoute;
    }

    public Button getBDepart(){
        return this.bDepart;
    }

    public Button setBDepart(Button boutonDepart){
        this.bDepart = boutonDepart
    }

    public Button getBFinCourse(){
        return this.bFinCourse;
    }

    public ComboBox<> getFormats(){
        return this.formats;
    }

    public HashSet<> getCategories(){
        return this.categories;
    }

    public HashSet<> getCategoriesChoisies(){
        return this.categoriesChoisies;
    }

    public TextField getNom(){
        return this.nom;
    }

    public TextField getDate(){
        return this.date;
    }

    public TextField getHeure(){
        return this.heure;
    }

    public ArrayList<> getDossards(){
        return this.dossards;
    }
    
    public ArrayList<> getDossardsPartis(){
        return this.dossardsPartis;
    }

    public ArrayList<> getDossardsArrives(){
        return this.dossardsArrives;
    }
}


public void initRootLayout() {
    
    // Load root layout from fxml file.
    FXMLLoader loader = new FXMLLoader(getClass().getResource("src/SAEprojetAccueil.fxml"));
    BorderPane acceuill =  (BorderPane) loader.load();
    Scene scene = new Scene(acceuill);

    
     
}

private void afficheCourses(){}


public TextField getTfDate(){
    
}

public void updateCourses(String date){}

public void nouvelleCourse(){}


public void chronometre(){}




public Alert popUpAlert(){
    Alert alert = new Alert(Alert.AlertType.ERROR,"Vous ne pouvez pas crée ceci\n assurer vous bien de remplir tout les champs", ButtonType.OK);
    alert.setTitle("Attention");
    return alert;
}
