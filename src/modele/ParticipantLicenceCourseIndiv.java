package src.modele;

public class ParticipantLicenceCourseIndiv extends Participant{
    private String club;
    private boolean licence;
    private String dateDeNaissance;

    public ParticipantLicenceCourseIndiv(int id, String nom, String prenom, String categorie, char sexe, String email, String ville, String certification, int numTel, String club, boolean licence, String dateDeNaissance){
        super(id, nom, prenom, categorie, sexe, email, ville, certification, numTel);
        this.club=club;
        this.licence=licence;
        this.dateDeNaissance=dateDeNaissance;
    }

    public String getClub(){
        return this.club;
    }

    public boolean getLicence(){
        return this.licence;
    }

    public String getDateDeNaissance(){
        return this.dateDeNaissance;
    }

    public void setClub(String club){
        this.club=club;
    }

    public void setLicence(boolean licence){
        this.licence=licence;
    }

    public void setDateDeNaissance(String dateDeNaissance){
        this.dateDeNaissance=dateDeNaissance;
    }
}