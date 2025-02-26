package com.trisln.aquaneutron.modele;

/**
 * Classe Participant NonLicenceCourseIndiv représantant un participant à une course individuelle sans licence
 */
public class ParticipantNonLicenceCourseIndiv extends Participant{
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
     * @param dateDeNaissance la date de naissance du participant
     */
    public ParticipantNonLicenceCourseIndiv(int id, String nom, String prenom, String categorie, String sousCategorie, char sexe, String email, String ville, boolean certification, String numTel, String dateDeNaissance){
        super(id, nom, prenom, categorie, sousCategorie, sexe, email, ville, certification, numTel, dateDeNaissance);
    }

    /**
     * @return la modélisation en chaîne de caractère du participant sans licence
     */
    @Override
    public String toString(){
        return super.toString();
    }
}