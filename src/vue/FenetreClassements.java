import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CheckBox;
import java.util.Set;
import javafx.fxml.FXMLLoader;
import javax.print.DocFlavor.STRING;
import javax.swing.ComboBoxEditor;
import src.modele.*;
import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;
import javafx.collections.ObservableList;

public class FenetreClassements {

    private TableView<ObservableList<Participant>> classement;
    private ComboBox<String> souscategories;
    private String categorieChoisie;
    private ComboBox<String> genre;
    private String genreChoisie;

    public FenetreClassements(){
        this.classement = new TableView<>();
        this.souscategories = new ComboBox<>();
        this.categorieChoisie = "";
        this.genre = new ComboBox<>();
        this.genreChoisie = "";
    }

    public TableView<ObservableList<Participant>> getClassement(){
        return this.classement;
    }

    public void setClassement(TableView<ObservableList<Participant>> nouveauClassement){
        this.classement = nouveauClassement;
    }

    public ComboBox<String> getSousCategorie() {
        return this.souscategories;
    }

    public void setCategories(ComboBox<String> nouvellesSousCategories) {
        this.souscategories = nouvellesSousCategories;
    }

    public String getCategorieChoisie(){
        return this.categorieChoisie;
    }

    public void setCategoriesChoisies(String nouvellesCategoriesChoisies){
        this.categorieChoisie = nouvellesCategoriesChoisies;
    }

    public ComboBox<String> getGenre(){
        return this.genre;
    }

    public void setGenre(ComboBox<String> nouveauGenre){
        this.genre = nouveauGenre;
    }

    public String getGenreChoisie(){
        return this.genreChoisie;
    }

    public void setGenreChosisie(String nouveauGenreChoisie){
        this.categorieChoisie = nouveauGenreChoisie;
    }




    private void afficheClassement(FXMLLoader loader){
        
    }

    public void updateClassement(String categorie, String genre){

    }

}
