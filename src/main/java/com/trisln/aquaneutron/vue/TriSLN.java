package com.trisln.aquaneutron.vue;

import com.trisln.aquaneutron.modele.Utilisateur;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;

import com.trisln.aquaneutron.vue.*;
import com.trisln.aquaneutron.bd.*;
import com.trisln.aquaneutron.controleurs.*;

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

    private Utilisateur utilisateur;
    private Stage popUpStage;
  
    private String precFXML;
    private ControleurBoutons precControleur;

    public static void main(String[] args){
        launch();
    }

    public void init(){
        bd = new BdTriSLN(new ConnexionMySQL("servinfo-maria", "DBguihard", "guihard", "guihard"));
        this.utilisateur = new Utilisateur();
        this.connecte=false;
    }
    public void start(Stage stage){
        this.precFXML="src/main/resources/com/trisln/aquaneutron/trislnaquaneutron/SAEprojetAccueil.fxml";
        this.precControleur=new ControleurBoutonsCo(this);
        System.out.println("Méthode start() appelée");
        this.stage = stage;
        this.stage.setTitle("TriSLN");
        File file=new File("src/main/resources/com/trisln/aquaneutron/trislnaquaneutron/SAEprojetAccueil.fxml");
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

    public void afficheLogin() throws IOException{
        File file=new File("src/main/resources/com/trisln/aquaneutron/trislnaquaneutron/SAEprojetConnexion.fxml");
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

    public void affichePopUpLogin() throws IOException {
        if (this.popUpStage != null && this.popUpStage.isShowing()) {
            this.stage.toFront();
            return;
        }

        File file = new File("src/main/resources/com/trisln/aquaneutron/trislnaquaneutron/SAEprojetPopUpAskEmail.fxml");
        try {
            FXMLLoader loader = new FXMLLoader(file.toURI().toURL());
            loader.setController(new ControleurBoutonsPopUpLogin(this));
            this.popUpStage = new Stage();
            this.popUpStage.setResizable(false);
            this.popUpStage.setAlwaysOnTop(true);

            this.fenetreLogin = new FenetreLogin(loader, this.popUpStage);

            // Si on ferme la pop-up
            this.stage.setOnCloseRequest(event -> {
                this.popUpStage = null;
            });

            this.stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void affichePopUpCode(String token, ControleurBoutonsPopUpLogin controleur) throws IOException {
        File file = new File("src/main/resources/com/trisln/aquaneutron/trislnaquaneutron/SAEprojetPopUpAskCode.fxml");
        try {
            FXMLLoader loader = new FXMLLoader(file.toURI().toURL());
            loader.setController(controleur);
            BorderPane page = (BorderPane) loader.load();
            Scene scene = new Scene(page);
            this.popUpStage.setScene(scene);
            this.popUpStage.setResizable(false);
            this.popUpStage.setAlwaysOnTop(true);

//            this.fenetreLogin = new FenetreLogin(loader, this.popUpStage);

            // Si on ferme la pop-up
            this.stage.setOnCloseRequest(event -> {
                this.popUpStage = null;
            });

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
        File file=new File("src/main/resources/com/trisln/aquaneutron/trislnaquaneutron/SAEprojetAjouterLesparticipant.fxml");
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
        File file=new File("src/main/resources/com/trisln/aquaneutron/trislnaquaneutron/SAEprojetSupprimerLesparticipant.fxml");
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

    public void afficheModifierP() throws IOException {
        if (this.stage == null) {
            System.out.println("Erreur : Stage non initialisé dans affichemodifierP.");
            return;
        }    
        File file=new File("src/main/resources/com/trisln/aquaneutron/trislnaquaneutron/SAEprojetModifierLesparticipant.fxml");
        this.precFXML = file.getPath();
        ControleurBoutonsCo controleur = new ControleurBoutonsCo(this);
        this.precControleur = controleur;
        try{
            FXMLLoader loader=new FXMLLoader(file.toURI().toURL());
            loader.setController(new ControleurBoutonsModifierP(this));
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
        File file=new File("src/main/resources/com/trisln/aquaneutron/trislnaquaneutron/SAEprojetAccueilConnecter.fxml");
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
        this.precFXML="src/main/resources/com/trisln/aquaneutron/trislnaquaneutron/SAEprojetAccueilConnecter.fxml";
        this.precControleur=new ControleurBoutonsCo(this);
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

    public void afficheLesParticipants(String categorieChoisie, String sousCategorieChoisie){
        File file=new File("src/main/resources/com/trisln/aquaneutron/trislnaquaneutron/SAEprojetParticiperCategorie.fxml");
        this.precFXML = file.getPath();
        ControleurBoutonsParticipants controleur = new ControleurBoutonsParticipants(this, categorieChoisie, sousCategorieChoisie);
        this.precControleur = controleur;
        try{
            FXMLLoader loader=new FXMLLoader(file.toURI().toURL());
            loader.setController(new ControleurBoutonsParticipants(this, categorieChoisie, sousCategorieChoisie));
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
            ControleurBoutonsPopUp controleur=new ControleurBoutonsPopUp(this, popUpName);
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
        File file=new File("src/main/resources/com/trisln/aquaneutron/trislnaquaneutron/SAEprojetGererCourses.fxml");
        this.precFXML="src/main/resources/com/trisln/aquaneutron/trislnaquaneutron/SAEprojetAccueilConnecter.fxml";
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
        this.precFXML = "src/main/resources/com/trisln/aquaneutron/trislnaquaneutron/SAEprojetGererCourses.fxml";
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
        System.out.println(this.precFXML);
        if (this.precFXML == null) {
            System.out.println("Erreur : aucune vue précédente n'a été enregistrée.");
            return;
        }
    
        File file = new File(this.precFXML);
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

    public Utilisateur getUtilisateur() {
        return this.utilisateur;
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

    public void affichePDF(String host, String user, String password, String database, String categorieCode, String genre) {
        if (host == null || user == null || password == null || database == null || categorieCode == null || genre == null) {
            return;
        }

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                "python3",
                "src/main/java/com/trisln/aquaneutron/bd/generationsPDF.py",
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

            if (process.waitFor() != 0) {
                System.err.println("Code de sortie : " + process.waitFor());
            }

        } catch (IOException | InterruptedException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}