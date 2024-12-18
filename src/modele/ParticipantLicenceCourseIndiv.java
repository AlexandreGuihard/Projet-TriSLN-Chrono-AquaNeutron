package src.modele;

public class ParticipantLicenceCourseIndiv extends Participant{
    private String club;
    private int numLicence;

    public ParticipantLicenceCourseIndiv(int id, String nom, String prenom, String categorie, String sousCategorie, char sexe, String email, String ville, boolean certification, String numTel, String club, int numLicence, String dateDeNaissance){
        super(id, nom, prenom, categorie, sousCategorie, sexe, email, ville, certification, numTel, dateDeNaissance);
        this.club=club;
        this.numLicence=numLicence;
    }

    @Override
    public String getClub(){
        return this.club;
    }

    @Override
    public void setClub(String club){
        this.club=club;
    }

    @Override
    public Integer getNumLicence() {
        return numLicence;
    }

    @Override
    public void setNumLicence(int numLicence) {
        this.numLicence = numLicence;
    }
}
