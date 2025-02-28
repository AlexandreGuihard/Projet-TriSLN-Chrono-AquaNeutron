package com.trisln.aquaneutron.vue;

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
import com.trisln.aquaneutron.modele.Chronometrage;
import com.trisln.aquaneutron.modele.Course;
import javafx.fxml.FXMLLoader;

/**
 * Classe FenetreCourses.
 * Permet d'afficher la partie Course de l'application.
 */
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
    private Stage stage;

    /**
     * Constructeur de la classe.
     * @param loader Le FXMLLoader utilisé pour charger la vue
     * @param stage La fenêtre principale de l'application
     */
    public FenetreCourses(FXMLLoader loader, Stage stage) {
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
        this.stage= stage;
        this.afficheCourses(loader);
    }

    /**
     * Getter du TextField de la date de création de la course ajoutée
     * @return le TextField
     */
    public TextField getTfDate() {
        return this.tfDate;
    }

    /**
     * Setter de la date de création de la course ajoutée
     * @param date le TextField
     */
    public void setTfDate(TextField date) {
        this.tfDate = date;
    }

    /**
     * Getter de la TableView des courses
     * @return la TableView
     */
    public TableView<ObservableList<Course>> getProchainesCourses() {
        return this.prochainesCourses;
    }

    /**
     * Setter de la TableView des courses
     * @param courses la TableView
     */
    public void setProchainesCourses(TableView<ObservableList<Course>> courses) {
        this.prochainesCourses = courses;
    }

    /**
     * Getter du bouton qui redirige vers la page d'ajout de courses
     * @return le bouton qui redirige vers la page d'ajout de courses
     */
    public Button getBNouvelleCourse() {
        return this.bNouvelleCourse;
    }

    /**
     * Setter du bouton qui redirige vers la page d'ajout de courses
     * @param nouvelleCourse le bouton qui redirige vers la page d'ajout de courses
     */
    public void setBNouvelleCourse(Button nouvelleCourse) {
        this.bNouvelleCourse = nouvelleCourse;
    }

    /**
     * Getter du bouton d'ajout de courses
     * @return le bouton d'ajout de courses
     */
    public Button getBAjouterCourse() {
        return this.bAjouterCourse;
    }

    /**
     * Setter du bouton d'ajout de courses
     * @param courseAjoute le bouton d'ajout de courses
     */
    public void setBAjouterCourse(Button courseAjoute) {
        this.bAjouterCourse = courseAjoute;
    }

    /**
     * Getter du bouton de début de courses
     * @return le bouton de début de courses
     */
    public Button getBDepart() {
        return this.bDepart;
    }

    /**
     * Setter du bouton de début de courses
     * @param boutonDepart bouton de début de courses
     */
    public void setBDepart(Button boutonDepart) {
        this.bDepart = boutonDepart;
    }

    /**
     * Getter du bouton de fin de courses
     * @return le bouton de fin de courses
     */
    public Button getBFinCourse() {
        return this.bFinCourse;
    }

    /**
     * Setter du bouton de fin de courses
     * @param boutonFin bouton de fin de courses
     */
    public void setBFinCourse(Button boutonFin) {
        this.bFinCourse = boutonFin;
    }

    /**
     * Getter de la ComboBox de formats de courses
     * @return de la ComboBox de formats de courses
     */
    public ComboBox<String> getFormats() {
        return this.formats;
    }

    /**
     * Setter de la ComboBox de formats de courses
     * @param nouveauxFormats la ComboBox de formats de courses
     */
    public void setFormats(ComboBox<String> nouveauxFormats) {
        this.formats = nouveauxFormats;
    }

    /**
     * Getter des CheckBox des catégories de courses
     * @return les CheckBox des catégories de courses
     */
    public Set<CheckBox> getCategories() {
        return this.categories;
    }

    /**
     * Setter des CheckBox des catégories de courses
     * @param nouvellesCategories CheckBox des catégories de courses
     */
    public void setCategories(Set<CheckBox> nouvellesCategories) {
        this.categories = nouvellesCategories;
    }

    /**
     * Getter des CheckBox des catégories de courses choisies
     * @return les CheckBox des catégories de courses choisies
     */
    public Set<CheckBox> getCategoriesChoisies() {
        return this.categoriesChoisies;
    }

    /**
     * Setter des CheckBox des catégories de courses choisies
     * @param nouvellesCategoriesChoisies CheckBox des catégories de courses choisies
     */
    public void setCategoriesChoisies(Set<CheckBox> nouvellesCategoriesChoisies) {
        this.categoriesChoisies = nouvellesCategoriesChoisies;
    }

    /**
     * Getter du TextField du nom de la course
     * @return le TextField du nom de la course
     */
    public TextField getNom() {
        return this.nom;
    }

    /**
     * Setter du TextField du nom de la course
     * @param nouveauNom TextField du nom de la course
     */
    public void setNom(TextField nouveauNom) {
        this.nom = nouveauNom;
    }

    /**
     * Getter du TextField de la date de la course
     * @return le TextField de la date de la course
     */
    public TextField getDate() {
        return this.date;
    }

    /**
     * Setter du TextField de la date de la course
     * @param nouvelleDate TextField de la date de la course
     */
    public void setDate(TextField nouvelleDate) {
        this.date = nouvelleDate;
    }

    /**
     * Getter du TextField de l'heure de la course
     * @return le TextField de l'heure de la course
     */
    public TextField getHeure() {
        return this.heure;
    }

    /**
     * Setter du TextField de l'heure de la course
     * @param nouvelleHeure TextField de l'heure de la course
     */
    public void setHeure(TextField nouvelleHeure) {
        this.heure = nouvelleHeure;
    }

    /**
     * Getter de la liste des dossards des participants de la course
     * @return la liste des dossards des participants de la course
     */
    public List<Integer> getDossards() {
        return this.dossards;
    }

    /**
     * Setter de la liste des dossards des participants de la course
     * @param nouveauxDossards liste des dossards des participants de la course
     */
    public void setDossards(List<Integer> nouveauxDossards) {
        this.dossards = nouveauxDossards;
    }

    /**
     * Getter de la liste des dossards des participants de la course qui sont partis
     * @return la liste des dossards des participants de la course qui sont partis
     */
    public List<Integer> getDossardsPartis() {
        return this.dossardsPartis;
    }

    /**
     * Setter de la liste des dossards des participants de la course qui sont partis
     * @param nouveauxDossardsPartis liste des dossards des participants de la course qui sont partis
     */
    public void setDossardsPartis(List<Integer> nouveauxDossardsPartis) {
        this.dossardsPartis = nouveauxDossardsPartis;
    }

    /**
     * Getter de la liste des dossards des participants de la course qui sont arrivés
     * @return la liste des dossards des participants de la course qui sont arrivés
     */
    public List<Integer> getDossardsArrives() {
        return this.dossardsArrives;
    }

    /**
     * Setter de la liste des dossards des participants de la course qui sont arrivés
     * @param nouveauxDossardsArrives liste des dossards des participants de la course qui sont arrivés
     */
    public void setDossardsArrives(List<Integer> nouveauxDossardsArrives) {
        this.dossardsArrives = nouveauxDossardsArrives;
    }

    /**
     * Getter de la fenêtre principale de l'application
     * @return la fenêtre principale de l'application
     */
    public Stage getWindow(){
        return this.stage;
    }

    /**
     * Affichage de la page de lancement de courses
     * @param loader le FXMLLoader utilisé pour charger la vue
     */
    private void afficheCourses(FXMLLoader loader){
        try {
            BorderPane root = (BorderPane) loader.load();
            Scene page;
            page = new Scene(root);
            this.stage.setScene(page);
            this.stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Mise à jour de la date de la courses
     * @param date la date de la courses
     */
    public void updateCourses(String date){

    }

    /**
     * Affichage de la page de création de courses
     * @param loader le FXMLLoader utilisé pour charger la vue
     */
    public void nouvelleCourse(FXMLLoader loader){
        try {
            BorderPane root = (BorderPane) loader.load();
            Scene page;
            page = new Scene(root);
            this.stage.setScene(page);
            this.stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gestion du chronomètre
     */
    public void chronometre(){
        Chronometrage chrono = new Chronometrage();
    }
}