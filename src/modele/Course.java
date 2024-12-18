package src.modele;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Course représentant une course
 */
public class Course {
    private List<Participant> lesParticipants;
    private int id;
    private String nom;
    private String format;
    private String categorie;
    private String sousCategorie;
    private String heureDepart;
    private double prix;
    
    /**
     * Constructeur de la classe Course
     * @param id l'id de la course
     * @param nom le nom de la course
     * @param idFormat le format de la course
     * @param idCategorie la catégorie de la course
     * @param heureDepart l'heure de départ de la course
     * @param prix le prix pour participer à la course
     */
    public Course(int id, String nom, String format, String categorie, String sousCategorie, String heureDepart, double prix){
        this.id = id;
        this.nom = nom;
        this.format = format;
        this.categorie = categorie;
        this.sousCategorie=sousCategorie;
        this.heureDepart = heureDepart;
        this.prix = prix;
        this.lesParticipants = new ArrayList<Participant>();
    }

    /**
     * Getter pour l'id
     * @return l'id de la course
     */
    public int getId() {
        return id;
    }

    /**
     * Getter pour le nom
     * @return le nom de la course
     */
    public String getNom() {
        return nom;
    }

    /**
     * Getter pour le format
     * @return le format de la course
     */
    public String getFormat() {
        return format;
    }

    /**
     * Getter pour la catégorie
     * @return la catégorie de la course
     */
    public String getCategorie() {
        return categorie;
    }

    public String getSousCategorie(){
        return sousCategorie;
    }

    /**
     * Getter pour l'heure de départ
     * @return l'heure de départ de la course
     */
    public String getHeureDepart() {
        return heureDepart;
    }

    /**
     * Getter pour le prix
     * @return le prix de la course
     */
    public double getPrix() {
        return prix;
    }

    /**
     * Getter pour les participants
     * @return lsite des participants dans la course
     */
    public List<Participant> getParticipants() {
        return lesParticipants;
    }

    /**
     * Setter pour l'id
     * @param id le nouvel id de la course
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setter pour le nom
     * @param nom le nouveau nom de la course
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Setter pour le format
     * @param format le nouveau format de la course
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * Setter pour la catégorie
     * @param categorie la nouvelle catégorie de la course
     */
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setSousCategorie(String sousCategorie){
        this.sousCategorie=sousCategorie;
    }

    /**
     * Setter pour l'heure de départ
     * @param heureDepart la nouvelle heure de départ de la course
     */
    public void setHeureDepart(String heureDepart) {
        this.heureDepart = heureDepart;
    }

    /**
     * Setter pour le prix
     * @param prix le nouveau prix de la course
     */
    public void setPrix(double prix) {
        this.prix = prix;
    }

    /**
     * Ajouter un participant à la course
     * @param participant le nouveau participant à la course
     */
    public void addParticipants(Participant participant) {
        lesParticipants.add(participant);
    }
}
