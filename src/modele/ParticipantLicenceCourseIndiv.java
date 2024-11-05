package src.modele;

public class ParticipantLicenceCourseIndiv extends Participant{
    private String club;
    private String licence;
    private String dateDeNaissance;
    private int numLicence;


    public ParticipantLicenceCourseIndiv(int id, String nom, String prenom, String categorie, char sexe, String email, String ville, String certification, int numTel, String club, String licence, String dateDeNaissance){
        super(id, nom, prenom, categorie, sexe, email, ville, certification, numTel);
        this.club=club;
        this.licence=licence;
        this.dateDeNaissance=dateDeNaissance;
    }

    public ParticipantLicenceCourseIndiv(int id, String nom, String prenom, String categorie, char sexe, String email, String ville, String certification, int numTel, String club, int numLicence, String dateDeNaissance){
        super(id, nom, prenom, categorie, sexe, email, ville, certification, numTel);
        this.club=club;
        this.numLicence=numLicence;
        this.dateDeNaissance=dateDeNaissance;
    }

//a choisir definitivement entre les deux

    public String getClub(){
        return this.club;
    }

    public String getLicence(){
        return this.licence;
    }

    public String getDateDeNaissance(){
        return this.dateDeNaissance;
    }

    public void setClub(String club){
        this.club=club;
    }

    public void setLicence(String licence){
        this.licence=licence;
    }

    public void setDateDeNaissance(String dateDeNaissance){
        this.dateDeNaissance=dateDeNaissance;
    }

    public int getNumLicence() {
        return numLicence;
    }

    public void setNumLicence(int numLicence) {
        this.numLicence = numLicence;
    }
}
