package modele;
import java.util.ArrayList;
import java.util.List;
import modele.Participant;

public class Course {
    private List<Participant> lesParticipants;
    private int id;
    private String nom;
    private String format;
    private String categorie;
    private String heureDepart;
    private double prix;
    
    public Course(int id, String nom, String format, String categorie, String heureDepart, double prix){
        this.id = id;
        this.nom = nom;
        this.format = format;
        this.categorie = categorie;
        this.heureDepart = heureDepart;
        this.prix = prix;
        this.lesParticipants = new ArrayList<Participant>();
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getFormat() {
        return format;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getHeureDepart() {
        return heureDepart;
    }

    public double getPrix() {
        return prix;
    }

    public List<Participant> getParticipants() {
        return lesParticipants;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setHeureDepart(String heureDepart) {
        this.heureDepart = heureDepart;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void addParticipants(Participant participant) {
        lesParticipants.add(participant);
    }
}
