package com.trisln.aquaneutron.controleurs;

import javafx.event.EventHandler;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import com.trisln.aquaneutron.vue.TriSLN;

import java.util.Set;

public class ControleurBoutonsNouvelleCourses extends ControleurBoutons implements EventHandler<ActionEvent> {
    private TriSLN vue;

    @FXML
    private TextField nomCourse;
    @FXML
    private ComboBox<String> formatCourse;
    @FXML
    private Button btnAjoutCourse;
    @FXML
    private Button btnAccueil;
    @FXML
    private Button btnRetour;
    @FXML
    private Button btnDeconnexion;
    @FXML
    private Button btnCompte;
    @FXML
    private RadioButton idMinPoussins;
    @FXML
    private RadioButton idPoussins;
    @FXML
    private RadioButton idPupilles;
    @FXML
    private RadioButton idBenjamins;
    @FXML
    private RadioButton idMinimes;
    @FXML
    private RadioButton idCadets;
    @FXML
    private RadioButton idJunoirs;
    @FXML
    private RadioButton idSeniors;
    @FXML
    private RadioButton idVetran;
    private ToggleGroup toggleGroup;
    @FXML
    private TextField heureCourse;
  

    public ControleurBoutonsNouvelleCourses(TriSLN vue){
        super();
        this.setBoutons(vue);
    }

    public void initialize() {
        this.toggleGroup = this.idMinPoussins.getToggleGroup();

        this.nomCourse.textProperty().addListener((observable, oldValue, newValue) -> {
            this.changeEtatBouton();
        });
    }

    private void setBoutons(TriSLN vue){
        super.setVue(vue);
        super.setBAccueil(btnAccueil);
        super.setBCompte(btnCompte);
        super.setBDeconnexion(btnDeconnexion);
        super.setBRetour(btnRetour);
    }

    @FXML
    public void onKeyReleased(KeyEvent event) {
        KeyCode keyCode = event.getCode();
        TextField textField = (TextField) event.getSource();
        if (textField.getId().equals("heureCourse")) {
            this.changeEtatBouton();
        }
    }

    @FXML
    public void handleBtnCoursesMouseEntered(MouseEvent event){
        try{
            Button btn=(Button)event.getSource();
            if(btn.getId().equals("btnAjoutCourse")){
                super.getVue().changeButtonColor(this.btnAjoutCourse, "#105c74", "");
            }
            else{
                super.handleBtnsMouseEntered(btn);
            }
        }
        catch(Exception e){
            System.err.println("Erreur");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleBtnCoursesMouseExited(MouseEvent event){
        try{
            Button btn=(Button)event.getSource();
            if(btn.getId().equals("btnAjoutCourse")){
                super.getVue().changeButtonColor(this.btnAjoutCourse, "#2596BE", "");
            }
            else{
                super.handleBtnsMouseExited(btn);
            }
        }
        catch(Exception e){
            System.err.println("Erreur");
            e.printStackTrace();
        }
    }

    public void handleRB(ActionEvent event) {
        this.changeEtatBouton();
    }

    @FXML
    public void handleCB(ActionEvent event) {
        this.changeEtatBouton();
    }

    public void handle(ActionEvent event){
      try {
            Button btn=(Button) event.getSource();
            if(btn.getId().equals("btnAjoutCourse")){
                String nom = this.nomCourse.getText();
                // La récupération de la catégorie est à refaire au niveau du FXML
                // Que fait-on avec la date ?
                String heure = this.heureCourse.getText();
                String categorie = "";
                if (this.idMinPoussins.isSelected()) {
                    categorie = "MP";
                }
                if (this.idBenjamins.isSelected()) {
                    categorie = "BE";
                }
                if (this.idPoussins.isSelected()) {
                    categorie = "PO";
                }
                if (this.idMinimes.isSelected()) {
                    categorie = "MI";
                }
                if (this.idPupilles.isSelected()) {
                    categorie = "PU";
                }
                if (this.idCadets.isSelected()) {
                    categorie = "CA";
                }
                if (this.idJunoirs.isSelected()) {
                    categorie = "JU";
                }
                if (this.idSeniors.isSelected()) {
                    categorie = "Senior";
                }
                if (this.idVetran.isSelected()) {
                    categorie = "Veteran";
                }
                try {
                    super.getVue().getBd().ajouterCourse(nom, "XS", categorie , heure, 1);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                super.getVue().afficheCourses();
                System.out.println("Course ajoutée");
            }
            else{
                super.handle(btn);
            }
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        } 
    }

    private void changeEtatBouton() {
        boolean nomVide = this.nomCourse.getText().isEmpty();
        boolean formatVide = (this.formatCourse.getValue() == null);
        boolean categorieVide = (this.toggleGroup.getSelectedToggle() == null);
        boolean heureRespectRegex = !(this.heureCourse.getText().matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$"));

        this.btnAjoutCourse.setDisable(nomVide || formatVide || categorieVide || heureRespectRegex);
    }
}