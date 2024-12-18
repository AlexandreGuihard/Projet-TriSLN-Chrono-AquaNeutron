package src.vue;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

import java.io.File;
import javafx.scene.control.ComboBox;

import src.vue.*;
import src.bd.*;
import src.controleurs.*;



public class TriSLN extends Application{
    private Stage stage;
    private ConnexionMySQL co;

    private static BdTriSLN bd;
    private boolean connecte;
    private Button btnConnexion;
    private Button btnDeconnexion;
    private Button btnPaticipants; // Bouton Participants de la page d'accueil
    private Button btnCourses; // Bouton Courses de la page d'accueil
    private Button btnClassements; // Bouton Classements de la page d'accueil
    private Button btnAccueil;
    private Button btnNvlCourse;
    private Button btnAJtCourse;
    private Button btnRetour;
    private Button btnCompte;
    private FenetreParticipant fenetreParticipants;
    private FenetreClassements fenetreClassements;
    private FenetreCourses fenetreCourses;
    private FenetreLogin fenetreLogin;
    private String precFXML;
    private ControleurBoutons precControleur;

    public static void main(String[] args){
        launch();
    }

    public void init(){
        bd = new BdTriSLN(new ConnexionMySQL("servinfo-maria", "DBguihard", "guihard", "guihard"));
        this.connecte=false;
    }
    public void start(Stage stage){
        this.precFXML="src/vue/fxml/SAEprojetAccueil.fxml";
        this.precControleur=new ControleurBoutonsCo(this);
        System.out.println("Méthode start() appelée");
        this.stage = stage;
        this.stage.setTitle("TriSLN");
        File file=new File("src/vue/fxml/SAEprojetAccueil.fxml");
        try{
            FXMLLoader loader=new FXMLLoader(file.toURI().toURL());
            loader.setController(this.precControleur);
            BorderPane accueil=(BorderPane)loader.load();
            Scene scene = new Scene(accueil);
            this.stage.setScene(scene);
            this.stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void afficheLogin () throws IOException{
        File file=new File("src/vue/fxml/SAEprojetConnexion.fxml");
        try{
            FXMLLoader loader=new FXMLLoader(file.toURI().toURL());
            loader.setController(new ControleurBoutonsLogin(this));
            this.fenetreLogin=new FenetreLogin(loader, this.stage);
            this.stage = this.fenetreLogin.getWindow();
            this.stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }

    public void afficheAccueil() throws IOException {
        if (this.stage == null) {
            System.out.println("Erreur : Stage non initialisé dans afficheAccueil.");
            return;
        }    
        File file=new File("src/vue/fxml/SAEprojetAccueil.fxml");
        this.precFXML = file.getPath();
        ControleurBoutonsCo controleur = new ControleurBoutonsCo(this);
        this.precControleur = controleur;
        try{
            FXMLLoader loader=new FXMLLoader(file.toURI().toURL());
            loader.setController(new ControleurBoutonsCo(this));
            BorderPane accueil=(BorderPane)loader.load();
            Scene scene=new Scene(accueil);
            this.stage.setScene(scene);
            this.stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void afficheAjouterP() throws IOException {
        if (this.stage == null) {
            System.out.println("Erreur : Stage non initialisé dans afficheAjouterP.");
            return;
        }    
        File file=new File("src/vue/fxml/SAEprojetAjouterLesparticipant.fxml");
        this.precFXML = file.getPath();
        ControleurBoutonsCo controleur = new ControleurBoutonsCo(this);
        this.precControleur = controleur;
        try{
            FXMLLoader loader=new FXMLLoader(file.toURI().toURL());
            loader.setController(new ControleurBoutonsAjouterP(this));
            BorderPane accueil=(BorderPane)loader.load();
            Scene scene=new Scene(accueil);
            this.stage.setScene(scene);
            this.stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void afficheSupprimerP() throws IOException {
        if (this.stage == null) {
            System.out.println("Erreur : Stage non initialisé dans afficheSupprimerP.");
            return;
        }    
        File file=new File("src/vue/fxml/SAEprojetSupprimerLesparticipant.fxml");
        this.precFXML = file.getPath();
        ControleurBoutonsCo controleur = new ControleurBoutonsCo(this);
        this.precControleur = controleur;
        try{
            FXMLLoader loader=new FXMLLoader(file.toURI().toURL());
            loader.setController(new ControleurBoutonsSupprimerP(this));
            BorderPane accueil=(BorderPane)loader.load();
            Scene scene=new Scene(accueil);
            this.stage.setScene(scene);
            this.stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void affichemodifierP() throws IOException {
        if (this.stage == null) {
            System.out.println("Erreur : Stage non initialisé dans affichemodifierP.");
            return;
        }    
        File file=new File("src/vue/fxml/SAEprojetModifierLesparticipant.fxml");
        this.precFXML = file.getPath();
        ControleurBoutonsCo controleur = new ControleurBoutonsCo(this);
        this.precControleur = controleur;
        try{
            FXMLLoader loader=new FXMLLoader(file.toURI().toURL());
            loader.setController(new ControleurBoutonsCo(this));
            BorderPane accueil=(BorderPane)loader.load();
            Scene scene=new Scene(accueil);
            this.stage.setScene(scene);
            this.stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void afficheAccueilConnecte(){
        File file=new File("src/vue/fxml/SAEprojetAccueilConnecter.fxml");
        this.precFXML = file.getPath();
        ControleurBoutonsCo controleur = new ControleurBoutonsCo(this);
        this.precControleur = controleur;
        try{
            FXMLLoader loader=new FXMLLoader(file.toURI().toURL());
            loader.setController(new ControleurBoutonsCo(this));
            BorderPane accueilConnecte=(BorderPane)loader.load();
            Scene scene=new Scene(accueilConnecte);
            this.stage.setScene(scene);
            this.stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void afficheParticipants(){
        this.precFXML="src/vue/fxml/SAEprojetAccueilConnecter.fxml";
        this.precControleur=new ControleurBoutonsCo(this);
        File file=new File("src/vue/fxml/SAEprojetParticiperAccueil.fxml");
        try{
            FXMLLoader loader=new FXMLLoader(file.toURI().toURL());
            loader.setController(new ControleurBoutonsParticipants(this));
            this.fenetreParticipants=new FenetreParticipant(loader, this.stage);
            this.stage=this.fenetreParticipants.getStage();
            this.stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void afficheLesParticipants(){
        File file=new File("src/vue/fxml/SAEprojetParticiperCategorie.fxml");
        this.precFXML = file.getPath();
        ControleurBoutonsParticipants controleur = new ControleurBoutonsParticipants(this);
        this.precControleur = controleur;
        try{
            FXMLLoader loader=new FXMLLoader(file.toURI().toURL());
            loader.setController(new ControleurBoutonsParticipants(this));
            this.fenetreParticipants.afficheParticipants(loader);
            this.stage=this.fenetreParticipants.getStage();
            this.stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void affichePopUp(FXMLLoader loader, String popUpName){
        try{
            ControleurBoutonsPopUp controleur=new ControleurBoutonsPopUp(this);
            loader.setController(controleur);
            switch(popUpName){
                case "V":
                    this.fenetreParticipants.popUpVeterans(loader);
                    break;
                case "S":
                    this.fenetreParticipants.popUpSeniors(loader);
                    break;    
            }
            this.stage=this.fenetreParticipants.getPopUpStage();
            this.stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void closePopUpStage(){
        this.stage.close();
    }

    public void afficheCourses() throws IOException{
        File file=new File("src/vue/fxml/SAEprojetGererCourses.fxml");
        this.precFXML="src/vue/fxml/SAEprojetAccueilConnecter.fxml";
        this.precControleur=new ControleurBoutonsCo(this);
        try{
            FXMLLoader loader=new FXMLLoader(file.toURI().toURL());
            loader.setController(new ControleurBoutonsCourses(this));
            this.fenetreCourses=new FenetreCourses(loader, this.stage);
            this.stage = this.fenetreCourses.getWindow();
            this.stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }



    public void afficheClassements() throws IOException{
        File file=new File("src/vue/fxml/SAEprojetClassements.fxml");
        this.precFXML = file.getPath();
        ControleurBoutonsClassements controleur = new ControleurBoutonsClassements(this);
        this.precControleur = controleur;
        try{
            FXMLLoader loader=new FXMLLoader(file.toURI().toURL());
            loader.setController(new ControleurBoutonsClassements(this));
            this.fenetreClassements=new FenetreClassements(loader, this.stage);
            this.stage = this.fenetreClassements.getWindow();
            this.stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void afficheClassementsDisconnected() throws IOException{
        File file=new File("src/vue/fxml/SAEprojetClassementsDisconnected.fxml");
        this.precFXML = file.getPath();
        ControleurBoutonsClassements controleur = new ControleurBoutonsClassements(this);
        this.precControleur = controleur;
        try{
            FXMLLoader loader=new FXMLLoader(file.toURI().toURL());
            loader.setController(new ControleurBoutonsClassements(this));
            this.fenetreClassements=new FenetreClassements(loader, this.stage);
            this.stage = this.fenetreClassements.getWindow();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void afficheNvlCourse() throws IOException{
        File file=new File("src/vue/fxml/SAEprojetNouvelleCourse.fxml");
        this.precFXML = "src/vue/fxml/SAEprojetGererCourses.fxml";
        this.precControleur = new ControleurBoutonsCourses(this);
        try{
            FXMLLoader loader=new FXMLLoader(file.toURI().toURL());
            loader.setController(new ControleurBoutonsNouvelleCourses(this));
            this.fenetreCourses=new FenetreCourses(loader, this.stage);
            this.stage = this.fenetreCourses.getWindow();
            this.stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void afficheMonCompte() throws IOException{
        File file=new File("src/vue/fxml/SAEprojet_Mon_compte_utilisateur.fxml");
        try{
            FXMLLoader loader=new FXMLLoader(file.toURI().toURL());
            loader.setController(new ControleurBoutonsCompte(this));
            BorderPane moncompte=(BorderPane)loader.load();
            Scene scene=new Scene(moncompte);
            this.stage.setScene(scene);
            this.stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void afficheRetour() {
        System.out.println(this.precFXML);
        if (this.precFXML == null) {
            System.out.println("Erreur : aucune vue précédente n'a été enregistrée.");
            return;
        }
    
        File file = new File(this.precFXML); // Utilise le dernier chemin enregistré
        try {
            FXMLLoader loader = new FXMLLoader(file.toURI().toURL());
            loader.setController(this.precControleur);
            BorderPane precedent = loader.load();
            Scene scene = new Scene(precedent);
            this.stage.setScene(scene);
            this.stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public FenetreParticipant getFenetreParticipants(){
        return this.fenetreParticipants;
    }

    public FenetreClassements getFenetreClassements(){
        return this.fenetreClassements;
    }

    public FenetreCourses getFenetreCourses(){
        return this.fenetreCourses;
    }

    public FenetreLogin getFenetreLogin(){
        return this.fenetreLogin;
    }

    public boolean getConnecte(){
        return this.connecte;
    }

    public static BdTriSLN getBd(){
        return bd;
    }

    public Stage getStage(){
        return this.stage;
    }
    
    public void setFenetreParticipants(FenetreParticipant fenetreParticipants){
        this.fenetreParticipants=fenetreParticipants;
    }

    public void setFenetreClassements(FenetreClassements fenetreClassements){
        this.fenetreClassements=fenetreClassements;
    }

    public void setFenetreCourses(FenetreCourses fenetreCourses){
        this.fenetreCourses=fenetreCourses;
    }

    public void setFenetreLogin(FenetreLogin fenetreLogin){
        this.fenetreLogin=fenetreLogin;
    }

    public void setConnecte(boolean connecte){
        this.connecte=connecte;
    }

    public void setStage(Stage stage){
        this.stage=stage;
    }

    public void changeButtonColor(Button button, String color, String otherStyle){
        if(otherStyle.equals("")){
            button.setStyle("-fx-background-color: "+color+";");
        }
        else{
            button.setStyle("-fx-background-color: "+color+";"+otherStyle+";");
        }
    }
}