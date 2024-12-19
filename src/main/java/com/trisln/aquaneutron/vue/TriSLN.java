package com.trisln.aquaneutron.vue;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.ComboBox;

import com.trisln.aquaneutron.bd.*;
import com.trisln.aquaneutron.controleurs.*;
import com.trisln.aquaneutron.modele.Utilisateur;

import java.io.*;



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

    private Utilisateur utilisateur;

    public static void main(String[] args){
        launch(args);
    }

    public void init(){
        bd = new BdTriSLN(new ConnexionMySQL("servinfo-maria", "DBguihard", "guihard", "guihard"));
        this.utilisateur = new Utilisateur();
        this.connecte=false;
    }
    public void start(Stage stage){
        this.stage=new Stage();
        this.stage.setTitle("TriSLN");
        File file=new File("src/main/resources/com/trisln/aquaneutron/trislnaquaneutron/SAEprojetAccueil.fxml");
        try{
            FXMLLoader loader=new FXMLLoader(file.toURI().toURL());
            loader.setController(new ControleurBoutonsAccueil(this));
            BorderPane accueil=(BorderPane)loader.load();
            Scene scene = new Scene(accueil);
            this.stage.setScene(scene);
            this.stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void afficheLogin() throws IOException {
        File file = new File("src/main/resources/com/trisln/aquaneutron/trislnaquaneutron/SAEprojetConnexion.fxml");
        try {
            FXMLLoader loader = new FXMLLoader(file.toURI().toURL());
            loader.setController(new ControleurBoutonsLogin(this));
            this.fenetreLogin = new FenetreLogin(loader, this.stage);
            this.stage = this.fenetreLogin.getWindow();
            this.stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void afficheAccueil() throws IOException {
        if (this.stage == null) {
            System.out.println("Erreur : Stage non initialisé dans afficheAccueil.");
            return;
        }
        File file=new File("src/main/resources/com/trisln/aquaneutron/trislnaquaneutron/SAEprojetAccueil.fxml");
        try{
            FXMLLoader loader=new FXMLLoader(file.toURI().toURL());
            loader.setController(new ControleurBoutonsAccueil(this));
            BorderPane accueil=(BorderPane)loader.load();
            Scene scene=new Scene(accueil);
            this.stage.setScene(scene);
            this.stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void afficheLesParticipants(){
        File file=new File("src/main/resources/com/trisln/aquaneutron/trislnaquaneutron/SAEprojetParticiperCategorie.fxml");
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

    public void afficheAccueilConnecte(){
        File file=new File("src/main/resources/com/trisln/aquaneutron/trislnaquaneutron/SAEprojetAccueilConnecter.fxml");
        try{
            FXMLLoader loader=new FXMLLoader(file.toURI().toURL());
            loader.setController(new ControleurBoutonsAccueil(this));
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
        File file=new File("src/main/resources/com/trisln/aquaneutron/trislnaquaneutron/SAEprojetParticiperAccueil.fxml");
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

    public void afficheCourses(){
        File file=new File("src/main/resources/com/trisln/aquaneutron/trislnaquaneutron/SAEprojetGererCourses.fxml");
        this.precFXML = file.getPath();
        ControleurBoutonsCourses controleur = new ControleurBoutonsCourses(this);
        this.precControleur = controleur;
        try{
            FXMLLoader loader=new FXMLLoader(file.toURI().toURL());
            loader.setController(controleur);
            this.fenetreCourses=new FenetreCourses(loader, this.stage);
            this.stage = this.fenetreCourses.getWindow();
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


    public void afficheClassements() throws IOException{
        File file=new File("src/main/resources/com/trisln/aquaneutron/trislnaquaneutron/SAEprojetClassements.fxml");
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
        File file=new File("src/main/resources/com/trisln/aquaneutron/trislnaquaneutron/SAEprojetClassementsDisconnected.fxml");
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

    public void afficheNvlCourse() throws IOException{
        File file=new File("src/main/resources/com/trisln/aquaneutron/trislnaquaneutron/SAEprojetNouvelleCourse.fxml");
        this.precFXML = file.getPath();
        ControleurBoutonsCourses controleur = new ControleurBoutonsCourses(this);
        this.precControleur = controleur;
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
        File file=new File("src/main/resources/com/trisln/aquaneutron/trislnaquaneutron/SAEprojet_Mon_compte_utilisateur.fxml");
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
    

    public Button getBConnexion(){
        return this.btnConnexion;
    }

    public Button getBDeconnexion(){
        return this.btnDeconnexion;
    }

    public Button getBParticipants(){
        return this.btnPaticipants;
    }

    public Button getBCourses(){
        return this.btnCourses;
    }

    public Button getBClassements(){
        return this.btnClassements;
    }

    public Button getBAccueil(){
        return this.btnAccueil;
    }

    public Button getBNvlCourse(){
        return this.btnNvlCourse;
    }

    public Button getBAJtCourse(){
        return this.btnAJtCourse;
    }

    public Button getBRetour(){
        return this.btnRetour;
    }

    public Button getBCompte(){
        return this.btnCompte;
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

    public Utilisateur getUtilisateur(){
        return this.utilisateur;
    }

    public void setBConnexion(Button btnConnexion){
        this.btnConnexion=btnConnexion;
    }

    public void setBDeconnexion(Button btnDeconnexion){
        this.btnDeconnexion=btnDeconnexion;
    }

    public void setBParticipants(Button btnParticipants){
        this.btnPaticipants=btnParticipants;
    }

    public void setBCourses(Button btnCourses){
        this.btnCourses=btnCourses;
    }

    public void setBClassements(Button btnClassements){
        this.btnClassements=btnClassements;
    }

    public void setBAccueil(Button btnAccueil){
        this.btnAccueil=btnAccueil;
    }

    public void setBNvlCourse(Button btnNvlCourse){
        this.btnNvlCourse=btnNvlCourse;
    }

    public void setBAJtCourse(Button btnAJtCourse){
        this.btnAJtCourse=btnAJtCourse;
    }

    public void setBRetour(Button btnRetour){
        this.btnRetour=btnRetour;
    }

    public void setBCompte(Button btnCompte){
        this.btnCompte=btnCompte;
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
        if(otherStyle==null){
            button.setStyle("-fx-background-color: "+color+";");
        }
        else{
            button.setStyle("-fx-background-color: "+color+";"+otherStyle+";");
        }
    }

    public void affichePDF(String host, String user, String password, String database, String categorieCode, String genre) {
        if (host == null || user == null || password == null || database == null || categorieCode == null || genre == null) {
            System.out.println("Tous les paramètres doivent être fournis.");
            return;
        }

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                "python3",
                "src/main/java/com/trisl/aquaneutron/bd/generationsPDF.py",
                host, user, password, database, categorieCode, genre
            );

            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = errorReader.readLine()) != null) {
                System.err.println(line);
            }

            if (process.waitFor() == 0) {
                System.out.println("Le PDF a été généré.");
            } else {
                System.err.println("Erreur lors de la génération du PDF. Code de sortie : " + process.waitFor());
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Erreur lors de l'exécution du script Python : " + e.getMessage());
            e.printStackTrace();
        }
    }

}
