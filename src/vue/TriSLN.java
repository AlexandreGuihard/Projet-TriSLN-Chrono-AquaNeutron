package src.vue;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.io.File;

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
    private FenetreParticipant fenetreParticipants;
    private FenetreClassements fenetreClassements;
    private FenetreCourses fenetreCourses;
    private FenetreLogin fenetreLogin;

    public static void main(String[] args){
        launch();
    }

    public void init(){
        bd = new BdTriSLN(new ConnexionMySQL("servinfo-maria", "DBguihard", "guihard", "guihard"));
        this.connecte=false;
    }
    public void start(Stage stage){
        this.stage=new Stage();
        this.stage.setTitle("TriSLN");
        File file=new File("src/vue/fxml/SAEprojetAccueil.fxml");
        try{
            FXMLLoader loader=new FXMLLoader(file.toURI().toURL());
            loader.setController(new ControleurBoutonsCo(this));
            BorderPane accueil=(BorderPane)loader.load();
            Scene scene = new Scene(accueil);
            this.stage.setScene(scene);
            this.stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void afficheLogin(){
        File file=new File("src/vue/fxml/SAEprojetConnexion.fxml");
        try{
            FXMLLoader loader=new FXMLLoader(file.toURI().toURL());
            this.fenetreLogin=new FenetreLogin(loader);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }

    public void afficheAccueilConnecte(){
        File file=new File("src/vue/fxml/SAEprojetAccueilConnecter.fxml");
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

    public void afficheCourses(){
        File file=new File("src/vue/fxml/SAEprojetGererCourses.fxml");
        try{
            FXMLLoader loader=new FXMLLoader(file.toURI().toURL());
            this.fenetreCourses=new FenetreCourses(loader);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void afficheClassements(){
        File file=new File("src/vue/fxml/SAEprojetClassements.fxml");
        try{
            FXMLLoader loader=new FXMLLoader(file.toURI().toURL());
            this.fenetreClassements=new FenetreClassements(loader);
        }
        catch(Exception e){
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

    public void changeButtonColor(Button button, String color, String otherStyle){
        if(otherStyle.equals("")){
            button.setStyle("-fx-background-color: "+color+";");
        }
        else{
            button.setStyle("-fx-background-color: "+color+";"+otherStyle+";");
        }
    }
}