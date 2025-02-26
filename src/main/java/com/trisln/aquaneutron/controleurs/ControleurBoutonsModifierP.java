package com.trisln.aquaneutron.controleurs;

import javafx.event.EventHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import com.trisln.aquaneutron.modele.Participant;
import com.trisln.aquaneutron.modele.ParticipantCourseRelais;
import com.trisln.aquaneutron.modele.ParticipantLicenceCourseIndiv;
import com.trisln.aquaneutron.modele.ParticipantNonLicenceCourseIndiv;
import com.trisln.aquaneutron.vue.TriSLN;

public class ControleurBoutonsModifierP extends ControleurBoutons implements EventHandler<ActionEvent>{
    // log accueil et AccConnecter

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
    private Button btnEnregistrerModification;

    @FXML
    private TextField textFieldId;
    @FXML
    private Text textId;
    @FXML
    private TextField textFieldNom;
    @FXML
    private TextField textFieldPrenom;
    @FXML
    private TextField textFieldSexe;
    @FXML
    private TextField textFieldDateDeNaissance;
    @FXML
    private TextField textFieldVille;
    @FXML
    private TextField textFieldNomEquipe;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldClub;
    @FXML
    private TextField textFieldNumTel;
    @FXML
    private TextField textFieldCertification;
    @FXML
    private TextField textFieldNumLicence;
    @FXML
    private TextField textFieldLicence;
    @FXML
    private ComboBox<String> comboxCategorie;
    @FXML
    private ComboBox<String> comboxSC;

    public ControleurBoutonsModifierP(TriSLN vue){
        super();
        this.setBoutons(vue);
    }

    public void initialize() {
        this.updateComboxSC();
        btnEnregistrerModification.setDisable(true);
    }


    private void setBoutons(TriSLN vue){
        super.setBCompte(btnCompte);
        super.setBAccueil(btnAccueil);
        super.setBRetour(btnRetour);
        super.setBDeconnexion(btnDeconnexion);
        super.setVue(vue);
    }

