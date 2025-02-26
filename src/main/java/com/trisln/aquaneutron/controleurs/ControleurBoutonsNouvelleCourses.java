package com.trisln.aquaneutron.controleurs;

import javafx.event.EventHandler;


import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import com.trisln.aquaneutron.vue.TriSLN;

import java.util.Set;

public class ControleurBoutonsNouvelleCourses extends ControleurBoutons implements EventHandler<ActionEvent> {
    private TriSLN vue;

    @FXML
    private TextField nomCourse;
    @FXML
    private ComboBox<String> formatCourse;
    @FXML
    private Button btnAjoutCourse;
    @FXML
    private Button btnAccueil;
    @FXML
    private Button btnRetour;
    @FXML
    private Button btnDeconnexion;
    @FXML
    private Button btnCompte;

    @FXML
    private RadioButton rbMP;
    @FXML
    private RadioButton rbPo;
    @FXML
    private RadioButton rbPu;
    @FXML
    private RadioButton rbB;
    @FXML
    private RadioButton rbM;
    @FXML
    private RadioButton rbC;
    @FXML
    private RadioButton rbJ;
    @FXML
    private RadioButton rbS;
    @FXML
    private RadioButton rbV;
    private ToggleGroup toggleGroup;
    @FXML
    private TextField heureCourse;

    public ControleurBoutonsNouvelleCourses(TriSLN vue){
        super();
        this.setBoutons(vue);
    }

    public void initialize() {
        this.toggleGroup = this.rbMP.getToggleGroup();

        this.nomCourse.textProperty().addListener((observable, oldValue, newValue) -> {
            this.changeEtatBouton();
        });
    }

    private void setBoutons(TriSLN vue){
        super.setVue(vue);
        super.setBAccueil(btnAccueil);
        super.setBCompte(btnCompte);
        super.setBDeconnexion(btnDeconnexion);
        super.setBRetour(btnRetour);
    }

    @FXML
    public void onKeyReleased(KeyEvent event) {
        //TODO : à Finir
        KeyCode keyCode = event.getCode();
        TextField textField = (TextField) event.getSource();
        if (textField.getId().equals("heureCourse")) {
            Set<Character> HEURE_AUTORISEE = Set.of('0', '1', '2');
            Set<Character> HEURE_AUTORISEE_SI_2 = Set.of('0', '1', '2', '3');
            Set<Character> MINUTE_AUTORISEE_PREMIER_CHAR = Set.of('0', '1', '2', '3', '4', '5', '6');


            int cursorPosition = this.heureCourse.getCaretPosition();
            String text = textField.getText();
            char newChar = textField.getText().charAt(cursorPosition-1);
            StringBuilder newText = new StringBuilder(text); // Pour modifier le contenu

            if ((keyCode.isDigitKey() || keyCode.isKeypadKey())
                    && cursorPosition > 0 && cursorPosition < newText.length()) {
                switch(cursorPosition) {
                    case 1:
                        if (HEURE_AUTORISEE.contains(newChar)) {
                            newText.setCharAt(cursorPosition, newChar);
                        }
                        break;
                    case 2:
                        if (newText.charAt(0) != '2' || HEURE_AUTORISEE_SI_2.contains(newChar)) {
                            newText.setCharAt(cursorPosition, newChar);
                        }
                        break;
                    case 3:
                        this.heureCourse.positionCaret(cursorPosition+1);
                        event.consume();
                        break;
                    case 4:
                        if (MINUTE_AUTORISEE_PREMIER_CHAR.contains(newChar)) {
                            newText.setCharAt(cursorPosition, newChar);
                        }
                        break;
                    case 5:
                        newText.setCharAt(cursorPosition, newChar);
                        break;
                }
            } else if (keyCode == KeyCode.BACK_SPACE) {
                newText.setCharAt(cursorPosition, '0');
            }

            // Enlever l'element ajouté par l'utilisateur
            if (keyCode.isLetterKey() || keyCode.isKeypadKey() || keyCode.isDigitKey() || keyCode == KeyCode.SPACE) {
                newText.deleteCharAt(cursorPosition-1);
                text = String.valueOf(newText);
            }
            // Mettre à jour le TextField
            this.heureCourse.setText(text);
            this.heureCourse.positionCaret(cursorPosition);
            this.changeEtatBouton();
        }
    }

    @FXML
    public void handleBtnCoursesMouseEntered(MouseEvent event){
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

                    case "btnAjoutCourse":
                        changedButton=this.btnAjoutCourse;
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
    public void handleBtnCoursesMouseExited(MouseEvent event){
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
                    case "btnAjoutCourse":
                        changedButton=this.btnAjoutCourse;
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

    public void handle(ActionEvent event){
        Object source = event.getSource();

        if (source instanceof Button btn) {
            switch (btn.getId()) {
                case "btnAccueil":
                case "btnRetour":
                case "btnCompte":
                case "btnDeconnexion":
                case "btnConnexion":
                    super.handle(btn);
                    break;
            }
        }
         else if (source instanceof ComboBox<?> comboBox) {
            if (comboBox.getId().equals("formatCourse")) {
                this.changeEtatBouton();
            }
        }
        else if (source instanceof RadioButton) {
            this.changeEtatBouton();
        }

    }

    private void changeEtatBouton() {
        boolean nomVide = this.nomCourse.getText().isEmpty();
        boolean formatVide = (this.formatCourse.getValue() == null);
        boolean categorieVide = (this.toggleGroup.getSelectedToggle() == null);
        boolean heureRespectRegex = !(this.heureCourse.getText().matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$"));

        this.btnAjoutCourse.setDisable(nomVide || formatVide || categorieVide || heureRespectRegex);
    }
}
