package com.trisln.aquaneutron.controleurs;

import javafx.event.EventHandler;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import com.trisln.aquaneutron.vue.FenetreCourses;
import com.trisln.aquaneutron.vue.TriSLN;


public class ControleurBoutonsNouvelleCourses extends ControleurBoutons implements EventHandler<ActionEvent> {
    private TriSLN vue;

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
    private TextField nomCourse;
    @FXML
    private MenuButton formatCourse;
    @FXML
    private TextField heureCourse;
    @FXML
    private CheckBox idMinPoussins;
    @FXML
    private CheckBox idBenjamins;
    @FXML
    private CheckBox idPoussins;
    @FXML
    private CheckBox idMinimes;
    @FXML
    private CheckBox idPupilles;
    @FXML
    private CheckBox idCadets;
    @FXML
    private CheckBox idJunoirs;
    @FXML
    private CheckBox idSeniors;
    @FXML
    private CheckBox idVetran;



    public ControleurBoutonsNouvelleCourses(TriSLN vue){
        super();
        this.setBoutons(vue);
    }

    private void setBoutons(TriSLN vue){
        super.setVue(vue);
        super.setBAccueil(btnAccueil);
        super.setBCompte(btnCompte);
        super.setBDeconnexion(btnDeconnexion);
        super.setBRetour(btnRetour);
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

    @Override
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
}