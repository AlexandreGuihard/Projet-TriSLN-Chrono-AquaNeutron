package src.Controleur;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import src.vue.TriSLN;

public class ControleurBoutonCo implements EventHandler<ActionEvent>{
    private TriSLN vue;

// log accuiel et AccConnecter
@FXML
private Button btnClassements;
@FXML
private Button btnConnexion;
@FXML
private Button btnPaticipants;
@FXML
private Button btnCourses;
@FXML
private Button btnDeconnexion;
//

public ControleurBoutonCo(TriSLN vue){
    this.vue = vue;
    this.vue.setBClassements(this.btnClassements);
    this.vue.setBConnexion(btnConnexion);
    this.vue.setBParticipants(btnPaticipants);
    this.vue.setBCourses(btnCourses);
    this.vue.setBDeconnexion(btnDeconnexion);

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