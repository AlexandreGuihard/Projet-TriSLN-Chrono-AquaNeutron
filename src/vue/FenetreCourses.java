package src.vue;

import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import src.modele.Chronometrage;
import src.modele.Course;
import javafx.fxml.FXMLLoader;

public class FenetreCourses {

    private TextField tfDate;
    private TableView<ObservableList<Course>> prochainesCourses;
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

    public FenetreCourses(FXMLLoader loader) {

        this.tfDate = new TextField();
        this.prochainesCourses = new TableView<>();
        this.bNouvelleCourse = new Button();
        this.bAjouterCourse = new Button();
        this.bDepart = new Button();
        this.bFinCourse = new Button();
        this.formats = new ComboBox<>();
        this.categories = new HashSet<>();
        this.categoriesChoisies = new HashSet<>();
        this.nom = new TextField();
        this.date = new TextField();
        this.heure = new TextField();
        this.dossards = new ArrayList<>();
        this.dossardsPartis = new ArrayList<>();
        this.dossardsArrives = new ArrayList<>();
    }

    public TextField getTfDate() {
        return this.tfDate;
    }

    public void setTfDate(TextField date) {
        this.tfDate = date;
    }

    public TableView<ObservableList<Course>> getProchainesCourses() {
        return this.prochainesCourses;
    }

    public void setProchainesCourses(TableView<ObservableList<Course>> courses) {
        this.prochainesCourses = courses;
    }

    public Button getBNouvelleCourse() {
        return this.bNouvelleCourse;
    }

    public void setBNouvelleCourse(Button nouvelleCourse) {
        this.bNouvelleCourse = nouvelleCourse;
    }

    public Button getBAjouterCourse() {
        return this.bAjouterCourse;
    }

    public void setBAjouterCourse(Button courseAjoute) {
        this.bAjouterCourse = courseAjoute;
    }

    public Button getBDepart() {
        return this.bDepart;
    }

    public void setBDepart(Button boutonDepart) {
        this.bDepart = boutonDepart;
    }

    public Button getBFinCourse() {
        return this.bFinCourse;
    }

    public void setBFinCourse(Button boutonFin) {
        this.bFinCourse = boutonFin;
    }

    public ComboBox<String> getFormats() {
        return this.formats;
    }

    public void setFormats(ComboBox<String> nouveauxFormats) {
        this.formats = nouveauxFormats;
    }

    public Set<CheckBox> getCategories() {
        return this.categories;
    }

    public void setCategories(Set<CheckBox> nouvellesCategories) {
        this.categories = nouvellesCategories;
    }

    public Set<CheckBox> getCategoriesChoisies() {
        return this.categoriesChoisies;
    }

    public void setCategoriesChoisies(Set<CheckBox> nouvellesCategoriesChoisies) {
        this.categoriesChoisies = nouvellesCategoriesChoisies;
    }

    public TextField getNom() {
        return this.nom;
    }

    public void setNom(TextField nouveauNom) {
        this.nom = nouveauNom;
    }

    public TextField getDate() {
        return this.date;
    }

    public void setDate(TextField nouvelleDate) {
        this.date = nouvelleDate;
    }

    public TextField getHeure() {
        return this.heure;
    }

    public void setHeure(TextField nouvelleHeure) {
        this.heure = nouvelleHeure;
    }

    public List<Integer> getDossards() {
        return this.dossards;
    }

    public void setDossards(List<Integer> nouveauxDossards) {
        this.dossards = nouveauxDossards;
    }

    public List<Integer> getDossardsPartis() {
        return this.dossardsPartis;
    }

    public void setDossardsPartis(List<Integer> nouveauxDossardsPartis) {
        this.dossardsPartis = nouveauxDossardsPartis;
    }

    public List<Integer> getDossardsArrives() {
        return this.dossardsArrives;
    }

    public void setDossardsArrives(List<Integer> nouveauxDossardsArrives) {
        this.dossardsArrives = nouveauxDossardsArrives;
    }



    private void afficheCourses(){
        try {
            Stage StageVue = new Stage();
            BorderPane root = FXMLLoader.load(getClass().getResource("SAEprojetGererCourses.fxml"));
            Scene page;
            page = new Scene(root);
            StageVue.setScene(page);
            StageVue.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCourses(String date){

    }

    public void nouvelleCourse(){
        try {
            Stage StageVue = new Stage();
            BorderPane root = FXMLLoader.load(getClass().getResource("SAEprojetNouvelleCourse.fxml"));
            Scene page;
            page = new Scene(root);
            StageVue.setScene(page);
            StageVue.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chronometre(){
        Chronometrage chrono = new Chronometrage();



    }
}