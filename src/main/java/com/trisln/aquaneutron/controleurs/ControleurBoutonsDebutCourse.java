package com.trisln.aquaneutron.controleurs;

import com.trisln.aquaneutron.vue.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import com.trisln.aquaneutron.vue.TriSLN;
import javafx.event.EventHandler;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import com.trisln.aquaneutron.modele.Course;
import com.trisln.aquaneutron.modele.Participant;

public class ControleurBoutonsDebutCourse extends ControleurBoutons implements EventHandler<ActionEvent> {

    private Course course;
    private TriSLN vue;
    private int dossardsArrives;
    @FXML
    private Button btnAccueil;
    @FXML
    private Button btnRetour;
    @FXML
    private Button btnDeconnexion;
    @FXML
    private Button btnCompte;
    @FXML
    private TableView<Course> tableViewDossards;

    public ControleurBoutonsDebutCourse(TriSLN vue, Course course){
        super();
        this.course = course;
        this.setBoutons(vue);
        this.dossardsArrives = 0;
    }

    private void setBoutons(TriSLN vue) {
        super.setVue(vue);
        super.setBAccueil(btnAccueil);
        super.setBCompte(btnCompte);
        super.setBDeconnexion(btnDeconnexion);
        super.setBRetour(btnRetour);
    }

    @FXML
    public void initialize() {
        if (tableViewDossards != null) {
            TableColumn<Course, Integer> colPartis = new TableColumn<>("Dossards partis");
            colPartis.setCellValueFactory(cellData ->{
                try {
                    List<Participant> participants = TriSLN.getBd().getParticipantsACourse(this.course.getId());
                    return new SimpleIntegerProperty(participants.size()).asObject();
                }
                catch (SQLException e){
                    e.printStackTrace();
                    return new SimpleIntegerProperty(0).asObject();
                }
            });
            TableColumn<Course, Integer> colArrives = new TableColumn<>("Dossards arrivés");
            colArrives.setCellValueFactory(cellData ->{
                return new SimpleIntegerProperty(dossardsArrives).asObject();
            });
            TableColumn<Course, Integer> colRestant = new TableColumn<>("Dossards restants");
            colRestant.setCellValueFactory(cellData ->{
                try {
                    int partis = TriSLN.getBd().getParticipantsACourse(this.course.getId()).size();
                    return new SimpleIntegerProperty(partis - dossardsArrives).asObject();
                } 
                catch (SQLException e){
                    e.printStackTrace();
                    return new SimpleIntegerProperty(0).asObject();
                }
            });
            this.tableViewDossards.getColumns().setAll(colPartis, colArrives, colRestant);
            ObservableList<Course> courseList = FXCollections.observableArrayList(this.course);
            this.tableViewDossards.setItems(courseList);
        }
    }



    public int getDossardsArrives(){
        return this.dossardsArrives;
    }

    public void setDossardsArrives(int lesDossardsArrives){
        this.dossardsArrives = lesDossardsArrives;
    }

    @FXML
    public void handleBtnDemarrerCoursesMouseEntered(MouseEvent event) {
        try {
                Button btn = (Button) event.getSource();
                super.handleBtnsMouseEntered(btn);
        } catch (Exception e) {
            System.err.println("Erreur");
            e.printStackTrace();
        }
    }


    @FXML
    public void handleBtnDemarrerCoursesMouseExited(MouseEvent event){
        try {
            Button btn = (Button) event.getSource();
            super.handleBtnsMouseExited(btn);
        } catch (Exception e) {
            System.err.println("Erreur");
            e.printStackTrace();
        }
    }
    
    @Override
    public void handle(ActionEvent event) {
        try {
            Button btn = (Button) event.getSource();
            super.handle(btn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