    @FXML
    public void handleBtnModifierPMouseEntered(MouseEvent event){
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

                    case "btnEnregistrerModification":
                        changedButton=this.btnEnregistrerModification;
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
    public void handleBtnModifierPMouseExited(MouseEvent event){
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
                    case "btnEnregistrerModification":
                        changedButton=this.btnEnregistrerModification;
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
                    case "btnEnregistrerModification":
                        System.out.println("enregistrer");
                        Participant participant=null;
                        try{
                            if(TriSLN.getBd().isParticipantsRelais(textFieldClub.getText(), textFieldNomEquipe.getText(), Boolean.parseBoolean(textFieldLicence.getText()), Integer.parseInt(textFieldNumLicence.getText()))){
                                participant=new ParticipantCourseRelais(Integer.parseInt(textId.getText()), textFieldNom.getText(), textFieldPrenom.getText(), getCategFromComboBox(), comboxSC.getValue(), textFieldSexe.getText().charAt(0), textFieldEmail.getText(), textFieldVille.getText(), Boolean.parseBoolean(textFieldCertification.getText()), textFieldNumTel.getText(), textFieldDateDeNaissance.getText(), textFieldNomEquipe.getText(), Boolean.parseBoolean(textFieldLicence.getText()));
                            }
                            else if(TriSLN.getBd().isParticipantsLicenceIndiv(textFieldClub.getText(), textFieldNomEquipe.getText(), Boolean.parseBoolean(textFieldLicence.getText()), Integer.parseInt(textFieldNumLicence.getText()))){
                                participant=new ParticipantLicenceCourseIndiv(Integer.parseInt(textId.getText()), textFieldNom.getText(), textFieldPrenom.getText(), getCategFromComboBox(), comboxSC.getValue(), textFieldSexe.getText().charAt(0), textFieldEmail.getText(), textFieldVille.getText(), Boolean.parseBoolean(textFieldCertification.getText()), textFieldNumTel.getText(), textFieldClub.getText(), Integer.parseInt(textFieldNumLicence.getText()), textFieldDateDeNaissance.getText());
                            }
                            else if(TriSLN.getBd().isParticipantsNonLicenceIndiv(textFieldClub.getText(), textFieldNomEquipe.getText(), Boolean.parseBoolean(textFieldLicence.getText()), Integer.parseInt(textFieldNumLicence.getText()))){
                                participant=new ParticipantNonLicenceCourseIndiv(Integer.parseInt(textId.getText()), textFieldNom.getText(), textFieldPrenom.getText(), getCategFromComboBox(), comboxSC.getValue(), textFieldSexe.getText().charAt(0), textFieldEmail.getText(), textFieldVille.getText(), Boolean.parseBoolean(textFieldCertification.getText()), textFieldNumTel.getText(), textFieldDateDeNaissance.getText());
                            }
                            super.getVue().getBd().updateParticipant(participant);
                        }
                        catch(SQLException e){
                            e.printStackTrace();
                        }
                        super.getVue().afficheModifierP();
                        break;
                    case "btnSuprimerParticipant":
                        super.getVue().afficheSupprimerP();
                        break;
                    case "btnAjouterParticipant":
                        super.getVue().afficheAjouterP();
                        break;
                    default:
                        super.getVue().afficheModifierP();
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
    public void handleCB(ActionEvent event){
        ComboBox<?> comboBox = (ComboBox<?>) event.getSource();
        if (comboBox.getId().equals("comboxCategorie")) {
            updateComboxSC();
        }
    }

    public void handleKeyReleased(KeyEvent event){
        try{
            Platform.runLater(()->textId.setText(""));
            Platform.runLater(()->textFieldNom.setText(""));
            Platform.runLater(()->textFieldPrenom.setText(""));
            Platform.runLater(()->textFieldSexe.setText(""));
            Platform.runLater(()->textFieldDateDeNaissance.setText(""));
            Platform.runLater(()->comboxCategorie.setValue(this.change("")));
            Platform.runLater(this::updateComboxSC);
            Platform.runLater(()->textFieldClub.setText(""));
            Platform.runLater(()->textFieldNomEquipe.setText(""));
            Platform.runLater(()->textFieldEmail.setText(""));
            Platform.runLater(()->textFieldNumTel.setText(""));
            Platform.runLater(()->textFieldCertification.setText(""));
            Platform.runLater(()->textFieldNumLicence.setText(""));
            Platform.runLater(()->textFieldVille.setText(""));
            Platform.runLater(()->textFieldLicence.setText(""));
            if(!textFieldId.getText().equals("")){
                btnEnregistrerModification.setDisable(false);
                int idParticipant=Integer.parseInt(textFieldId.getText());
                Participant participant=TriSLN.getBd().getParticipantFromId(idParticipant);
                Platform.runLater(()->textId.setText(participant.getId()+""));
                Platform.runLater(()->textFieldNom.setText(participant.getNom()));
                Platform.runLater(()->textFieldPrenom.setText(participant.getPrenom()));
                Platform.runLater(()->textFieldSexe.setText(participant.getSexe()+""));
                Platform.runLater(()->textFieldDateDeNaissance.setText(participant.getDateNaissance()));
                Platform.runLater(()->comboxCategorie.setValue(this.change(participant.getCategorie())));
                if(participant.getSousCategorie()!=null){
                    Platform.runLater(()->comboxSC.setValue(this.change(participant.getSousCategorie())));
                }
                Platform.runLater(()->textFieldClub.setText(participant.getClub()));
                Platform.runLater(()->textFieldNomEquipe.setText(participant.getNomEquipe()));
                Platform.runLater(()->textFieldEmail.setText(participant.getEmail()));
                Platform.runLater(()->textFieldNumTel.setText(participant.getTel()));
                Platform.runLater(()->textFieldCertification.setText(participant.getCertification()+""));
                Platform.runLater(()->textFieldNumLicence.setText(participant.getNumLicence()+""));
                Platform.runLater(()->textFieldVille.setText(participant.getVille()));
                Platform.runLater(()->textFieldLicence.setText(participant.getLicence()+""));
            }
            else{
                btnEnregistrerModification.setDisable(true);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private void updateComboxSC() {
        String selectedCategory = this.getCategFromComboBox();

        if (selectedCategory == null) { // Évite NullPointerException
            this.comboxSC.setDisable(true);
            return;
        }

        if (selectedCategory.equals("V")) {
            List<String> scVet = List.of("V1", "V2", "V3", "V4", "V5", "V6", "V7");
            this.comboxSC.setItems(FXCollections.observableArrayList(scVet));
            this.comboxSC.setDisable(false);
        } else if (selectedCategory.equals("S")) {
            List<String> scSen = List.of("S1", "S2", "S3", "S4");
            this.comboxSC.setItems(FXCollections.observableArrayList(scSen));
            this.comboxSC.setDisable(false);
        } else {
            this.comboxSC.hide();
            this.comboxSC.setDisable(true);
        }
    }


    private String getCategFromComboBox() {
        if (comboxCategorie.getValue() == null) {
            return null; // Évite NullPointerException
        }

        switch (comboxCategorie.getValue()) {
            case "Mini-poussin": return "MP";
            case "Poussin": return "PO";
            case "Pupille": return "PU";
            case "Benjamin": return "BE";
            case "Minime": return "MI";
            case "Cadet": return "CA";
            case "Junior": return "JU";
            case "Sénior": return "S";
            case "Vétéran": return "V";
            default: return comboxCategorie.getValue();
        }
    }


    private String change(String categorie) {
        switch (categorie) {
            case "MP":
                return "Mini-poussin";
            case "PO":
                return "Poussin";
            case "PU":
                return "Pupille";
            case "BE":
                return "Benjamin";
            case "MI":
                return "Minime";
            case "CA":
                return "Cadet";
            case "JU":
                return "Junior";
            case "S":
                return "Sénior";
            case "V":
                return "Vétéran";
        }
        return categorie;
    }
}

