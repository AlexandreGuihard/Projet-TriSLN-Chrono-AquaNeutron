package com.trisln.aquaneutron.modele;

public class ParticipantCourseRelais extends Participant {
    private String nomEquipe;
    private boolean licence;
    private int dossard;
    private Integer numLicence = 0;


    /**
     * Constructeur de la classe ParticipantCourseRelais
     * @param id l'identifiant du participant
     * @param nom le nom du participant
     * @param prenom le prénom du participant
     * @param categorie la catégorie du participant
     * @param sousCategorie la sous catégorie du participant
     * @param sexe le sexe du participant
     * @param email l'email du participant
     * @param ville la ville où habite le participant
     * @param certification la certification du participant
     * @param numTel le numéro du téléphone du participant
     * @param nomEquipe le nom de l'équipe du participant
     * @param licence si le participant possède une licence ou non
     * @param dossard le numéro de dossard du participant
     */

     public ParticipantCourseRelais(int id, String nom, String prenom, String categorie, String sousCategorie, char sexe, String email, String ville, boolean certification, String numTel, String dateNaissance, String nomEquipe, boolean licence) {
        super(id, nom, prenom, categorie, sousCategorie, sexe, email, ville, certification, numTel, dateNaissance);
        this.nomEquipe = nomEquipe;
        this.licence = licence;
    }

    public ParticipantCourseRelais(int id, String nom, String prenom, String categorie, String sousCategorie, char sexe, String email, String ville, boolean certification, String numTel, String dateNaissance, String nomEquipe, boolean licence, int dossard) {
        super(id, nom, prenom, categorie, sousCategorie, sexe, email, ville, certification, numTel, dateNaissance);
        this.nomEquipe = nomEquipe;
        this.licence = licence;
        this.dossard = dossard;
    }

    public ParticipantCourseRelais(int id, String nom, String prenom, String categorie, String sousCategorie, char sexe, String email, String ville, boolean certification, Integer numLicence, String numTel, String dateNaissance, String nomEquipe, boolean licence, int dossard) {
        super(id, nom, prenom, categorie, sousCategorie, sexe, email, ville, certification, numTel, dateNaissance);
        this.nomEquipe = nomEquipe;
        this.licence = licence;
        this.dossard = dossard;
        this.numLicence = numLicence;
    }


    /**
     * Getter du nom de l'équipe du participant
     * @return le nom de l'équipe du participant
     */
    @Override
    public String getNomEquipe(){
        return this.nomEquipe;
    }

    @Override
    public Integer getNumLicence() {
        return numLicence;
    }


    /**
     * Getter pour savoir si le participant possède une licence
     * @return "true" si le partcipant possède une licence et "false" sinon
     */
    @Override
    public boolean getLicence(){
        return this.licence;
    }

    /**
     * Setter du nom de l'équipe du participant
     * @param nomEquipe le nouveau nom de l'équipe du participant
     */
    @Override
    public void setNomEquipe(String nomEquipe){
        this.nomEquipe=nomEquipe;
    }

    /**
     * Setter pour savoir si le participant possède une licence
     * @param licence la modification de la possession (ou non) d'une licence
     */
    @Override
    public void setLicence(boolean licence){
        this.licence=licence;
    }

    @Override
    public int getDossard() {
        return this.dossard;
    }

    /**
     * @return la modélisation en chaîne de caractère du participant à une course avec relais
     */
    @Override
    public String toString(){
        return super.toString()+"\nNom d'équipe: "+nomEquipe;
    }
}