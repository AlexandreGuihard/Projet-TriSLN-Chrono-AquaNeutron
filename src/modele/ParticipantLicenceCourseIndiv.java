package src.modele;

public class ParticipantLicenceCourseIndiv extends Participant{
    private String club;
    private String dateDeNaissance;
    private int numLicence;

    public ParticipantLicenceCourseIndiv(int id, String nom, String prenom, String categorie, char sexe, String email, String ville, String certification, int numTel, String club, int numLicence, String dateDeNaissance){
        super(id, nom, prenom, categorie, sexe, email, ville, certification, numTel);
        this.club=club;
        this.numLicence=numLicence;
        this.dateDeNaissance=dateDeNaissance;
    }

    public String getClub(){
        return this.club;
    }

    public String getDateDeNaissance(){
        return this.dateDeNaissance;
    }

    public void setClub(String club){
        this.club=club;
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
