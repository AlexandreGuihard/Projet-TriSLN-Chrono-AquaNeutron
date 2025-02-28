package com.trisln.aquaneutron.controleurs;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import com.trisln.aquaneutron.vue.TriSLN;
import javafx.event.EventHandler;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import com.trisln.aquaneutron.modele.Chronometrage;
import com.trisln.aquaneutron.modele.Course;
import com.trisln.aquaneutron.modele.Participant;
import com.trisln.aquaneutron.modele.Exceptions.ChronoNotStartedException;
import javafx.scene.text.Text;

/**
 * Classe du controleur de la page de lancement d'une course.
 */
public class ControleurBoutonsDebutCourse extends ControleurBoutons implements EventHandler<ActionEvent> {

    private Course course;
    private TriSLN vue;
    private int dossardsArrives;
    private List<Integer> lesArrives;
    private ObservableList<Object[]> arrivalList;
    private Chronometrage chrono;
    private int tempsDiffere;
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
    private Button btnTopDepart;
    @FXML
    private Button btnTopStop;
    @FXML
    private Button btnFinCourse;
    @FXML
    private TextField numeroDossard;
    @FXML
    private TextField tempsDiff;
    @FXML
    private Text infoDossardArr;
    @FXML
    private TableView<Course> tableViewDossards;
    @FXML
    private TableView<Object[]> tableViewArrive;

    @FXML
    private Text textChronoHeure;
    @FXML
    private Text textChronoMin;
    @FXML
    private Text textChronoSec;

