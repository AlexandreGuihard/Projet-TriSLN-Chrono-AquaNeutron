package com.trisln.aquaneutron.controleurs;

import java.io.IOException;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import com.trisln.aquaneutron.vue.TriSLN;

public abstract class ControleurBoutons {
    private TriSLN vue;
    @FXML
    private Button btnAccueil;
    @FXML
    private Button btnRetour;
    @FXML
    private Button btnDeconnexion;
    @FXML
    private Button btnCompte;
    @FXML
    private Button btnConnexion;
    @FXML
    private Button btnAccueilDisconnected;

    /**
     * Constructeur appelé par les enfants de cette classe. Initialise tous les attributs à null
     */
    public ControleurBoutons(){
        this.vue=null;
        this.btnAccueil=null;
        this.btnRetour=null;
        this.btnDeconnexion=null;
        this.btnCompte=null;
        this.btnConnexion=null;
        this.btnAccueilDisconnected=null;
    }

    /**
     * Getter de la vue
     * @return la vue
     */
    public TriSLN getVue(){
        return this.vue;
    }

    /**
     * Setter de la vue
     * @param vue la vue
     */
    public void setVue(TriSLN vue){
        this.vue = vue;
    }

    /**
     * Getter du bouton de déconnexion
     * @return le bouton de déconnexion
     */
    public Button getBDeconnexion(){
        return this.btnDeconnexion;
    }

    /**
     * Getter du bouton d'accueil lorsque l'utilisateur est déconnecté
     * @return le bouton d'accueil
     */
    public Button getBAccueilDisconnected(){
        return this.btnAccueilDisconnected;
    }

    /**
     * Getter du bouton d'accueil
     * @return le bouton d'accueil
     */
    public Button getBAccueil(){
        return this.btnAccueil;
    }

    /**
     * Getter du bouton retour
     * @return le bouton retour
     */
    public Button getBRetour(){
        return this.btnRetour;
    }

    /**
     * Getter du bouton du compte
     * @return le bouton du compte
     */
    public Button getBCompte(){
        return this.btnCompte;
    }

    /**
     * Getter du bouton de déconnexion
     * @return le bouton de déconnexion
     */
    public Button getBConnexion(){
        return this.btnConnexion;
    }

    /**
     * Setter du bouton de connexion
     * @param btnConnexion le bouton de déconnexion
     */
    public void setBConnexion(Button btnConnexion){
        this.btnConnexion=btnConnexion;
    }

    /**
     * Setter du bouton de déconnexion
     * @param btnDeconnexion le bouton de déconnexion
     */
    public void setBDeconnexion(Button btnDeconnexion){
        this.btnDeconnexion=btnDeconnexion;
    }

    /**
     * Setter du bouton d'accueil lorsque l'utilisateur est déconnecté
     * @param btnAccueilDisconnected le bouton d'accueil
     */
    public void setBAccueilDisconnected(Button btnAccueilDisconnected){
        this.btnAccueilDisconnected = btnAccueilDisconnected;
    }

    /**
     * Setter du bouton d'accueil
     * @param btnAccueil le bouton d'accueil
     */
    public void setBAccueil(Button btnAccueil){
        this.btnAccueil=btnAccueil;
    }

    /**
     * Setter du bouton retour
     * @param btnRetour le bouton retour
     */
    public void setBRetour(Button btnRetour){
        this.btnRetour=btnRetour;
    }
    
    /**
     * Setter du bouton du compte
     * @param btnCompte le bouton compte
     */
    public void setBCompte(Button btnCompte){
        this.btnCompte=btnCompte;
    }

    /**
     * Méthode appelée lorsqu'on passe la souris sur un bouton
     * @param button le bouton
     */
    public void handleBtnsMouseEntered(Button button){
        Button changedButton=null;
        String newBtnColor="#949494";
        String otherStyle="-fx-background-radius: 15;";
        switch(button.getId()){
            case "btnCompte":
                changedButton=this.btnCompte;
                break;
            case "btnAccueil":
                changedButton=this.btnAccueil;
                break;
            case "btnAccueilDisconnected":
                changedButton=this.btnAccueilDisconnected;
                break;
            case "btnConnexion":
                changedButton=this.btnConnexion;
                break;
            case "btnDeconnexion":
                changedButton=this.btnDeconnexion;
                break;
            default:
                System.out.println("Btn retour exited");
                changedButton=this.btnRetour;
                newBtnColor="";
                otherStyle="";
                break;             
        }
        this.vue.changeButtonColor(changedButton, newBtnColor, otherStyle);
    }

    /**
     * Méthode appelée lorsqu'on enlève la souris d'un bouton
     * @param button le bouton
     */
    public void handleBtnsMouseExited(Button button){
        Button changedButton=null;
        String newBtnColor="lightgrey";
        String otherStyle="-fx-background-radius: 15;";
        switch(button.getId()){
            case "btnCompte":
                changedButton=this.btnCompte;
                break;
            case "btnAccueil":
                changedButton=this.btnAccueil;
                break;
            case "btnAccueilDisconnected":
                changedButton=this.btnAccueilDisconnected;
                break;
            case "btnConnexion":
                changedButton=this.btnConnexion;
                break;
            case "btnDeconnexion":
                changedButton=this.btnDeconnexion;
                break;
            default:
                System.out.println("Btn retour exited");
                changedButton=this.btnRetour;
                newBtnColor="";
                otherStyle="";
                break;             
        }
        this.vue.changeButtonColor(changedButton, newBtnColor, otherStyle);
    }

    /**
     * Méthode appelée lorsqu'on appuie sur un bouton
     * @param pressedButton le bouton appuyé
     */
    public void handle(Button pressedButton){
        try{
            switch(pressedButton.getId()){
                case "btnCompte":
                    this.vue.afficheMonCompte();
                    break;
                case "btnConnexion":
                    this.vue.afficheLogin();
                    break;    
                case "btnDeconnexion" :
                    this.vue.afficheAccueil();
                    break;
                
                case "btnAccueil":
                    this.vue.afficheAccueilConnecte();
                    break;

                case "btnAccueilDisconnected":
                    this.vue.afficheAccueil();
                    break;

                default:
                    this.vue.afficheRetour(); 
                    break;           
            }
        }catch (IOException e){
            e.printStackTrace();
        } 
    }
}
