package src.modele;

/**
 * Classe ParticipantCourseRelais représentant un participant à une course de relais
 */
public class ParticipantCourseRelais extends Participant{
    private String nomEquipe;
    private boolean licence;


    /**
     * Constructeur de la classe ParticipantCourseRelais
     * @param id l'identifiant du participant
     * @param nom le nom du participant
     * @param prenom le prénom du participant
     * @param categorie la catégorie du participant
     * @param sexe le sexe du participant
     * @param email l'email du participant
     * @param ville la ville où habite le participant
     * @param certification la certification du participant
     * @param numTel le numéro du téléphone du participant
     * @param nomEquipe le nom de l'équipe du participant
     * @param licence si le participant possède une licence ou non
     */
    public ParticipantCourseRelais(int id, String nom, String prenom, String categorie, String sousCategorie, char sexe, String email, String ville, boolean certification, String numTel, String dateNaissance, String nomEquipe, boolean licence){
        super(id, nom, prenom, categorie, sousCategorie, sexe, email, ville, certification, numTel, dateNaissance);
        this.nomEquipe=nomEquipe;
        this.licence=licence;
    }


    /**
     * Getter du nom de l'équipe du participant
     * @return le nom de l'équipe du participant
     */
    @Override
    public String getNomEquipe(){
        return this.nomEquipe;
    }

    /**
     * Getter pour savoir si le participant possède une licence
     * @return "true" si le partcipant possède une licence et "false" sinon
     */
    @Override
    public boolean getLicence(){
        return this.licence;
    }

    /**
     * Setter du nom de l'équipe du participant
     * @param nomEquipe le nouveau nom de l'équipe du participant
     */
    @Override
    public void setNomEquipe(String nomEquipe){
        this.nomEquipe=nomEquipe;
    }

    /**
     * Setter pour savoir si le participant possède une licence
     * @param licence la modification de la possession (ou non) d'une licence
     */
    @Override
    public void setLicence(boolean licence){
        this.licence=licence;
    }
}