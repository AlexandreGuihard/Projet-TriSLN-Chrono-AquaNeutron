package com.trisln.aquaneutron.bd;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Alerter {

    


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



public void showConfirmationSupprimerParticipant() {

      Alert alert = new Alert(AlertType.CONFIRMATION);
      alert.setTitle("Delete Participant");
      alert.setHeaderText("Voulez vous vraiment supprimer le Participant ?");
      alert.setContentText("C:/MyFile.txt");

      // option != null.
      Optional<ButtonType> option = alert.showAndWait();

      if (option.get() == null) {
        System.out.println("No selection!");
      } else if (option.get() == ButtonType.OK) {
        System.out.println("File deleted!");
      } else if (option.get() == ButtonType.CANCEL) {
        System.out.println("Cancelled!");
      }
   }

public void showConfirmationModifierParticipant() {

    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Delete Participant");
    alert.setHeaderText("Voulez vous vraiment supprimer le Participant ?");
    alert.setContentText("C:/MyFile.txt");

    // option != null.
    Optional<ButtonType> option = alert.showAndWait();

    if (option.get() == null) {
      System.out.println("No selection!");
    } else if (option.get() == ButtonType.OK) {
      System.out.println("File deleted!");
    } else if (option.get() == ButtonType.CANCEL) {
      System.out.println("Cancelled!");
    }
 }


public void Testfonct(){
    Alerter A = new Alerter();
    this.showAlertIdParticipant(0);
    this.showAlertNomParticipant(null, 0);
    this.showAlertPrenomParticipant(null, null, 0);
    this.showAlertIdCategorieParticipant(null, null, null, 0);
    this.showAlertSexeParticipant("idParticipant", "nom", "prenom", 1 );
    this.showAlertNumTelParticipant("idParticipant", "nom", "prenom", 1 );
    this.showAlertDateNaissanceParticipant("idParticipant", "nom", "prenom", 1 );
}


}

