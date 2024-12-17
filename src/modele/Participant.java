package src.modele;

import javax.print.DocFlavor.STRING;

/** Classe abstraite participant
 */
public abstract class Participant{
    private int id;
    private String nom;
    private String prenom;
    private int idCategorie;
    private char sexe;
    private String email;
    private String ville;
    private boolean certification;
    private String numTel;
    private String dateNaissance;
    private boolean licence;
    private Classement classement;


    /**
     * 
     * @param id l'id du participant
     * @param nom le nom du participant
     * @param prenom le prenom du participant
     * @param idCategorie la catégorie à la laquelle le participant appartient
     * @param sexe le sexe du participant
     * @param email l'email du participant
     * @param ville la ville du participant
     * @param certification le certificat du participant
     * @param numTel le numéro de téléphone du participant
     * @param dateNaissance la date de naissance du participant
     * @param licence le participant possède ou non une licence
     */
    
    public Participant(int id, String nom, String prenom, int idCategorie, char sexe, String email, String ville, boolean certification, String numTel, String dateNaissance, boolean licence){
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.idCategorie=idCategorie;
        this.sexe=sexe;
        this.email=email;
        this.ville=ville;
        this.certification=certification;
        this.numTel=numTel;
        this.dateNaissance=dateNaissance;
        this.licence = licence;
    }

    /**
     * Getter pour avoir l'id
     * @return l'id du participant
     */
    public int getId(){
        return this.id;
    }

    /**
     * Getter pour avoior le nom
     * @return le nom du participant
     */
    public String getNom(){
        return this.nom;
    }

    /**
     * Getter pour avoir le prénom
     * @return le prénom du participant
     */
    public String getPrenom(){
        return this.prenom;
    }

    /**
     * Getter pour avoir la catégorie
     * @return la catégorie du participant
     */
    public int getCategorie(){
        return this.idCategorie;
    }

    /**
     * Getter pour avoir le sexe
     * @return le sexe du participant
     */
    public char getSexe(){
        return this.sexe;
    }

    /**
     * Getter pour avoir l'email
     * @return l'email du participant
     */
    public String getEmail(){
        return this.email;
    }

    /**
     * Getter pour avoir la ville
     * @return la ville du participant
     */
    public String getVille(){
        return this.ville;
    }

    /**
     * Getter pour avoir le certificat
     * @return le certificat du participant
     */
    public boolean getCertification(){
        return this.certification;
    }

    /**
     * Getter pour avoir le numéro de téléphone
     * @return le numéro de téléphone du participant
     */
    public String getTel(){
        return this.numTel;
    }

    /**
     * Getter pour avoir la date de naissance
     * @return la date de naissance du participant
     */
    public String getDateNaissance(){
        return this.dateNaissance;
    }

    /**
     * Getter pour avoir la licence
     * @return TRUE si le participant possède une licence et non sinon
     */
    public boolean getLicence(){
        return this.licence;
    }

    /**
     * Setter de l'id
     * @param id le nouvel id du participant
     */
    public void setId(int id){
        this.id=id;
    }

    /**
     * Setter du nom 
     * @param nom le nouveau nom du participant
     */
    public void setNom(String nom){
        this.nom=nom;
    }

    /**
     * Setter du prénom
     * @param prenom le nouveau prénom du participant
     */
    public void setPrenom(String prenom){
        this.prenom=prenom;
    }

    /**
     * Setter de la catégorie
     * @param categorie la nouvelle catégorie du participant
     */
    public void setCategorie(int idCategorie){
        this.idCategorie=idCategorie;
    }

    /**
     * Setter du sexe
     * @param sexe le nouveau sexe du participant
     */
    public void setSexe(char sexe){
        this.sexe=sexe;
    }

    /**
     * Setter de l'email
     * @param email le nouvel email du participant
     */
    public void setEmail(String email){
        this.email=email;
    }

    /**
     * Setter de la ville
     * @param ville la nouvelle ville du participant
     */
    public void setVille(String ville){
        this.ville=ville;
    }

    /**
     * Setter de la certification
     * @param certification le nouveau cartificat du participant
     */
    public void setCertification(boolean certification){
        this.certification=certification;
    }

    /**
     * Setter du numéro de téléphone
     * @param numTel le nouveau numéro de téléphone du participant
     */
    public void setTel(String numTel){
        this.numTel=numTel;
    }

    /**
     * Setter de la date de naissance
     * @param dateNaissance la nouvelle date de naissance du participant
     */
    public void setDateNaissance(String dateNaissance){
        this.dateNaissance=dateNaissance;
    }

    /**
     * Setter de la licence
     * @param licence le nouveau statut de la licence du participant
     */
    public void setLicence(boolean licence){
        this.licence=licence;
    }

    public String toString() {
        return "id=" + this.getId() + ", nom='" + this.getNom() + ", prenom='" + this.getPrenom() + ", categorie=" + this.getCategorie()+ ", email='" + this.getEmail();
}

}