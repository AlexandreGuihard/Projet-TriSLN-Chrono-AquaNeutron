package src.vue;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import src.vue.TriSLN;

public class ControleurBoutons implements EventHandler<ActionEvent>{
    private TriSLN vue;
    @FXML
    private Button btnClassements;

    public ControleurBoutons(TriSLN vue){
        this.vue=vue;
        this.vue.setBClassements(this.btnClassements);
    }

    @FXML
    public void handleBtnAccueil(MouseEvent event){
        try{
            this.vue.changeButtonColor(this.btnClassements);
        }
        catch(Exception e){
            System.err.println("Erreur");
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