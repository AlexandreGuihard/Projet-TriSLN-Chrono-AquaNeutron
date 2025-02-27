package com.trisln.aquaneutron.controleurs;

import javafx.event.EventHandler;

import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import com.trisln.aquaneutron.vue.TriSLN;
import com.trisln.aquaneutron.modele.Participant;
import com.trisln.aquaneutron.modele.Alerter;

public class ControleurBoutonsSupprimerP extends ControleurBoutons implements EventHandler<ActionEvent>{
    @FXML
    private Button btnConnexion;
    @FXML
    private Button btnRetour;
    @FXML
    private Button btnDeconnexion;
    @FXML
    private Button btnAccueil;
    @FXML
    private Button btnCompte;

    @FXML
    private Button btnAjouterParticipant;
    @FXML
    private Button btnSuprimerParticipant;
    @FXML
    private Button btnModifierParticipant;
    @FXML
    private TextField textFieldId;
    @FXML
    private Button btnSupprimerParticipantValidation;

    @FXML
    private Text textId;
    @FXML
    private Text textNom;
    @FXML
    private Text textPrenom;
    @FXML
    private Text textSexe;
    @FXML
    private Text textDateDeNaissance;
    @FXML
    private Text textCategorie;
    @FXML
    private Text textClub;
    @FXML
    private Text textNomEquipe;
    @FXML
    private Text textEmail;
    @FXML
    private Text textNumTel;
    @FXML
    private Text textCertification;
    @FXML
    private Text textNumLicence;
    @FXML
    private Text textVille;

    public ControleurBoutonsSupprimerP(TriSLN vue){
        super();
        this.setBoutons(vue);
    }


    private void setBoutons(TriSLN vue){
        super.setBCompte(btnCompte);
        super.setBAccueil(btnAccueil);
        super.setBRetour(btnRetour);
        super.setBDeconnexion(btnDeconnexion);
        super.setVue(vue);
    }

