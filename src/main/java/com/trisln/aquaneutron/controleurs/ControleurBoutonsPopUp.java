package com.trisln.aquaneutron.controleurs;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import com.trisln.aquaneutron.vue.TriSLN;

public class ControleurBoutonsPopUp implements EventHandler<ActionEvent>{
    private TriSLN vue;

    @FXML
    private Button btnRetourPopUp;
    @FXML
    private Button btnValiderPopUp;
    @FXML
    private ComboBox<String> choiceSenSCategSeniors;
    @FXML
    private ComboBox<String> choiceVeterantSCateg;
    private String categorieChoisie;
    private String sousCategorieChoisie;

    /**
     * Constructeur de la classe
     * @param vue la vue
     * @param categorieChoisie la catégorie choisie
     */
    public ControleurBoutonsPopUp(TriSLN vue, String categorieChoisie){
        this.vue = vue;
        this.categorieChoisie=categorieChoisie;
        this.sousCategorieChoisie=null;
    }

    /**
     * Méthode appelée lorsqu'on passe la souris sur le bouton de validation de la sous catégorie
     * @param event l'évènement déclenché par la souris
     */
    public void handleBtnValiderMouseEntered(MouseEvent event){
        this.vue.changeButtonColor(this.btnValiderPopUp, "#105c74", "");
    }

    /**
     * Méthode appelée lorsque la souris quitte le bouton de validation de la sous catégorie
     * @param event l'évènement déclenché par la souris
     */
    public void handleBtnValiderMouseExited(MouseEvent event){
        this.vue.changeButtonColor(this.btnValiderPopUp, "#2596BE", "");
    }

    /**
     * Méthode appelée pour activer ou désactiver le bouton valider de la popup des séniors
     * @param event l'évènement déclenché
     */
    public void handleComboBoxSeniors(ActionEvent event){
        if(!this.choiceSenSCategSeniors.getValue().equals("-- Choisir une sous-catégorie --")){
            this.btnValiderPopUp.setDisable(false);
        }
        else{
            this.btnValiderPopUp.setDisable(true);
        }
    }

    /**
     * Méthode appelée pour activer ou désactiver le bouton valider de la popup des vétérants
     * @param event l'évènement déclenché
     */
    public void handleComboBoxVeterant(ActionEvent event){
        if(!this.choiceVeterantSCateg.getValue().equals("-- Choisir une sous-catégorie --")){
            this.btnValiderPopUp.setDisable(false);
        }
        else{
            this.btnValiderPopUp.setDisable(true);
        }
    }

    /**
     * Méthode appelée lorsqu'on appuie sur un bouton
     * @param event l'évènement déclenché
     */
    @Override
    public void handle(ActionEvent event){
        Button btn=(Button) event.getSource();
        switch(btn.getId()){
            case "btnRetourPopUp":
                this.vue.closePopUpStage();
                break;
            case "btnValiderPopUp":
                this.vue.closePopUpStage();
                if(choiceSenSCategSeniors!=null){
                    sousCategorieChoisie=choiceSenSCategSeniors.getValue();
                }
                else{
                    sousCategorieChoisie=choiceVeterantSCateg.getValue();
                }
                this.vue.afficheLesParticipants(categorieChoisie, sousCategorieChoisie); 
                break;  
        }
    }
}