package src.controleurs;

import javafx.event.EventHandler;

import java.io.IOException;

import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import src.vue.TriSLN;


public class ControleurBoutonsNouvelleCourses implements EventHandler<ActionEvent> {
    private TriSLN vue;

    @FXML
    private Button btnAJtCourse;
    @FXML
    private Button btnAccueil;

    public ControleurBoutonsNouvelleCourses(TriSLN vue){
        this.vue = vue;
        this.vue.setBAJtCourse(this.btnAJtCourse);
        this.vue.setBAccueil(this.btnAccueil);
    }

    @FXML
    public void handleBtnCoursesMouseEntered(MouseEvent event){
        try{
            Button btn=(Button)event.getSource();
            switch(btn.getId()){
                case "btnAJtCourse":
                    this.vue.changeButtonColor(this.btnAJtCourse, "#105c74", null);
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
                case "btnAJtCourse":
                    this.vue.changeButtonColor(this.btnAJtCourse, "#2596BE", null);
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
                case "btnAJtCourse":
                    System.out.println("Ajouter course");
                    this.vue.afficheNvlCourse();
                    break;
                case "btnRetour":
                    System.out.println("Retour");
                    this.vue.afficheCourses();
                    break;
                case "btnAccueil":
                    System.out.println("Accueil");
                    this.vue.afficheAccueilConnecte();
            }
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        } 
    }
}
