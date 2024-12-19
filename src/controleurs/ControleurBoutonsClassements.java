package src.controleurs;

import src.vue.TriSLN;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;

public class ControleurBoutonsClassements implements EventHandler<ActionEvent> {
    private TriSLN vue;

    @FXML
    private Button btnConnexion;
    @FXML
    private Button btnAccueil;
    @FXML
    private Button btnRetour;
    @FXML
    private Button btnAccueil2;
    @FXML
    private Button btnRetour2;
    @FXML
    private Button deconnecter;
    @FXML
    private Button btnCompte;
    @FXML
    private Button genpdf;
    @FXML
    private ComboBox<String> idSC;
    @FXML
    private ComboBox<String> idG;
    @FXML
    private Button btnValider;

    public ControleurBoutonsClassements(TriSLN vue) {
        this.vue = vue;
    }

    public void handleComboBoxCategorie(ActionEvent event){
        if(!this.idSC.getValue().equals("-- Choisir une sous-catégorie --")){
            this.btnValider.setDisable(false);
        }
        else{
            this.btnValider.setDisable(true);
        }
    }

    public void handleComboBoxGenre(ActionEvent event){
        if(!this.idG.getValue().equals("-- Choisir un genre --")){
            this.btnValider.setDisable(false);
        }
        else{
            this.btnValider.setDisable(true);
        }
    }

    @FXML
    public void handleBtnClassementMouseEntered(MouseEvent event) {
        try {
            Button btn = (Button) event.getSource();
            switch (btn.getId()) {
                case "btnConnexion":
                    this.vue.changeButtonColor(this.btnConnexion, "#949494", "-fx-background-radius: 15");
                    break;
                case "btnAccueil":
                    this.vue.changeButtonColor(this.btnAccueil, "#949494", "-fx-background-radius: 15");
                    break;
                case "btnRetour":
                    this.vue.changeButtonColor(this.btnRetour, "lightgrey", "-fx-background-radius: 15");
                    break;
                case "btnAccueil2":
                    this.vue.changeButtonColor(this.btnAccueil2, "#949494", "-fx-background-radius: 15");
                    break;
                case "btnRetour2":
                    this.vue.changeButtonColor(this.btnRetour2, "lightgrey", "-fx-background-radius: 15");
                    break;
                case "deconnecter":
                    this.vue.changeButtonColor(this.deconnecter, "#949494", "-fx-background-radius: 15");
                    break;
                case "btnCompte":
                    this.vue.changeButtonColor(this.btnCompte, "#949494", "-fx-background-radius: 15");
                    break;
                case "genpdf":
                    this.vue.changeButtonColor(this.genpdf, "#949494", "-fx-background-radius: 15");
                    break;
                case "btnValider":
                    this.vue.changeButtonColor(this.btnValider, "#949494", "-fx-background-radius: 15");
                    break;
            }
        } catch (Exception e) {
            System.err.println("Erreur lors du survol du bouton (handleBtnClassementMouseEntered): " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void handleBtnClassementMouseExited(MouseEvent event) {
        try {
            Button btn = (Button) event.getSource();
            switch (btn.getId()) {
                case "btnConnexion":
                    this.vue.changeButtonColor(this.btnConnexion, "lightgrey", "-fx-background-radius: 15");
                    break;
                case "btnAccueil":
                    this.vue.changeButtonColor(this.btnAccueil, "lightgrey", "-fx-background-radius: 15");
                    break;
                case "btnRetour":
                    this.vue.changeButtonColor(this.btnRetour, "white", "-fx-background-radius: 15");
                    break;
                case "btnAccueil2":
                    this.vue.changeButtonColor(this.btnAccueil2, "lightgrey", "-fx-background-radius: 15");
                    break;
                case "btnRetour2":
                    this.vue.changeButtonColor(this.btnRetour2, "white", "-fx-background-radius: 15");
                    break;
                case "deconnecter":
                    this.vue.changeButtonColor(this.deconnecter, "lightgrey", "-fx-background-radius: 15");
                    break;
                case "btnCompte":
                    this.vue.changeButtonColor(this.btnCompte, "lightgrey", "-fx-background-radius: 15");
                    break;
                case "genpdf":
                    this.vue.changeButtonColor(this.genpdf, "lightgrey", "-fx-background-radius: 15");
                    break;
                case "btnValider":
                    this.vue.changeButtonColor(this.btnValider, "lightgrey", "-fx-background-radius: 15");
                    break;
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la sortie du survol du bouton (handleBtnClassementMouseExited): " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            Button btn = (Button) event.getSource();
            switch (btn.getId()) {
                case "btnAccueil":
                    this.vue.afficheAccueil();
                    break;
                case "btnRetour":
                    this.vue.afficheAccueil();
                    break;
                case "btnAccueil2":
                    this.vue.afficheAccueilConnecte();
                    break;
                case "btnRetour2":
                    this.vue.afficheAccueilConnecte();
                    break;
                case "btnConnexion":
                    this.vue.afficheLogin();
                    break;
                case "deconnecter":
                    this.vue.afficheAccueil();
                    break;
                case "btnCompte":
                    this.vue.afficheMonCompte();
                    break;
                case "genpdf":
                    if (this.idSC.getValue() != null && !this.idSC.getValue().equals("-- Choisir une sous-catégorie --")) {
                        if (this.idG.getValue() != null && !this.idG.getValue().equals("-- Choisir un genre --")) {
                            this.vue.affichePDF("servinfo-maria", "guihard", "guihard", "DBguihard", this.idSC.getValue(), this.idG.getValue());
                        } else {
                            System.err.println("Aucune sélection de genre.");
                        }
                    } else {
                        System.err.println("Aucune sélection de sous-catégorie.");
                    }
                    break;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
