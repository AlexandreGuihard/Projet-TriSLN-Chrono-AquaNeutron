package com.trisln.aquaneutron.modele;

public class ParticipantCourseRelais extends Participant {
    private String nomEquipe;
    private String licence; // Changez le type en String

    public ParticipantCourseRelais(int id, String nom, String prenom, String categorie, char sexe, String email, String ville, String certification, int numTel, String nomEquipe, String licence) {
        super(id, nom, prenom, categorie, sexe, email, ville, certification, numTel);
        this.nomEquipe = nomEquipe;
        this.licence = licence;
    }

    public String getNomEquipe() {
        return this.nomEquipe;
    }

    public String getLicence() { // Changez le type de retour en String
        return this.licence;
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public void setLicence(String licence) { // Changez le type de paramètre en String
        this.licence = licence;
    }
}