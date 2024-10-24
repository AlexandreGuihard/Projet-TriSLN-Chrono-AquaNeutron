

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;





public class Controleur_button_accuiel {
    
    @FXML
    private Button btnConnexion; 
    
    @FXML
    private Button btnPaticipants; 
    @FXML
    private Button btnCourses; 
    
    @FXML
    private Button btnClassements; 
    
    @FXML
    private Button btnDeconnexion; 
    



    @FXML
    private void handleButtonAction(ActionEvent event) {
    Object o = event.getSource();
    String nomboutton = String.valueOf(o);

    //blabla
    
}
}


