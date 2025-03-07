package com.trisln.aquaneutron.modele;

/**
 * Classe Classement représentant le classement d'un participant
 */
public class Classement{
    private int id;
    private int posGeneral;
    private String posCategorie;
    private int posClub;
    private String temps;
    private Participant participant;

    /**
     * Constructeur de la classe Classement
     * @param id l'id du classement
     * @param posGeneral la position général du participant dans le classement
     * @param posCategorie la position du participant dans sa catégorie
     * @param posClub la position du participant dans son club
     * @param temps le temps qu'a effectuer le participant
     * @param participant la participant
     */
    public Classement(int id, int posGeneral, String posCategorie, int posClub, String temps, Participant participant){ 
        this.id = id;
        this.posGeneral = posGeneral;
        this.posCategorie = posCategorie;
        this.posClub = posClub;
        this.temps = temps;
        this.participant = participant;
    }

    /**
     * Getter pour avoir l'id
     * @return l'id du classement
     */
    public int getId() {
        return id;
    }

    /**
     * Getter pour avoir la position générale
     * @return la position général du participant dans le classement
     */
    public int getPosGeneral() {
        return posGeneral;
    }

    /**
     * Getter pour avoir la position dans la catégorie
     * @return la position du participant dans sa catégorie
     */
    public String getPosCategorie() {
        return posCategorie;
    }

    /**
     * Getter pour avoir la position dans le club
     * @return la position du participant dans son club
     */
    public int getPosClub() {
        return posClub;
    }

    /**
     * Getter pour avoir le temps
     * @return le temps qu'a effectuer le participant
     */
    public String getTemps() {
        return temps;
    }

    /**
     * Getter pour avoir le participant
     * @return le participant
     */
    public Participant getParticipant() {
        return participant;
    }

    /**
     * Getter pour avoir le numero du dossard du participant
     * @return le numero du dossard
     */
    public int getDossard() {
        return participant.getDossard();
    }

    /**
     * Getter pour avoir le numero de licence du participant
     * @return le numero de licence
     */
    public int getNumLicence() {
        return participant.getNumLicence();
    }

    /**
     * Getter pour avoir le num du club du participant
     * @return le nom du club
     */
    public String getClub(){
        return participant.getClub();
    }

    /**
     * Getter pour avoir le nom de l'équipe du participant
     * @return le nom de l'équipe
     */
    public String getNomEquipe(){
        return participant.getNomEquipe();
    }

    /**
     * Setter de l'id
     * @param id l'id du classement
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setter de la position général 
     * @param posGeneral la nouvelle position général du participant
     */
    public void setPosGeneral(int posGeneral) {
        this.posGeneral = posGeneral;
    }

    /**
     * Setter de la position dans la catégorie
     * @param posCategorie la nouvelle position du participant dans sa catégorie
     */
    public void setPosCategorie(String posCategorie) {
        this.posCategorie = posCategorie;
    }

    /**
     * Setter de la position dans le club
     * @param posClub la nouvelle position du participant dans son club
     */
    public void setPosClub(int posClub) {
        this.posClub = posClub;
    }

    /**
     * Setter du temps
     * @param temps le nouveau temps du participant
     */
    public void setTemps(String temps) {
        this.temps = temps;
    }
}