package modele;

public abstract class Participant{
    private int id;
    private String nom;
    private String prenom;
    private String categorie;
    private char sexe;
    private String email;
    private String ville;
    private String certification;
    private int numTel;
    private Classement classement;

    public Participant(int id, String nom, String prenom, String categorie, char sexe, String email, String ville, String certification, int numTel){
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.categorie=categorie;
        this.sexe=sexe;
        this.email=email;
        this.ville=ville;
        this.certification=certification;
        this.numTel=numTel;
    }

    public int getId(){
        return this.id;
    }

    public String getNom(){
        return this.nom;
    }

    public String getPrenom(){
        return this.prenom;
    }

    public String getCategorie(){
        return this.categorie;
    }

    public char getSexe(){
        return this.sexe;
    }

    public String getEmail(){
        return this.email;
    }

    public String getVille(){
        return this.ville;
    }

    public String getCertification(){
        return this.certification;
    }

    public int getTel(){
        return this.numTel;
    }

    public void setId(int id){
        this.id=id;
    }

    public void setNom(String nom){
        this.nom=nom;
    }

    public void setPrenom(String prenom){
        this.prenom=prenom;
    }

    public void setCategorie(String categorie){
        this.categorie=categorie;
    }

    public void setSexe(char sexe){
        this.sexe=sexe;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public void setVille(String ville){
        this.ville=ville;
    }

    public void setCertification(String certification){
        this.certification=certification;
    }

    public void setTel(int numTel){
        this.numTel=numTel;
    }
}