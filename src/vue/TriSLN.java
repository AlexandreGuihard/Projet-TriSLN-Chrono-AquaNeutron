import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.io.File;


public class TriSLN extends Application{
    private boolean connecte=false;
    private Button btnConnexion;
    private Button btnDeconnexion;
    private Button btnPaticipants; // Bouton Participants de la page d'accueil
    private Button btnCourses; // Bouton Courses de la page d'accueil
    private Button btnClassements; // Bouton Classements de la page d'accueil
    private Button btnAccueil;
    private FenetreParticipants fenetreParticipants;
    private FenetreClassements fenetreClassements;
    private FenetreCourses fenetreCourses;
    private FenetreLogin fenetreLogin;

    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage stage){
        stage.setTitle("TriSLN");
        File file=new File("src/SAEprojetAccueil.fxml");
        try{
            FXMLLoader loader=new FXMLLoader(file.toURI().toURL());
            loader.setController(new ControleurBoutons(this));
            BorderPane accueil=(BorderPane)loader.load();
            Scene scene = new Scene(accueil);
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        //try{
        //    BorderPane root=loader.load();
        //    Scene scene=new Scene(root);
        //    stage.setScene(scene);
        //}
        //catch(IOException e){
        //    e.printStackTrace();
        //}
        //stage.show();
    }

    public void afficheLogin(){
        
    }

    public void afficheAccueilConnecte(){

    }

    public void afficheParticipants(){
        
    }

    public void afficheCourses(){
        
    }

    public void afficheClassements(){
        
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

    public FenetreParticipants getFenetreParticipants(){
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

    public void setFenetreParticipants(FenetreParticipants fenetreParticipants){
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

    public void changeButtonColor(Button button){
        button.setStyle("-fx-background-color: #105c74;");
    }
}