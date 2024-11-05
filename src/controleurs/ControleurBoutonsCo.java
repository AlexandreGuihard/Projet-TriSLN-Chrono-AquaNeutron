package src.controleurs;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import src.vue.TriSLN;

public class ControleurBoutonsCo implements EventHandler<ActionEvent>{
    private TriSLN vue;

    // log accueil et AccConnecter
    @FXML
    private Button btnClassements;
    @FXML
    private Button btnConnexion;
    @FXML
    private Button btnParticipants;
    @FXML
    private Button btnCourses;
    @FXML
    private Button btnDeconnexion;

    public ControleurBoutonsCo(TriSLN vue){
        this.vue = vue;
        this.vue.setBClassements(this.btnClassements);
        this.vue.setBConnexion(this.btnConnexion);
        this.vue.setBParticipants(this.btnParticipants);
        this.vue.setBCourses(this.btnCourses);
        this.vue.setBDeconnexion(this.btnDeconnexion);
    }

    @FXML
    public void handleBtnAccueilMouseEntered(MouseEvent event){
        try{
            Button btn=(Button)event.getSource();
            switch(btn.getText()){
                case "Classements":
                    this.vue.changeButtonColor(this.btnClassements, "#105c74", null);
                    break;
                case "Participants":
                    this.vue.changeButtonColor(this.btnParticipants, "#105c74", null);
                    break;
                case "Courses":
                    this.vue.changeButtonColor(this.btnCourses, "#105c74", null);    
                    break;
                case "Se connecter":
                    System.out.println(btn.getText());
                    this.vue.changeButtonColor(this.btnConnexion, "#949494", "-fx-background-radius: 15");
                    break;
                case "Deconnecter":
                    this.vue.changeButtonColor(this.btnDeconnexion, "#949494", "-fx-background-radius: 15");
                    break;
            }
            
        }
        catch(Exception e){
            System.err.println("Erreur");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleBtnAccueilMouseExited(MouseEvent event){
        try{
            Button btn=(Button)event.getSource();
            switch(btn.getText()){
                case "Classements":
                    this.vue.changeButtonColor(this.btnClassements, "#2596BE", null);
                    break;
                case "Participants":
                    this.vue.changeButtonColor(this.btnParticipants, "#2596BE", null);
                    break;
                case "Courses":
                    this.vue.changeButtonColor(this.btnCourses, "#2596BE", null);    
                    break;
                case "Se connecter":
                    this.vue.changeButtonColor(this.btnConnexion, "lightgrey", "-fx-background-radius: 15");
                    break;
                case "Deconnecter":
                    this.vue.changeButtonColor(this.btnDeconnexion, "lightgrey", "-fx-background-radius: 15");
                    break;
            }
        }
        catch(Exception e){
            System.err.println("Erreur");
            e.printStackTrace();
        }
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