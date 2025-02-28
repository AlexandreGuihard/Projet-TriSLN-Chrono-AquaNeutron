package com.trisln.aquaneutron.vue;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import com.trisln.aquaneutron.bd.*;

/**
 * Classe FenetreLogin.
 * Permet d'afficher la fenêtre de connexion.
 */
public class FenetreLogin {

    private BorderPane root;
    private TextField identifier;
    private PasswordField mdp;
    private Button bConnexion;
    private Stage stage;     

    /**
     * Constructeur de la classe FenetreLogin.
     * Initialise les composants de l'interface et affiche la fenêtre de connexion.
     * @param loader Le FXMLLoader utilisé pour charger la vue.
     * @param stage La fenêtre principale de l'application.
     */
    public FenetreLogin(FXMLLoader loader, Stage stage){
        this.root = new BorderPane();
        this.identifier = new TextField();       
        this.mdp = new PasswordField();
        this.bConnexion = new Button();
        this.stage = stage;
        this.afficheLogin(loader);
    }

    /**
     * Getter pour avoir l'identifiant
     * @return l'identifiant
     */
    public String getIdentifiant() {
        return this.identifier.getText();
    }

    /**
     * Setter pour modifier l'identifiant
     * @param identifiant l'identifiant
     */
    public void setIdentifiant(TextField identifiant) {
        this.identifier= identifiant;
    }

    /**
     * Getter pour avoir le mot de passe
     * @return le mot de passe
     */
    public String getMotDePasse() {
        return this.mdp.getText();
    }

    /**
     * Setter pour modifier le mot de passe
     * @param motDePasse le mot de passe
     */
    public void setMotDePasse(PasswordField motDePasse){
        this.mdp = motDePasse;
    }

    /**
     * Getter pour avoir le bouton de connexion
     * @return le bouton de connexion
     */
    public Button getBoutonConnexion() {
        return this.bConnexion;
    }

    /**
     * Setter pour modifier le bouton de connexion
     * @param bouton le bouton de connexion
     */
    public void setBoutonConnexion(Button bouton){
        this.bConnexion = bouton;
    }

    /**
     * Getter pour avoir la racine
     * @return la racine
     */
    public BorderPane getRoot(){
        return this.root;
    }

    /**
     * Getter pour avoir la fenêtre
     * @return la fenêtre
     */
    public Stage getWindow(){
        return this.stage;
    }

    /**
     * Méthode pour se connecter
     * @throws Exception si une erreur survient
     */
    public void connecter() {   
        try {
            String identifiant = getIdentifiant();
            String motDePasse = getMotDePasse();
            BdTriSLN bd = TriSLN.getBd();
            if (bd.verifConnexion(identifiant, motDePasse)){
                this.root = FXMLLoader.load(getClass().getResource("SAEprojetAccueilConnecter.fxml"));
                Scene scene = new Scene(this.root);
                this.stage.setScene(scene);
                this.stage.show();
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

    /**
     * Méthode pour afficher la fenêtre de connexion
     * @param loader le loader
     * @throws Exception si une erreur survient
     */
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