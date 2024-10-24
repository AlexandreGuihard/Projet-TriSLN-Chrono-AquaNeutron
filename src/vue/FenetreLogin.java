import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class FenetreLogin {


    private TextField identifier;
    private PasswordField mdp;
    private Button bConnexion;     

    public FenetreLogin(){

        this.identifier = new TextField();       
        this.mdp = new PasswordField();
        this.bConnexion = new Button();
    }

    public String getIdentifiant() {
        return this.identifier.getText();
    }

    public void setIdentifiant(TextField identifiant) {
        this.identifier= identifiant;
    }

    public String getMotDePasse() {
        return this.mdp.getText();
    }

    public void setMotDePasse(PasswordField motDePasse){
        this.mdp = motDePasse;
    }

    public Button getBoutonConnexion() {
        return this.bConnexion;
    }

    public void setBoutonConnexion(Button bouton){
        this.bConnexion = bouton;
    }

    public void connecter() {
        
    }
}
