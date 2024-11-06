package src.controleurs;

import javafx.event.EventHandler;

import java.io.IOException;

import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;
import javax.swing.plaf.synth.SynthStyle;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import src.vue.TriSLN;


public class ControleurBoutonsCourses implements EventHandler<ActionEvent> {
    private TriSLN vue;

    @FXML
    private Button btnNvlCourse;
    @FXML
    private Button btnAccueil;
    @FXML
    private Button btnRetour;

    public ControleurBoutonsCourses(TriSLN vue){
        this.vue = vue;
        this.vue.setBNvlCourse(this.btnNvlCourse);
        this.vue.setBAccueil(this.btnAccueil);
        this.vue.setBRetour(this.btnRetour);
    }

    @FXML
    public void handleBtnCoursesMouseEntered(MouseEvent event){
        try{
            Button btn=(Button)event.getSource();
            switch(btn.getId()){
                case "btnNvlCourse":
                    this.vue.changeButtonColor(this.btnNvlCourse, "#105c74", null);
                    break;
            }
            
        }
        catch(Exception e){
            System.err.println("Erreur");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleBtnCoursesMouseExited(MouseEvent event){
        try{
            Button btn=(Button)event.getSource();
            switch(btn.getId()){
                case "btnNvlCourse":
                    this.vue.changeButtonColor(this.btnNvlCourse, "#2596BE", null);
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
        try {
            Button btn=(Button) event.getSource();
            switch(btn.getId()){
                case "btnNvlCourse":
                    System.out.println("Nouvelle Course");
                    this.vue.afficheNvlCourse();
                    break;
                case "btnRetour":
                    System.out.println("Retour");
                    this.vue.afficheAccueilConnecte();
                    break;
                case "btnAccueil":
                    System.out.println("Accueil");
                    this.vue.afficheAccueilConnecte();
                    break;
            }
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        } 
    }
}