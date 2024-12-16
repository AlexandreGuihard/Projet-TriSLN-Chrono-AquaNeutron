package src.controleurs;

import javafx.event.EventHandler;

import java.io.IOException;

import javax.swing.ButtonGroup;
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


public class ControleurBoutonsNouvelleCourses extends ControleurBoutons implements EventHandler<ActionEvent> {
    private TriSLN vue;

    @FXML
    private Button btnAjoutCourse;
    @FXML
    private Button btnAccueil;
    @FXML
    private Button btnRetour;
    @FXML
    private Button btnDeconnexion;
    @FXML
    private Button btnCompte;


    public ControleurBoutonsNouvelleCourses(TriSLN vue){
        super();
        this.setBoutons(vue);
    }

    private void setBoutons(TriSLN vue){
        super.setVue(vue);
        super.setBAccueil(btnAccueil);
        super.setBCompte(btnCompte);
        super.setBDeconnexion(btnDeconnexion);
        super.setBRetour(btnRetour);
    }

    @FXML
    public void handleBtnCoursesMouseEntered(MouseEvent event){
        try{
            Button btn=(Button)event.getSource();
            if(btn.getId().equals("btnAjoutCourse")){
                super.getVue().changeButtonColor(this.btnAjoutCourse, "#105c74", "");
            }
            else{
                super.handleBtnsMouseEntered(btn);
            }
            //switch(btn.getId()){
            //    case "btnAjoutCourse":
            //        this.vue.changeButtonColor(this.btnAjoutCourse, "#105c74", null);
            //        break;
            //    case "btnAccueil":
            //        this.vue.changeButtonColor(this.btnAccueil, "#949494", "-fx-background-radius: 15");
            //        break;
            //    case "btnDeconnexion":
            //        this.vue.changeButtonColor(this.btnDeconnexion, "#949494", "-fx-background-radius: 15");
            //        break;
            //    case "btnCompte":
            //        this.vue.changeButtonColor(this.btnCompte, "#949494", "-fx-background-radius: 15");
            //        break;
            //}
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
            if(btn.getId().equals("btnAjoutCourse")){
                super.getVue().changeButtonColor(this.btnAjoutCourse, "#2596BE", "");
            }
            else{
                super.handleBtnsMouseExited(btn);
            }
            //switch(btn.getId()){
            //    case "btnAjoutCourse":
            //        this.vue.changeButtonColor(this.btnAjoutCourse, "#2596BE", "");
            //        break;
            //    case "btnAccueil":
            //        this.vue.changeButtonColor(this.btnAccueil, "lightgrey", "-fx-background-radius: 15");
            //        break;
            //    case "btnDeconnexion":
            //        this.vue.changeButtonColor(this.btnDeconnexion, "lightgrey", "-fx-background-radius: 15");
            //        break;
            //    case "btnCompte":
            //        this.vue.changeButtonColor(this.btnCompte, "lightgrey", "-fx-background-radius: 15");
            //        break;
            //}
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
            if(btn.getId().equals("btnAjoutCourse")){
                System.out.println("Course ajoutée");
                super.getVue().afficheCourses();
            }
            else{
                super.handle(btn);
            }
            //switch(btn.getId()){
            //    case "btnAjoutCourse":
            //        System.out.println("Ajouter course");
            //        this.vue.afficheNvlCourse();
            //        break;
            //    case "btnRetour":
            //        System.out.println("Retour");
            //        this.vue.afficheCourses();
            //        break;
            //    case "btnAccueil":
            //        System.out.println("Accueil");
            //        this.vue.afficheAccueilConnecte();
            //        break;
            //    case "btnCompte":
            //        System.out.println("Mon compte");
            //        this.vue.afficheMonCompte();
            //        break;
            //    case "btnDeconnexion":
            //        System.out.println("Déconnexion");
            //        this.vue.afficheAccueil();
            //        break;
            //}
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        } 
    }
}
