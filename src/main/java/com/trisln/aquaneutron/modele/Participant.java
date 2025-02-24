package com.trisln.aquaneutron.modele;

/** Classe abstraite participant
 */
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

    /**
     * 
     * @param id l'id du participant
     * @param nom le nom du participant
     * @param prenom le prenom du participant
     * @param categorie la catégorie à la laquelle le participant appartient
     * @param sexe le sexe du participant
     * @param email l'email du participant
     * @param ville la ville du participant
     * @param certification le certificat du participant
     * @param numTel le numéro de téléphone du participant
     */
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
    public String getCategorie(){
        return this.categorie;
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
    public String getCertification(){
        return this.certification;
    }

    /**
     * Getter pour avoir le numéro de téléphone
     * @return le numéro de téléphone du participant
     */
    public int getTel(){
        return this.numTel;
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
    public void setCategorie(String categorie){
        this.categorie=categorie;
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
    public void setCertification(String certification){
        this.certification=certification;
    }

    /**
     * Setter du numéro de téléphone
     * @param id le nouveau numéro de téléphone du participant
     */
    public void setTel(int numTel){
        this.numTel=numTel;
    }
}