package com.trisln.aquaneutron.controleurs;

import com.trisln.aquaneutron.vue.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import com.trisln.aquaneutron.vue.TriSLN;
import javafx.event.EventHandler;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private List<Integer> lesArrives;
    private ObservableList<Object[]> arrivalList;
    @FXML
    private Button btnAccueil;
    @FXML
    private Button btnRetour;
    @FXML
    private Button btnDeconnexion;
    @FXML
    private Button btnCompte;
    @FXML
    private Button btnDossardArrive;   
    @FXML
    private TextField numeroDossard;
    @FXML
    private TableView<Course> tableViewDossards;
    @FXML
    private TableView<Object[]> tableViewArrive;
    
    public ControleurBoutonsDebutCourse(TriSLN vue, Course course){
        super();
        this.course = course;
        this.setBoutons(vue);
        this.dossardsArrives = 0;
        this.lesArrives = new ArrayList<>();
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
        if (tableViewArrive != null) {
            TableColumn<Object[], Integer> colDossard = new TableColumn<>("Dossards");
            colDossard.setCellValueFactory(cellData -> new SimpleIntegerProperty((Integer) cellData.getValue()[0]).asObject());
            TableColumn<Object[], Integer> colTopDepart = new TableColumn<>("Top départ");
            colTopDepart.setCellValueFactory(cellData -> new SimpleIntegerProperty((Integer) cellData.getValue()[1]).asObject());
            TableColumn<Object[], Integer> colTopArrivee = new TableColumn<>("Top arrivée");
            colTopArrivee.setCellValueFactory(cellData -> new SimpleIntegerProperty((Integer) cellData.getValue()[2]).asObject());
            TableColumn<Object[], Integer> colChrono = new TableColumn<>("Chrono");
            colChrono.setCellValueFactory(cellData -> new SimpleIntegerProperty((Integer) cellData.getValue()[3]).asObject());
            TableColumn<Object[], String> colSexe = new TableColumn<>("Sexe");
            colSexe.setCellValueFactory(cellData -> new SimpleStringProperty((String) String.valueOf(cellData.getValue()[4])));
            tableViewArrive.getColumns().setAll(colDossard, colTopDepart, colTopArrivee, colChrono, colSexe);
            arrivalList = FXCollections.observableArrayList();
            tableViewArrive.setItems(arrivalList);
        }
    }

    public int getDossardsArrives(){
        return this.dossardsArrives;
    }

    public void setDossardsArrives(int lesDossardsArrives){
        this.dossardsArrives = lesDossardsArrives;
    }

    public void enregistrerArrive() throws SQLException {
        int leDossard = Integer.parseInt(this.numeroDossard.getText());
        if (TriSLN.getBd().isParticipantOfCourse(leDossard, course) && !lesArrives.contains(leDossard)){
            lesArrives.add(leDossard);        
            System.out.println("Dossard " + leDossard + " est arrivé.");
            Participant participant = TriSLN.getBd().getParticipantByDossard(leDossard);
            arrivalList.add(new Object[]{leDossard, 0, 100, 10, participant.getSexe()});
            tableViewArrive.setItems(arrivalList);
            dossardsArrives += 1;
            tableViewDossards.refresh();
        } else {
            System.out.println("Le dossard ne peut pas être en course");
        }
    }

    @FXML
    public void handleBtnDemarrerCoursesMouseEntered(MouseEvent event) {
        try {
                Button btn = (Button) event.getSource();
                if(btn.getId().equals("btnDossardArrive")){
                    super.getVue().changeButtonColor(this.btnDossardArrive, "#105c74", "");
                }
                else{
                    super.handleBtnsMouseEntered(btn);
                }
        } catch (Exception e) {
            System.err.println("Erreur");
            e.printStackTrace();
        }
    }


    @FXML
    public void handleBtnDemarrerCoursesMouseExited(MouseEvent event){
        try {
            Button btn = (Button) event.getSource();
            if(btn.getId().equals("btnDossardArrive")){
                super.getVue().changeButtonColor(this.btnDossardArrive, "#2596BE", "");
            } else {
                super.handleBtnsMouseExited(btn);
            }
        } catch (Exception e) {
            System.err.println("Erreur");
            e.printStackTrace();
        }
    }
    
    @Override
    public void handle(ActionEvent event) {
        try {
            Button btn = (Button) event.getSource();
            if (btn.getId().equals("btnDossardArrive")) {
                enregistrerArrive();
            } else {
                super.handle(btn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}