package modele;

public class ParticipantCourseRelais extends Participant{
    private String nomEquipe;
    private String licence;

    public ParticipantCourseRelais(int id, String nom, String prenom, String categorie, char sexe, String email, String ville, String certification, int numTel, String nomEquipe, String licence){
        super(id, nom, prenom, categorie, sexe, email, ville, certification, numTel);
        this.nomEquipe=nomEquipe;
        this.licence=licence;
    }

    public String getNomEquipe(){
        return this.nomEquipe;
    }

    public String getLicence(){
        return this.licence;
    }

    public void setNomEquipe(String nomEquipe){
        this.nomEquipe=nomEquipe;
    }

    public void setLicence(String licence){
        this.licence=licence;
    }
}