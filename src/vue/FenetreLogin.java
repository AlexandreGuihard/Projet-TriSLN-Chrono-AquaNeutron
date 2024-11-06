package src.vue;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import src.vue.*;
import src.bd.*;
import src.controleurs.*;


public class FenetreLogin {

    private BorderPane root;
    private TextField identifier;
    private PasswordField mdp;
    private Button bConnexion;
    private Stage stage;     

    public FenetreLogin(FXMLLoader loader, Stage stage){
        this.root = new BorderPane();
        this.identifier = new TextField();       
        this.mdp = new PasswordField();
        this.bConnexion = new Button();
        this.stage = stage;
        loader.setController(new ControleurBoutonsCo(new TriSLN()));
        this.afficheLogin(loader);
    }

    public String getIdentifiant() {
        return this.identifier.getText();
    }

    public void setIdentifiant(TextField identifiant) {
        this.identifier= identifiant;
    }

    public String getMotDePasse() {
        return this.mdp.getText();
    }

    public void setMotDePasse(PasswordField motDePasse){
        this.mdp = motDePasse;
    }

    public Button getBoutonConnexion() {
        return this.bConnexion;
    }

    public void setBoutonConnexion(Button bouton){
        this.bConnexion = bouton;
    }

    public BorderPane getRoot(){
        return this.root;
    }

    public Stage getWindow(){
        return this.stage;
    }


    public void connecter() {   
        try {
            String identifiant = getIdentifiant();
            String motDePasse = getMotDePasse();
            BdTriSLN bd = TriSLN.getBd();
            if (bd.verifConnexion(identifiant, motDePasse)){
                Stage StageVue = new Stage();
                this.root = FXMLLoader.load(getClass().getResource("SAEprojetAccueilConnecter.fxml"));
                Scene scene = new Scene(this.root);
                StageVue.setScene(scene);
                StageVue.show();
            }
            else
            {
                this.mdp.setText("");
                Text t = new Text("Identifiant ou Mot de passe incorrect.");
                t.setFill(Color.RED);
                this.root.getChildren().add(t);
    
            }
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        
    }

    public void afficheLogin(FXMLLoader loader){
        try {
            this.root = (BorderPane) loader.load();
            Scene page;
            page = new Scene(this.root);
            this.stage.setScene(page);
            this.stage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}