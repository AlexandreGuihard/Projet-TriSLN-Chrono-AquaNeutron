package src.modele;

/**
 * Classe Participant NonLicenceCourseIndiv représantant un participant à une course individuelle sans licence
 */
public class ParticipantNonLicenceCourseIndiv extends Participant{
    private String dateDeNaissance;

    /**
     * Constructeur de la classe NonLicenceCourseIndiv
     * @param id l'id du participant
     * @param nom le nom du participant
     * @param prenom le prenom du participant
     * @param categorie la catégorie du participant
     * @param sexe le sexe du participant
     * @param email l'email du participant
     * @param ville la ville du participant
     * @param certification la certification du participant
     * @param numTel le numéro de téléphone du participant
     * @param dateDeNaissance la date de naissance du participant
     */
    public ParticipantNonLicenceCourseIndiv(int id, String nom, String prenom, String categorie, char sexe, String email, String ville, String certification, int numTel, String dateDeNaissance){
        super(id, nom, prenom, categorie, sexe, email, ville, certification, numTel);
        this.dateDeNaissance=dateDeNaissance;
    }

    /**
     * Getter de la date de naissance
     * @return la date de naissance du participant
     */
    public String getDateDeNaissance(){
        return this.dateDeNaissance;
    }

    /**
     * Setter de la date de naissance
     * @param dateDeNaissance la nouvelle date de naissance du participant
     */
    public void setDateDeNaissance(String dateDeNaissance){
        this.dateDeNaissance=dateDeNaissance;
    }
}