    @FXML
    public void handleBtnSupprimerPMouseEntered(MouseEvent event){
        try{
            boolean superButton=false;
            Button changedButton=null;
            String newBtnColor="";
            String otherStyle="";
            Button btn=(Button)event.getSource();
            if(btn.getId().equals("btnAccueil") || btn.getId().equals("btnRetour") || btn.getId().equals("btnCompte") || btn.getId().equals("btnDeconnexion")|| btn.getId().equals("btnConnexion")){
                super.handleBtnsMouseEntered(btn);
            }
            else{
                switch(btn.getId()){
                    case "btnAjouterParticipant":
                        changedButton=this.btnAjouterParticipant;
                        newBtnColor="#105c74";
                        break;
                    case "btnSuprimerParticipant":
                        changedButton=this.btnSuprimerParticipant;
                        newBtnColor="#105c74";
                        break;
                    case "btnModifierParticipant":
                        changedButton=this.btnModifierParticipant;
                        newBtnColor="#105c74";
                        break;
                    case "btnSupprimerParticipantValidation":
                        changedButton=this.btnSupprimerParticipantValidation;
                        newBtnColor="#105c74";
                        break;
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
    public void handleBtnSupprimerPMouseExited(MouseEvent event){
        try{
            boolean superButton=false;
            Button changedButton=null;
            String newBtnColor="";
            String otherStyle="";
            Button btn=(Button)event.getSource();
            if(btn.getId().equals("btnAccueil") || btn.getId().equals("btnRetour") || btn.getId().equals("btnCompte") || btn.getId().equals("btnDeconnexion")|| btn.getId().equals("btnConnexion")){
                super.handleBtnsMouseExited(btn);
            }
            else{
                switch(btn.getId()){
                    case "btnAjouterParticipant":
                    changedButton=this.btnAjouterParticipant;
                    newBtnColor="#2596BE";
                        break;
                    case "btnSuprimerParticipant":
                    changedButton=this.btnSuprimerParticipant;
                    newBtnColor="#2596BE";
                        break;
                    case "btnModifierParticipant":
                    changedButton=this.btnModifierParticipant;
                    newBtnColor="#2596BE";
                    break;
                        case "btnSupprimerParticipantValidation":
                        changedButton=this.btnSupprimerParticipantValidation;
                        newBtnColor="#2596BE";
                        break;

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

        try {Button btn=(Button)event.getSource();
            if(btn.getId().equals("btnAccueil") || btn.getId().equals("btnRetour") || btn.getId().equals("btnCompte") || btn.getId().equals("btnDeconnexion")|| btn.getId().equals("btnConnexion")){
                    super.handle(btn);
            }
            else{
                switch(btn.getId()){
                    case "btnSupprimerParticipantValidation":
                        
                        try{
                            Alerter A = new Alerter();
                            if(A.showConfirmationSupprimerParticipant()){
                                System.out.println("supprimer");
                                super.getVue().getBd().supprimerParticipant(Integer.parseInt(textFieldId.getText()));
                                int idParticipant=Integer.parseInt(textFieldId.getText());
                                
                                Participant participant=TriSLN.getBd().getParticipantFromId(idParticipant);
                                Platform.runLater(()->textId.setText(participant.getId()+""));
                                Platform.runLater(()->textNom.setText(participant.getNom()));
                                Platform.runLater(()->textPrenom.setText(participant.getPrenom()));
                                Platform.runLater(()->textSexe.setText(participant.getSexe()+""));
                                Platform.runLater(()->textDateDeNaissance.setText(participant.getDateNaissance()));
                                Platform.runLater(()->textCategorie.setText(participant.getCategorie()));
                                Platform.runLater(()->textClub.setText(participant.getClub()));
                                Platform.runLater(()->textNomEquipe.setText(participant.getNomEquipe()));
                                Platform.runLater(()->textEmail.setText(participant.getEmail()));
                                Platform.runLater(()->textNumTel.setText(participant.getTel()));
                                Platform.runLater(()->textCertification.setText(participant.getCertification()+""));
                                Platform.runLater(()->textNumLicence.setText(participant.getNumLicence()+""));
                                Platform.runLater(()->textVille.setText(participant.getVille()));
                            }
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                        break;
                    case "btnAjouterParticipant":
                        super.getVue().afficheAjouterP();
                        break;
                    case "btnModifierParticipant":
                        super.getVue().afficheModifierP();
                        break;
                    case "btnSuprimerParticipant":
                        super.getVue().afficheSupprimerP();
                        break;
                    default:
                        super.getVue().afficheSupprimerP();
                        break;
                        
            }
                
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        } 
    }

    @FXML
    public void handleKeyReleased(KeyEvent event){
        try{
            int idParticipant=Integer.parseInt(textFieldId.getText());
            Participant participant=TriSLN.getBd().getParticipantFromId(idParticipant);
            Platform.runLater(()->textId.setText(participant.getId()+""));
            Platform.runLater(()->textNom.setText(participant.getNom()));
            Platform.runLater(()->textPrenom.setText(participant.getPrenom()));
            Platform.runLater(()->textSexe.setText(participant.getSexe()+""));
            Platform.runLater(()->textDateDeNaissance.setText(participant.getDateNaissance()));
            Platform.runLater(()->textCategorie.setText(participant.getCategorie()));
            Platform.runLater(()->textClub.setText(participant.getClub()));
            Platform.runLater(()->textNomEquipe.setText(participant.getNomEquipe()));
            Platform.runLater(()->textEmail.setText(participant.getEmail()));
            Platform.runLater(()->textNumTel.setText(participant.getTel()));
            Platform.runLater(()->textCertification.setText(participant.getCertification()+""));
            Platform.runLater(()->textNumLicence.setText(participant.getNumLicence()+""));
            Platform.runLater(()->textVille.setText(participant.getVille()));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}

