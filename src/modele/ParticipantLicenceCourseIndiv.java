package src.modele;

public class ParticipantLicenceCourseIndiv extends Participant{
    private String club;
    private int numLicence;

    public ParticipantLicenceCourseIndiv(int id, String nom, String prenom, int idCategorie, char sexe, String email, String ville, boolean certification, String numTel, String club, int numLicence, String dateDeNaissance, boolean licence){
        super(id, nom, prenom, idCategorie, sexe, email, ville, certification, numTel, dateDeNaissance, licence);
        this.club=club;
        this.numLicence=numLicence;
    }

    public String getClub(){
        return this.club;
    }


    public void setClub(String club){
        this.club=club;
    }

    public int getNumLicence() {
        return numLicence;
    }

    public void setNumLicence(int numLicence) {
        this.numLicence = numLicence;
    }
}
