package src.controleurs;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import src.vue.TriSLN;

import javax.swing.plaf.synth.SynthStyle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ControleurBoutonsPopUp implements EventHandler<ActionEvent>{
    private TriSLN vue;

    @FXML
    private Button btnRetourPopUp;
    @FXML
    private Button btnValiderPopUp;
    // ComboBox des popup
    @FXML
    private ComboBox<String> choiceSeniorsSCateg;
    @FXML
    private ComboBox<String> choiceVeterantSCateg;

public ControleurBoutonsPopUp(TriSLN vue){
    this.vue = vue;
    this.choiceSeniorsSCateg=new ComboBox<>();
    this.choiceVeterantSCateg=new ComboBox<>();
    this.setComboBox();
}

public void handleBtnValiderMouseEntered(MouseEvent event){
    this.vue.changeButtonColor(this.btnValiderPopUp, "#105c74", null);
}

public void handleBtnValiderMouseExited(MouseEvent event){
    this.vue.changeButtonColor(this.btnValiderPopUp, "#2596BE", null);
}

private void setComboBox(){
    this.choiceSeniorsSCateg.getItems().addAll("-- Choisir une sous-catégorie --", "S1", "S2", "S3", "S4");
    this.choiceVeterantSCateg.getItems().addAll("-- Choisir une sous-catégorie --", "V1", "V2", "V3", "V4", "V5", "V6", "V7");
}

@Override
public void handle(ActionEvent event){
    Button btn=(Button) event.getSource();
    switch(btn.getId()){
        case "btnRetourPopUp":
            System.out.println("toto");
            this.vue.closePopUpStage();
            break;
        case "btnValiderPopUp":
            this.vue.closePopUpStage();
            this.vue.afficheLesParticipants(); 
            break;  
    }
}
}