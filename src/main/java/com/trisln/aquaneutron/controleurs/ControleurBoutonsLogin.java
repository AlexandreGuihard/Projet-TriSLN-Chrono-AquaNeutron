package com.trisln.aquaneutron.controleurs;

import java.io.IOException;
import java.sql.SQLException;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import com.trisln.aquaneutron.vue.TriSLN;
import com.trisln.aquaneutron.modele.Exceptions.NoSuchUserException;

/**
 * Classe ControleurBoutonsLogin.
 * Permet de gérer les boutons de la vue Login.
 */
public class ControleurBoutonsLogin extends ControleurBoutons implements EventHandler<ActionEvent>{
    private TriSLN vue;

    @FXML
    private Button btnConnecter;
    @FXML
    private Button btnAccueil;
    @FXML
    private Button btnRetour;
    @FXML
    private TextField idIdentifiant;
    @FXML
    private TextField idMdp;
    @FXML
    private Label idInfoLabel;

    /**
     * Constructeur de la classe ControleurBoutonsLogin.
     * Initialise les boutons de la vue Login.
     * @param vue La vue de l'application.
     */
    public ControleurBoutonsLogin(TriSLN vue){
        super();
        this.setBoutons(vue);
    }

    /**
     * Permet de définir les boutons de la vue Login.
     * @param vue La vue de l'application.
     */
    private void setBoutons(TriSLN vue){
        super.setVue(vue);
        super.setBRetour(btnRetour);
        super.setBAccueil(btnAccueil);
    }

    /**
     * Permet de gérer les événements liés aux boutons de la vue Login.
     * @param event L'événement lié au bouton.
     */
    @FXML
    public void onKeyTyped(KeyEvent event) {
        TextField tf = (TextField) event.getSource();
        if (event.getCode() == KeyCode.ENTER) {
            if (tf.getId().equals("idIdentifiant")) {
                this.idMdp.requestFocus();
            } else if (tf.getId().equals("idMdp")) {
                this.btnConnecter.fire();
            }
        }
    }

    /**
     * Permet de gérer les événements liés aux boutons de la vue Login.
     * @param event L'événement lié au bouton.
     * @throws Exception Si une erreur survient.
     */
    @FXML
    public void handleBtnLoginMouseEntered(MouseEvent event){
        try{
            Button btn=(Button)event.getSource();
            if(btn.getId().equals("btnConnecter")){
                super.getVue().changeButtonColor(this.btnConnecter, "#105c74", "");
            }
            else{
                super.handleBtnsMouseEntered(btn);
            }
        }
        catch(Exception e){
            System.err.println("Erreur");
            e.printStackTrace();
        }
    }

    /**
     * Permet de gérer les événements liés aux boutons de la vue Login.
     * @param event L'événement lié au bouton.
     * @throws Exception Si une erreur survient.
     */
    @FXML
    public void handleBtnLoginMouseExited(MouseEvent event){
        try{
            Button btn=(Button)event.getSource();
            if(btn.getId().equals("btnConnecter")){
                super.getVue().changeButtonColor(this.btnConnecter, "#2596BE", "");
            }
            else{
                super.handleBtnsMouseExited(btn);
            }
        }
        catch(Exception e){
            System.err.println("Erreur");
            e.printStackTrace();
        }
    }

    /**
     * Permet de gérer les événements liés aux boutons de la vue Login.
     * @param event L'événement lié au bouton.
     * @throws Exception Si une erreur survient.
     */
    @Override
    public void handle(ActionEvent event) {
        Button btn = (Button) event.getSource();
        switch (btn.getId()) {
            case "btnAccueil":
                System.out.println("Clique accueil");
                try {
                    super.getVue().afficheAccueil();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "btnConnecter":
                System.out.println("Clique connection");
                try {
                    super.getVue().getUtilisateur().connecter(this.idIdentifiant.getText(), this.idMdp.getText());
                    idInfoLabel.setText("Identifié en tant que " + super.getVue().getUtilisateur().getRole());
                    super.getVue().afficheAccueilConnecte();
                    super.getVue().setUtilisateur(TriSLN.getBd().getUtilisateurFromIdentifiant(this.idIdentifiant.getText()));
                }
                catch (SQLException e) {
                    e.printStackTrace();
                } 
                catch (NoSuchUserException e){
                    idInfoLabel.setText("Cette utilisateur n'existe pas");
                    e.printStackTrace();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                break;
            case "btnForgotPassword":
                System.out.println("Clique mot de passe oublié");
                try {
                    super.getVue().affichePopUpLogin();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                super.handle(btn);
                break;
        }
    }
}