    /**
     * Constructeur de la classe.
     * @param vue la vue
     * @param course la course à lancer
     */
    public ControleurBoutonsDebutCourse(TriSLN vue, Course course){
        super();
        this.course = course;
        this.setBoutons(vue);
        this.dossardsArrives = 0;
        this.lesArrives = new ArrayList<>();
        this.chrono = new Chronometrage();
        this.tempsDiffere = 0;
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
     * Initialise le tableau d'affichage du nombre de dossard participant à la course et le tableau des arrivés.
     */
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
            TableColumn<Object[], String> colTopDepart = new TableColumn<>("Top départ");
            colTopDepart.setCellValueFactory(cellData -> new SimpleStringProperty((String) String.valueOf(cellData.getValue()[1])));
            TableColumn<Object[], String> colTopArrivee = new TableColumn<>("Top arrivée");
            colTopArrivee.setCellValueFactory(cellData -> new SimpleStringProperty((String) String.valueOf(cellData.getValue()[2])));
            TableColumn<Object[], String> colChrono = new TableColumn<>("Chrono");
            colChrono.setCellValueFactory(cellData -> new SimpleStringProperty((String) String.valueOf(cellData.getValue()[3])));
            TableColumn<Object[], String> colSexe = new TableColumn<>("Sexe");
            colSexe.setCellValueFactory(cellData -> new SimpleStringProperty((String) String.valueOf(cellData.getValue()[4])));
            tableViewArrive.getColumns().setAll(colDossard, colTopDepart, colTopArrivee, colChrono, colSexe);
            arrivalList = FXCollections.observableArrayList();
            tableViewArrive.setItems(arrivalList);

            this.btnDossardArrive.setDisable(true);
            this.numeroDossard.setDisable(true);
            this.btnTopStop.setDisable(true);
        }
    }

    /**
     * Getter du nombre de dossards arrivés.
     * @return le nombre de dossards arrivés
     */
    public int getDossardsArrives(){
        return this.dossardsArrives;
    }

    /**
     * Convertisseur de chaîne de caractère dans le format hh:mm:ss en entier correspondant aux secondes.
     * @param differe la chaîne de caractère du temps à convertir
     * @return le nombre de secondes que contient le temps entré en paramètre
     */
    public static int convertirTextToSecondes(String differe) {
        String[] textDecoupe = differe.split(":");
        int heures = Integer.parseInt(textDecoupe[0]);
        int minutes = Integer.parseInt(textDecoupe[1]);
        int secondes = Integer.parseInt(textDecoupe[2]);
        int secondesDiffere = (heures * 3600) + (minutes * 60) + secondes;
        return secondesDiffere;
    }

    /**
     * Setter du nombre de dossards arrivés.
     */
    public void setDossardsArrives(int lesDossardsArrives){
        this.dossardsArrives = lesDossardsArrives;
    }

    /**
     * Inscrit le dossard dans le tableau avec son temps de course et s'il il a un teamps de départ différés 
     */
    public void enregistrerArrive() throws SQLException, ChronoNotStartedException {
        int leDossard = -1;
        if (!this.numeroDossard.getText().isEmpty()) {
            leDossard = Integer.parseInt(this.numeroDossard.getText());
        }
        if (TriSLN.getBd().isParticipantOfCourse(leDossard, this.course) && !lesArrives.contains(leDossard)){
            lesArrives.add(leDossard);        
            System.out.println("Dossard " + leDossard + " est arrivé.");
            Participant participant = TriSLN.getBd().getParticipantByDossard(leDossard); 
            int tempsCourse = (int)this.chrono.getDuree();
            int tempDepart = 0;
            String tempsComplet = TriSLN.getBd().tempsEnSTring(tempsCourse);

            if (participant.getSexe()== 'M') {
                tempsCourse = tempsCourse - tempsDiffere;
                tempDepart = tempsDiffere;
            }
            String tempsACourir = TriSLN.getBd().tempsEnSTring(tempsCourse);
            String debutCourse = TriSLN.getBd().tempsEnSTring(tempDepart);
            Object[] dosssardArrive = new Object[]{leDossard, debutCourse, tempsComplet, tempsACourir , participant.getSexe()};
            arrivalList.add(dosssardArrive);
            int index = arrivalList.indexOf(dosssardArrive) + 1;
            this.tableViewArrive.setItems(arrivalList);
            this.dossardsArrives += 1;
            TriSLN.getBd().genererClassement(participant.getId(),index, this.course.getId(), tempsCourse, participant.getClub());
            this.tableViewDossards.refresh();
            this.infoDossardArr.setText("");
        } else {
            this.infoDossardArr.setText("Ce dossard n'existe pas");
        }
        this.numeroDossard.setText("");
    }

    /**
     * Gère l'accessibilité du bouton Start si le format de l'heure différés est bien respecté.
     */
    public void changerBoutonLancerChrono() {
        boolean respectRegex = this.tempsDiff.getText().matches("^([01]?\\d|2[0-3]):([0-5]\\d):([0-5]\\d)$");
        boolean tempsDiffEmpty = this.tempsDiff.getText().isEmpty();

        this.btnTopDepart.setDisable(!(respectRegex || tempsDiffEmpty));
    }

    /**
     * Permet la saise d'une arrivée de dossard avec la touche entrée.
     */
    @FXML
    public void onKeyPressed(KeyEvent event) {
        TextField tf = (TextField) event.getSource();
        if (tf.getId().equals("numeroDossard")) {
            if (event.getCode().equals(KeyCode.ENTER)) {
                this.btnDossardArrive.fire();
            }
        } else if (tf.getId().equals("tempsDiff")) {
            changerBoutonLancerChrono();
        }
    }

    /**
     * Gère l'affichage des boutons si la souris en survole un.
     * @param event l'évenement de la souris qui survole un élément
     */
    @FXML
    public void handleBtnDemarrerCoursesMouseEntered(MouseEvent event) {
        try {
            Button btn = (Button) event.getSource();
            switch (btn.getId()) {
                case "btnDossardArrive":
                    super.getVue().changeButtonColor(this.btnDossardArrive, "#105c74", "");
                    break;
                case "btnTopDepart":
                    super.getVue().changeButtonColor(this.btnTopDepart, "#105c74", "");
                    break;
                case "btnTopStop":
                    super.getVue().changeButtonColor(this.btnTopStop, "#105c74", "");
                    break;
                case "btnFinCourse":
                    super.getVue().changeButtonColor(this.btnFinCourse, "#105c74", "");
                    break;
                default:
                    super.handleBtnsMouseEntered(btn);
                    break;
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
    public void handleBtnDemarrerCoursesMouseExited(MouseEvent event) {
        try {
            Button btn = (Button) event.getSource();
            switch (btn.getId()) {
                case "btnDossardArrive":
                    super.getVue().changeButtonColor(this.btnDossardArrive, "#2596BE", "");
                    break;
                case "btnTopDepart":
                    super.getVue().changeButtonColor(this.btnTopDepart, "#2596BE", "");
                    break;
                case "btnTopStop":
                    super.getVue().changeButtonColor(this.btnTopStop, "#2596BE", "");
                    break;
                case "btnFinCourse":
                    super.getVue().changeButtonColor(this.btnFinCourse, "#2596BE", "");
                    break;
                default:
                    super.handleBtnsMouseExited(btn);   
                    break;
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
            switch (btn.getId()) {
                case "btnRetour":
                    super.getVue().afficheCourses();
                    break;
                case "btnDossardArrive":
                    if (this.chrono.estDemarrer()) {
                        enregistrerArrive();
                        this.numeroDossard.setText("");
                    } else {
                        System.out.println("Le chronomètre n'a pas démarrer");
                    }
                    break;

                case "btnTopDepart":
                    this.tempsDiff.setDisable(true);
                    this.chrono.demarrer();
                    if ((!this.tempsDiff.getText().equals("")) && this.chrono.getEstParti()) {
                        tempsDiffere = convertirTextToSecondes(this.tempsDiff.getText());
                    }
                    this.btnDossardArrive.setDisable(false);
                    this.numeroDossard.setDisable(false);
                    this.btnTopDepart.setDisable(true);
                    this.btnTopStop.setDisable(false);
                    Timer timer = new Timer();
                    timer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            Platform.runLater(() -> {
                                try {
                                    int heure = Integer.parseInt((textChronoHeure.getText()));
                                    int minutes = Integer.parseInt((textChronoMin.getText()));
                                    int seconds = (int) chrono.getDuree() % 60;
                                    if ((seconds % 60) == 0 && chrono.getDuree() > 1) {
                                        minutes += 1;
                                    }
                                    if (minutes == 60) {
                                        minutes = 0;
                                        heure += 1;
                                    }
                                    String textHeure = String.valueOf(heure);
                                    String textMin = String.valueOf(minutes);
                                    String textSec = String.valueOf(seconds);
                                    if (textHeure.length() == 1) {
                                        textHeure = "0" + textHeure;
                                    }
                                    if (textMin.length() == 1) {
                                        textMin = "0" + textMin;
                                    }
                                    if (textSec.length() == 1) {
                                        textSec = "0" + textSec;
                                    }
                                    textChronoHeure.setText(textHeure);
                                    textChronoMin.setText(textMin);
                                    textChronoSec.setText(textSec);
                                } catch (ChronoNotStartedException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                        }
                    }, 0, 1000);
                    break;

                case "btnTopStop":
                    this.chrono.stopper();
                    this.btnDossardArrive.setDisable(true);
                    this.numeroDossard.setDisable(true);
                    this.btnTopDepart.setDisable(false);
                    this.btnTopStop.setDisable(true);
                    break;

                case "btnFinCourse":
                    super.getVue().afficheCourses();
                    break;

                default:
                    super.handle(btn);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}