package src.modele;

public class ParticipantNonLicenceCourseIndiv extends Participant{
    private String dateDeNaissance;

    public ParticipantNonLicenceCourseIndiv(int id, String nom, String prenom, String categorie, char sexe, String email, String ville, String certification, int numTel, String dateDeNaissance){
        super(id, nom, prenom, categorie, sexe, email, ville, certification, numTel);
        this.dateDeNaissance=dateDeNaissance;
    }

    public String getDateDeNaissance(){
        return this.dateDeNaissance;
    }

    public void setDateDeNaissance(String dateDeNaissance){
        this.dateDeNaissance=dateDeNaissance;
    }
}