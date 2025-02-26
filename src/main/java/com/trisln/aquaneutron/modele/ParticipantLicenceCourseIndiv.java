package com.trisln.aquaneutron.modele;

public class ParticipantLicenceCourseIndiv extends Participant{
    private String club;
    private int numLicence;

    /**
     * Constructeur de la classe NonLicenceCourseIndiv
     * @param id l'id du participant
     * @param nom le nom du participant
     * @param prenom le prenom du participant
     * @param categorie la catégorie du participant
     * @param sousCategorie la sous catégorie du participant
     * @param sexe le sexe du participant
     * @param email l'email du participant
     * @param ville la ville du participant
     * @param certification la certification du participant
     * @param numTel le numéro de téléphone du participant
     * @param club le club du participant
     * @param numLicence le numéro de licence du participant
     * @param dateDeNaissance la date de naissance du participant
     */
    public ParticipantLicenceCourseIndiv(int id, String nom, String prenom, String categorie, String sousCategorie, char sexe, String email, String ville, boolean certification, String numTel, String club, int numLicence, String dateDeNaissance){
        super(id, nom, prenom, categorie, sousCategorie, sexe, email, ville, certification, numTel, dateDeNaissance);
        this.club=club;
        this.numLicence=numLicence;
    }

    /**
     * Getter du club du participant
     * @return le club du participant
     */
    @Override
    public String getClub(){
        return this.club;
    }

    /**
     * Setter du club du participant
     * @param club le nouveau club du participant
     */
    @Override
    public void setClub(String club){
        this.club=club;
    }

    /**
     * Getter du numéro de licence
     * @return le numéro de licence du participant
     */
    @Override
    public Integer getNumLicence() {
        return numLicence;
    }

    /**
     * Setter du numéro de licence
     * @param numLicence le nouveau numéro de licence
     */
    @Override
    public void setNumLicence(int numLicence) {
        this.numLicence = numLicence;
    }

    /**
     * @return la modélisation en chaîne de caractère du participant avec licence
     */
    @Override
    public String toString(){
        return super.toString()+"\nClub: "+club+" | Numéro de licence: "+numLicence;
    }
}
