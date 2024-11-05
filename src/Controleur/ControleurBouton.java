package src.Controleur;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import src.vue.TriSLN;

public class ControleurBouton implements EventHandler<ActionEvent>{
    private TriSLN vue;

    //pop up senior
        @FXML
        private Button btnRetourSC;
        @FXML
        private Button choiceVetSCCB;
        @FXML
        private Button btnValiderSC;
    //
    //pop up veterant
        @FXML
        private Button choiceSenSCCB;
    //

    public ControleurBouton(TriSLN vue){
        this.vue = vue;
        //this.vue.setBClassements(this.btnClassements);
    }

    @Override
    public void handle(ActionEvent event){
        Button btn=(Button) event.getSource();
        switch(btn.getText()){
            case "Classements":
                System.out.println("Classement");
                break;
            default:
                System.out.println("Accueil");
                break;
        }
    }
}