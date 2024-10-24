
import javafx.scene.Scene;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Fenetre_Accueil {
    
    FXMLLoader loader;

    BorderPane acceuill;


    public Fenetre_Accueil(Stage prymaryStage){
        try{
        if (connecter == false){
                     
            BorderPane acceuill = (BorderPane) FXMLLoader.load(getClass().getResource("src/SAEprojetAccueil.fxml"));
        }
        else{
            BorderPane acceuill = (BorderPane) FXMLLoader.load(getClass().getResource("src/SAEprojetAccueilConnecter.fxml"));
            }
            Scene scene = new Scene(acceuill);
            prymaryStage.setScene(scene);
            prymaryStage.setTitle("acceuil");
            prymaryStage.show();
        
        

        }
        catch(Exception e){
            System.out.println("acceuill non trouver");
        }
    }


}
