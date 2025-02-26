package com.trisln.aquaneutron.modele;

/** Classe abstraite participant
 */
public abstract class Participant implements IParticipant{
    private int id;
    private String nom;
    private String prenom;
    private String categorie;
    private String sousCategorie;
    private char sexe;
    private String email;
    private String ville;
    private boolean certification;
    private String numTel;
    private String dateNaissance;
    private Classement classement;
    private String club; // Ajout de la variable club
    private int dossard; // Ajout de la variable dossard
    private String licence; // Ajout de la variable licence

    /**
     * Constructeur de la classe. Appelé par les classes enfants
     * @param id l'id du participant
     * @param nom le nom du participant
     * @param prenom le prenom du participant
     * @param categorie la catégorie à la laquelle le participant appartient
     * @param sousCategorie la sous catégorie du participant
     * @param sexe le sexe du participant
     * @param email l'email du participant
     * @param ville la ville du participant
     * @param certification le certificat du participant
     * @param numTel le numéro de téléphone du participant
     * @param dateNaissance la date de naissance du participant
     */
    public Participant(int id, String nom, String prenom, String categorie, String sousCategorie, char sexe, String email, String ville, boolean certification, String numTel, String dateNaissance){
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.categorie=categorie;
        this.sousCategorie=sousCategorie;
        this.sexe=sexe;
        this.email=email;
        this.ville=ville;
        this.certification=certification;
        this.numTel=numTel;
        this.dateNaissance=dateNaissance;
    }

    /**
     * Getter pour avoir l'id
     * @return l'id du participant
     */
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
    /**
     * Getter de la sous catégorie du participant
     * @return la sous catégorie du participant
     */
    public String getSousCategorie(){
        return sousCategorie;
    }

    /**
     * Getter de la sous catégorie du participant
     * @return la sous catégorie du participant
     */
    public String getSousCategorie(){
        return sousCategorie;
    }

    /**
     * Getter pour avoir le sexe
     * @return le sexe du participant
     */
    public char getSexe(){
        return this.sexe;
    }

    public String getEmail(){
        return this.email;
    }

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
        return dateNaissance;
    }

    /**
     * Setter de l'id
     * @param id le nouvel id du participant
     */
    public void setId(int id){
        this.id = id;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public void setPrenom(String prenom){
        this.prenom = prenom;
    }

    public void setCategorie(String categorie){
        this.categorie = categorie;
    }

    /**
     * Setter de la sous catégorie
     * @param sousCategorie la nouvelle sous catégorie du participant
     */
    public void setSousCategorie(String sousCategorie){
        this.sousCategorie=sousCategorie;
    }

    /**
     * Setter de la sous catégorie
     * @param sousCategorie la nouvelle sous catégorie du participant
     */
    public void setSousCategorie(String sousCategorie){
        this.sousCategorie=sousCategorie;
    }

    /**
     * Setter du sexe
     * @param sexe le nouveau sexe du participant
     */
    public void setSexe(char sexe){
        this.sexe = sexe;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setVille(String ville){
        this.ville = ville;
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
     * @param id le nouveau numéro de téléphone du participant
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

    // Implémentées dans les classes enfants
    /**
     * Implémentée dans une classe enfant
     * @return null
     */
    @Override
    public String getClub(){
        return null;
    }

    /**
     * Implémentée dans une classe enfant
     * @param club 
     */
    @Override
    public void setClub(String club){
        return;
    }

    /**
     * Implémentée dans une classe enfant
     * @return null
     */
    @Override
    public Integer getNumLicence() {
        // Pas de numéro de licence
        return null;
    }

    /**
     * Implémentée dans une classe enfant
     * @param numLicence
     */
    @Override
    public void setNumLicence(int numLicence) {
        return;
    }

    /**
     * Implémentée dans une classe enfant
     * @return null
     */
    @Override
    public String getNomEquipe(){
        return null;
    }

    /**
     * Implémentée dans une classe enfant
     * @param nomEquioe
     */
    @Override
    public void setNomEquipe(String nomEquioe){
        return;
    }

    /**
     * Implémentée dans une classe enfant
     * @return null
     */
    @Override
    public boolean getLicence(){
        return false;
    } 

    /**
     * Implémentée dans une classe enfant
     * @param licence
     */
    @Override
    public void setLicence(boolean licence){
        return;
    }

    /**
     * @return la modélisation en chaîne de caractère du participant
     */
    @Override
    public String toString(){
        return "Participant: "+prenom+nom+", "+sexe+" | Né le: "+dateNaissance+"\nTel: "+numTel+" | Email: "+email+" | Habite à: "+ville+"\nCatégorie: "+categorie+" | "+sousCategorie;
    }
}