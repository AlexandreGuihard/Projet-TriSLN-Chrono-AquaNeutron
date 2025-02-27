package com.trisln.aquaneutron.modele;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Alerter {

  public void Testfonct(){
      Alerter A = new Alerter();
      this.showError("");
  }

  public void showError(String listeErreur) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error alert");
    alert.setHeaderText("Liste des ligne qui ne sont pas passée");

    VBox dialogPaneContent = new VBox();

    Label label = new Label("Stack Trace:");
    TextArea textArea = new TextArea();
    textArea.setText(listeErreur);
    textArea.setEditable( false );
    dialogPaneContent.getChildren().addAll(label, textArea);

    // Set content for Dialog Pane
    alert.getDialogPane().setContent(dialogPaneContent);
    alert.showAndWait();
  }






















// Alerte confirmation

  public boolean showConfirmationDeconnection() {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Deconnecter");
    alert.setHeaderText("Voulez vous vraiment vous deconnectez ?");
    Optional<ButtonType> option = alert.showAndWait();

    if (option.get() == null) {
      return false;
    } else if (option.get() == ButtonType.OK) {
      System.out.println("deco");
      return true;
    } else if (option.get() == ButtonType.CANCEL) {
      System.out.println("annuler");
      return false;
    }
    return false;
  }

  public boolean showConfirmationSupprimerParticipant() {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Delete Participant");
    alert.setHeaderText("Voulez vous vraiment supprimer le Participant ?");
    Optional<ButtonType> option = alert.showAndWait();

    if (option.get() == null) {
      return false;
    } else if (option.get() == ButtonType.OK) {
      System.out.println("'sup'");
      return true;
    } else if (option.get() == ButtonType.CANCEL) {
      System.out.println("annuler");
      return false;
    }
    return false;
  }

  public boolean showConfirmationModifierParticipant() {

    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("modifier Participant");
    alert.setHeaderText("Voulez vous vraiment modifier le Participant ?");
    alert.setContentText("C:/MyFile.txt");

    // option != null.
    Optional<ButtonType> option = alert.showAndWait();

    if (option.get() == null) {
      return false;
    } else if (option.get() == ButtonType.OK) {
      System.out.println("mod");
      return true;
    } else if (option.get() == ButtonType.CANCEL) {
      System.out.println("annuler");
      return false;
    }
    return false;
  }



















// Alerte non utiliser

  public void showAlertIdParticipant(int ligne) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error alert");
    alert.setHeaderText("Results: Missing Id Dossard");
    alert.setContentText("Il manque l'id du participant a la ligne " + ligne);
    alert.showAndWait();
  }

  public void showAlertNomParticipant(String idParticipant,int ligne ) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error alert");
    alert.setHeaderText("Results: Missing Nom");
    alert.setContentText("Il manque le nom du participant id "+idParticipant+ " a la ligne " + ligne);
    alert.showAndWait();
  }

  public void showAlertPrenomParticipant(String idParticipant,String nom,int ligne ) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error alert");
    alert.setHeaderText("Results: Missing Prénom");
    alert.setContentText("Il manque le prenom du participant "+nom+" id "+idParticipant+ " a la ligne " + ligne);
    alert.showAndWait();
  }


  public void showAlertIdCategorieParticipant(String idParticipant,String nom,String prenom,int ligne ) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error alert");
    alert.setHeaderText("Results: Missing Id Categorie");
    alert.setContentText("Il manque l'idCategorie du participant "+nom+" "+prenom +" id "+idParticipant+ " a la ligne " + ligne);
    alert.showAndWait();
  }

  public void showAlertSexeParticipant(String idParticipant,String nom,String prenom,int ligne ) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error alert");
    alert.setHeaderText("Results: Missing Sexe du Participant");
    alert.setContentText("Il manque le sexe du participant "+nom+" "+prenom +" id "+idParticipant+ " a la ligne " + ligne);
    alert.showAndWait();
  }


  public void showAlertNumTelParticipant(String idParticipant,String nom,String prenom,int ligne ) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error alert");
    alert.setHeaderText("Results: Missing Numero de Telephone");
    alert.setContentText("Il manque le telephone du participant "+nom+" "+prenom +" id "+idParticipant+ " a la ligne " + ligne);
    alert.showAndWait();
  }

  public void showAlertDateNaissanceParticipant(String idParticipant,String nom,String prenom,int ligne ) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error alert");
    alert.setHeaderText("Results: Missing Date de Naissance");
    alert.setContentText("Il manque la date de naissance du participant "+nom+" "+prenom +" id "+idParticipant+ " a la ligne " + ligne);
    alert.showAndWait();
  }

}

