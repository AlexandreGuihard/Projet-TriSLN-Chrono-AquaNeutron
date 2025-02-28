package com.trisln.aquaneutron.controleurs;

import javafx.event.EventHandler;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import java.sql.SQLException;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import com.trisln.aquaneutron.vue.TriSLN;
import java.util.List;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import com.trisln.aquaneutron.modele.Course;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;

/**
 * Classe du controleur de la page d'affichage des courses.
 */
public class ControleurBoutonsCourses extends ControleurBoutons implements EventHandler<ActionEvent> {
    private TriSLN vue;

    @FXML
    private Button btnNvlCourse;
    @FXML
    private Button btnDemarrerCourse;
    @FXML
    private Button btnAccueil;
    @FXML
    private Button btnRetour;
    @FXML
    private Button btnDeconnexion;
    @FXML
    private Button btnCompte;
    @FXML
    private TableView<Course> tableViewCourses;

    /**
     * Constructeur de la classe.
     * @param vue la vue
     */
    public ControleurBoutonsCourses(TriSLN vue) {
        super();
        this.setBoutons(vue);
    }

    /**
     * Initialisation des boutons grâce à la classe parente ControleurBoutons
     * @param vue la vue
     */
    private void setBoutons(TriSLN vue) {
        super.setVue(vue);
        super.setBAccueil(btnAccueil);
        super.setBCompte(btnCompte);
        super.setBDeconnexion(btnDeconnexion);
        super.setBRetour(btnRetour);
    }

    /**
     * Initialise le tableau d'affichage des courses avec l'ensemble des courses.
     */
    @FXML
    public void initialize() {
        if(tableViewCourses!= null){
            TableColumn<Course, Integer> colId = new TableColumn<>("Id");
            colId.setCellValueFactory(cellData -> {
                return new SimpleIntegerProperty(cellData.getValue().getId()).asObject();
            });
            TableColumn<Course, String> colNom = new TableColumn<>("Nom");
            colNom.setCellValueFactory(cellData -> {
                return new SimpleStringProperty(cellData.getValue().getNom());
            });
            TableColumn<Course, String> colFormat = new TableColumn<>("Format");
            colFormat.setCellValueFactory(cellData -> {
                return new SimpleStringProperty(cellData.getValue().getFormat());
            });
            TableColumn<Course, String> colCategorie = new TableColumn<>("Catégories");
            colCategorie.setCellValueFactory(cellData -> {
                return new SimpleStringProperty(cellData.getValue().getCategorie());
            });
            TableColumn<Course, String> colDepart = new TableColumn<>("Départ");
            colDepart.setCellValueFactory(cellData -> {
                return new SimpleStringProperty(cellData.getValue().getHeureDepart());
            });
            this.tableViewCourses.getColumns().setAll(colId, colNom, colFormat, colCategorie, colDepart);
            try {
                List<Course> listeCourses = TriSLN.getBd().getCourses();
                this.tableViewCourses.setItems(FXCollections.observableArrayList(listeCourses));
            } catch (SQLException e) {
                System.err.println("Erreur lors de la récupération des courses: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * Gère l'affichage des boutons si la souris en survole un.
     * @param event l'évenement de la souris qui survole un élément
     */
    @FXML
    public void handleBtnCoursesMouseEntered(MouseEvent event) {
        try {
            Button btn = (Button) event.getSource();
            if (btn.getId().equals("btnNvlCourse")) {
                super.getVue().changeButtonColor(this.btnNvlCourse, "#105c74", "");
            } else {
                if (btn.getId().equals("btnDemarrerCourse")) {
                    super.getVue().changeButtonColor(this.btnDemarrerCourse, "#105c74", "");
                }
                else{
                    super.handleBtnsMouseEntered(btn);
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur");
            e.printStackTrace();
        }
    }

    /**
     * Gère l'affichage des boutons si la souris quitte un élément.
     * @param event l'évenement de la souris qui quitte un élément
     */
    @FXML
    public void handleBtnCoursesMouseExited(MouseEvent event) {
        try {
            Button btn = (Button) event.getSource();
            if (btn.getId().equals("btnNvlCourse")) {
                super.getVue().changeButtonColor(this.btnNvlCourse, "#2596BE", "");
            } else {
                if (btn.getId().equals("btnDemarrerCourse")) {
                    super.getVue().changeButtonColor(this.btnDemarrerCourse, "#2596BE", "");
                } else {
                    super.handleBtnsMouseExited(btn);
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur");
            e.printStackTrace();
        }
    }

    /**
     * Gère l'utilisation des boutons lorsque l'on clique sur l'un d'eux.
     * @param event l'évenement de la souris qui clique sur un bouton
     */
    @Override
    public void handle(ActionEvent event) {
        try {
            Button btn = (Button) event.getSource();
            if (btn.getId().equals("btnNvlCourse")) {
                super.getVue().afficheNvlCourse();
            }else if(btn.getId().equals("btnDemarrerCourse")){
                Course course = tableViewCourses.getSelectionModel().getSelectedItem();
                if (course!=null) {
                    super.getVue().afficheDemarerCourse(course);   
                }
            }
            else{
                super.handle(btn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}