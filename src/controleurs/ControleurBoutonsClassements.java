package src.controleurs;

import src.vue.TriSLN;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import src.vue.*;


public class ControleurBoutonsClassements extends ControleurBoutons implements EventHandler<ActionEvent>{
    private TriSLN vue;

    @FXML
    private Button btnConnexion;
    @FXML
    private Button btnAccueil;
    @FXML
    private Button btnRetour;
    @FXML
    private Button btnDeconnexion;
    @FXML
    private Button btnCompte;
    @FXML
    private Button btnAccueilDisconnected;

    public ControleurBoutonsClassements(TriSLN vue){
        super();
        this.setBoutons(vue);
    }

    private void setBoutons(TriSLN vue){
        super.setVue(vue);
        super.setBAccueil(btnAccueil);
        super.setBDeconnexion(btnDeconnexion);
        super.setBCompte(btnCompte);
        super.setBRetour(btnRetour);
        super.setBAccueil(btnAccueilDisconnected);
    }
    
    @FXML
    public void handleBtnClassementMouseExited(MouseEvent event){
        try{
            boolean superButton=false;
            Button changedButton=null;
            String newBtnColor="";
            String otherStyle="";
            Button btn=(Button)event.getSource();
            if(btn.getId().equals("btnAccueil") ||btn.getId().equals("btnAccueilDisconnected") || btn.getId().equals("btnRetour") || btn.getId().equals("btnCompte") || btn.getId().equals("btnDeconnexion")){
                
                super.handleBtnsMouseExited(btn);
            }
            else{
                switch (btn.getId()) {
                    

                    default:
                         superButton=true;
                        break;
                    }
                    super.getVue().changeButtonColor(changedButton, newBtnColor, otherStyle);
                }
            }

        catch(Exception e){
            System.err.println("Erreur");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleBtnClassementMouseEntered(MouseEvent event){
        try{
            boolean superButton=false;
            Button changedButton=null;
            String newBtnColor="";
            String otherStyle="";
            Button btn=(Button)event.getSource();
            if(btn.getId().equals("btnAccueil") ||btn.getId().equals("btnAccueilDisconnected") || btn.getId().equals("btnRetour") || btn.getId().equals("btnCompte") || btn.getId().equals("btnDeconnexion")){
                
                super.handleBtnsMouseEntered(btn);
            }
            else{
                switch (btn.getId()) {

                default:
                        superButton=true;
                        break;
                }
                super.getVue().changeButtonColor(changedButton, newBtnColor, otherStyle);
            }

            
        }
        catch(Exception e){
            System.err.println("Erreur");
            e.printStackTrace();
        }
    }


    @Override
    public void handle(ActionEvent event){
        try{
            boolean superButton=false;
            Button changedButton=null;
            String newBtnColor="";
            String otherStyle="";
            Button btn=(Button)event.getSource();
            if(btn.getId().equals("btnAccueil") ||btn.getId().equals("btnAccueilDisconnected") || btn.getId().equals("btnRetour") || btn.getId().equals("btnCompte") || btn.getId().equals("btnDeconnexion")){
                super.handle(btn);
            }
            else{
                switch (btn.getId()) {
                    default:
                            superButton=true;
                            break;
                    }
                super.getVue().changeButtonColor(changedButton, newBtnColor, otherStyle);
            }



        }catch (Exception e){
            e.printStackTrace();
        }
    }
}