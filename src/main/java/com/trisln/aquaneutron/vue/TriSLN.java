package com.trisln.aquaneutron.vue;

import com.trisln.aquaneutron.modele.Utilisateur;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

import java.sql.SQLException;


import java.io.File;
import com.trisln.aquaneutron.bd.*;
import com.trisln.aquaneutron.controleurs.*;
import com.trisln.aquaneutron.modele.Course;

/**
 * Classe qui gère la vue de l'application et l'affichage des pages.
 */
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

    /**
     * Main de la classe.
     * @param args saisie du terminal
     */
    public static void main(String[] args){
        launch();
    }

    /**
     * Initialisation de la classe avec la connexion à la base de données.
     */
    public void init(){

        bd = new BdTriSLN(new ConnexionMySQL("servinfo-maria", "DBguihard", "guihard", "guihard"));
        this.utilisateur = new Utilisateur();
        this.connecte=false;
    }

    /**
     * Méthode qui affiche la page d'accueil au lancement de l'application.
     * @param stage la fenêtre principale de l'application
     */
    public void start(Stage stage){
        this.precFXML="src/main/resources/com/trisln/aquaneutron/trislnaquaneutron/SAEprojetAccueil.fxml";
        this.precControleur=new ControleurBoutonsCo(this);
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

    /**
     * Méthode qui affiche la page de login.
     * @throws IOException
     */
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

    /**
     * Méthode qui affiche la pop up du mot de passe oublié et de saisie d'email.
     * @throws IOException
     */
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

    /**
     * Méthode qui affiche la pop up qui demande le code de vérificate de l'email.
     * @param token le token à saisir pour confirmer son email
     * @param controleur le controleur du pop Up
     * @throws IOException
     */
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
            this.stage.setOnCloseRequest(event -> {
                this.popUpStage = null;
            });

            this.stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode qui affiche la pop up de saisie du nouveau de passe.
     * @param controleur le controleur du pop Up
     * @throws IOException
     */
    public void affichePopUpMDP(ControleurBoutonsPopUpLogin controleur) throws IOException {
        File file = new File("src/main/resources/com/trisln/aquaneutron/trislnaquaneutron/SAEprojetPopUpAskNewPassword.fxml");
        try {
            FXMLLoader loader = new FXMLLoader(file.toURI().toURL());
            loader.setController(controleur);
            BorderPane page = (BorderPane) loader.load();
            Scene scene = new Scene(page);
            this.popUpStage.setScene(scene);
            this.popUpStage.setResizable(false);
            this.popUpStage.setAlwaysOnTop(true);
            this.stage.setOnCloseRequest(event -> {
                this.popUpStage = null;
            });

            this.stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode qui affiche l'acceuil lorsque l'utilisateur n'est pas connecté.
     * @throws IOException
     */
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

    /**
     * Méthode qui affiche la page d'ajout de participant.
     * @throws IOException
     */
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

    /**
     * Méthode qui affiche la page de supression de participant.
     * @throws IOException
     */
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

    /**
     * Méthode qui affiche la page de modification de participant.
     * @throws IOException
     */
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

    /**
     * Méthode qui affiche la page d'accueil lorsque l'utilisateur est connecté.
     */
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

    /**
     * Méthode qui affiche la page du choix des participants à afficher.
     */
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

    /**
     * Méthode qui affiche la page des participants en fonction de la catégorie et de la sous catégorie.
     * @param categorieChoisie la catégorie choisie
     * @param sousCategorieChoisie la sous-catégorie choisie
     */
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

    /**
     * Méthode qui affiche la page de choix de la sous sous catégorie.
     * @param loader le FXMLLoader utilisé pour charger la vue
     * @param popUpName categorie de la pop Up
     */
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

    /**
     * Méthode qui ferme la pop Up en cours d'affichage.
     */
    public void closePopUpStage(){
        this.stage.close();
    }

    /**
     * Méthode qui affiche la page qui montre toutes les courses.
     * @throws IOException
     */
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

    /**
     * Méthode qui affiche la page de classement lorsque l'utilisateur est connecté.
     * @throws IOException
     */
    public void afficheClassements() throws IOException {
        File file = new File("src/main/resources/com/trisln/aquaneutron/trislnaquaneutron/SAEprojetClassements.fxml");
        try {
            FXMLLoader loader = new FXMLLoader(file.toURI().toURL());
            loader.setController(new ControleurBoutonsClassements(this));
            BorderPane root = loader.load();
            Scene scene = new Scene(root);
            this.stage.setScene(scene);
            this.stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode qui affiche la page de classement lorsque l'utilisateur est déconnecté.
     * @throws IOException
     */
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

    /**
     * Méthode qui affiche la page d'ajout de course.
     * @throws IOException
     */
    public void afficheNvlCourse() throws IOException{
        File file=new File("src/main/resources/com/trisln/aquaneutron/trislnaquaneutron/SAEprojetNouvelleCourse.fxml");
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

    /**
     * Méthode qui affiche la page de lancement de course.
     * @param course la course à demarrer
     * @throws IOException
     */
    public void afficheDemarerCourse(Course course) throws IOException{
        File file=new File("src/main/resources/com/trisln/aquaneutron/trislnaquaneutron/SAEprojetChrono.fxml");
        try{
            FXMLLoader loader=new FXMLLoader(file.toURI().toURL());
            loader.setController(new ControleurBoutonsDebutCourse(this, course));
            this.fenetreCourses=new FenetreCourses(loader, this.stage);
            this.stage=this.fenetreCourses.getWindow();
            this.stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Méthode qui affiche la page des informations du compte.
     * @throws IOException
     */
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

    /**
     * Méthode qui affiche la page précédente à la page en cours d'utilisation.
     */
    public void afficheRetour() {
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
    
    /**
     * Getter du FenetreParticipant de la vue
     * @return du FenetreParticipant de la vue
     */
    public FenetreParticipant getFenetreParticipants(){
        return this.fenetreParticipants;
    }

    /**
     * Getter du FenetreClassements de la vue
     * @return du FenetreClassements de la vue
     */
    public FenetreClassements getFenetreClassements(){
        return this.fenetreClassements;
    }

    /**
     * Getter du FenetreCourses de la vue
     * @return du FenetreCourses de la vue
     */
    public FenetreCourses getFenetreCourses(){
        return this.fenetreCourses;
    }

    /**
     * Getter du FenetreLogin de la vue
     * @return du FenetreLogin de la vue
     */
    public FenetreLogin getFenetreLogin(){
        return this.fenetreLogin;
    }

    /**
     * Getter pour savoir si l'utilisateur est connecté
     * @return true si l'utilisateur est connecté et false sinon
     */
    public boolean getConnecte(){
        return this.connecte;
    }

    /**
     * Getter de la base de données
     * @return la base de données
     */
    public static BdTriSLN getBd(){
        return bd;
    }

    /**
     * Getter de la fenêtre principale de l'application
     * @return la fenêtre principale de l'application
     */
    public Stage getStage(){
        return this.stage;
    }

    /**
     * Getter de l'utilisateur de l'application
     * @return l'utilisateur de l'application
     */
    public Utilisateur getUtilisateur() {
        return this.utilisateur;
    }

    /**
     * Setter de l'utilisateur de l'application
     * @param utilisateur l'utilisateur de l'application
     */
    public void setUtilisateur(Utilisateur utilisateur){
        this.utilisateur=utilisateur;
    }
    
    /**
     * Setter du FenetreParticipant de la vue
     * @param fenetreParticipants FenetreParticipant de la vue
     */
    public void setFenetreParticipants(FenetreParticipant fenetreParticipants){
        this.fenetreParticipants=fenetreParticipants;
    }

    /**
     * Setter du FenetreClassements de la vue
     * @param fenetreClassements FenetreClassements de la vue
     */
    public void setFenetreClassements(FenetreClassements fenetreClassements){
        this.fenetreClassements=fenetreClassements;
    }

    /**
     * Setter du FenetreCourses de la vue
     * @param fenetreCourses FenetreCourses de la vue
     */
    public void setFenetreCourses(FenetreCourses fenetreCourses){
        this.fenetreCourses=fenetreCourses;
    }

    /**
     * Setter du FenetreLogin de la vue
     * @param fenetreLogin FenetreLogin de la vue
     */
    public void setFenetreLogin(FenetreLogin fenetreLogin){
        this.fenetreLogin=fenetreLogin;
    }

    /**
     * Setter du boolean pour savoir si l'utilisateur est connecte
     * @param connecte boolean pour savoir si l'utilisateur est connecte
     */
    public void setConnecte(boolean connecte){
        this.connecte=connecte;
    }

    /**
     * Setter de la fenêtre principale de l'application
     * @param stage la fenêtre principale de l'application
     */
    public void setStage(Stage stage){
        this.stage=stage;
    }

    /**
     * Changement du style du boutton
     * @param button le button
     * @param color couleur à appliqué
     * @param otherStyle style à appliqué
     */
    public void changeButtonColor(Button button, String color, String otherStyle){
        if(otherStyle.equals("")){
            button.setStyle("-fx-background-color: "+color+";");
        }
        else{
            button.setStyle("-fx-background-color: "+color+";"+otherStyle+";");
        }
    }

    /**
     * Affichage du classement sous forme de pdf
	 * @param host le nom du serveur
	 * @param database le nom de la base de données
	 * @param user le nom de l'utilisateur
	 * @param password le mot de passe de l'utilisateur
     * @param categorieCode la catégorie du classement
     * @param genre le genre du classement
	 */
    public void affichePDF(String host, String user, String password, String database, String categorieCode, String genre, String Licence) {
        if (host == null || user == null || password == null || database == null || categorieCode == null || genre == null) {
            return;
        }
        try {
            BdTriSLN bdTriSLN = new BdTriSLN(new ConnexionMySQL(host, database, user, password));
            bdTriSLN.genererPdfClassement(host, user, password, database, genre, categorieCode, Licence);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
