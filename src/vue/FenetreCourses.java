import javafx.scene.control.TextField;
import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;

public class FenetreCourses{

    private TextField tfDate;
    private TableView<ObservableList<Course>> prochainesCourses;
    private Button bNouvelleCourse;
    private Button bAjouterCourse;
    private Button bDepart;
    private Button bFinCourse;
    private ComboBox<String> formats;
    private Set<CheckBox> categories;
    private Set<CheckBox> categoriesChoisies;
    private TextField nom;
    private TextField date;
    private TextField heure;
    private List<Integer> dossards;
    private List<Integer> dossardsPartis;
    private List<Integer> dossardsArrives;

    public FenetreCourses(){

        this.tfDate = new TextField();
        this.prochainesCourses = new TableView<>();
        this.bNouvelleCourse = new Button();
        this.bAjouterCourse = new Button();
        this.bDepart = new Button();
        this.bFinCourse = new Button();
        this.formats = new ComboBox<>();
        this.categories = new HashSet<>();
        this.categoriesChoisies = new HashSet<>();
        this.nom = new TextField();
        this.date = new TextField();
        this.heure = new TextField();
        this.dossards = new ArrayList<>();
        this.dossardsPartis = new ArrayList<>();
        this.dossardsArrives = new ArrayList<>();
    }

    public TextField getTfDate(){
        return this.tfDate;
    }

    public void setTfDate(TextField date){
        this.tfDate = date;
    }

    public TableView<> getProchainesCourses(){
        return this.prochainesCourses;
    }

    public void SetProchainesCourses(TableView<> courses){
        this.prochainesCourses = courses;
    }
    
    public Button getBNouvelleCourse(){
        return this.bNouvelleCourse;
    }

    public void setBNouvelleCourse(Button nouvelleCourse){
        this.bNouvelleCourse = nouvelleCourse;
    }

    public Button getBAjouterCourse(){
        return this.bAjouterCourse;
    }

    public void setBAjouterCourse(Button courseAjoute){
        this.bAjouterCourse = courseAjoute;
    }

    public Button getBDepart(){
        return this.bDepart;
    }

    public Button setBDepart(Button boutonDepart){
        this.bDepart = boutonDepart
    }

    public Button getBFinCourse(){
        return this.bFinCourse;
    }

    public ComboBox<> getFormats(){
        return this.formats;
    }

    public HashSet<> getCategories(){
        return this.categories;
    }

    public HashSet<> getCategoriesChoisies(){
        return this.categoriesChoisies;
    }

    public TextField getNom(){
        return this.nom;
    }

    public TextField getDate(){
        return this.date;
    }

    public TextField getHeure(){
        return this.heure;
    }

    public ArrayList<> getDossards(){
        return this.dossards;
    }
    
    public ArrayList<> getDossardsPartis(){
        return this.dossardsPartis;
    }

    public ArrayList<> getDossardsArrives(){
        return this.dossardsArrives;
    }
}



- afficheCourses():void
+ getTfDate():TextField
+ updateCourses(date:String):void
+ nouvelleCourse():void
+ chronometre():void
