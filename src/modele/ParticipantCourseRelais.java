package src.modele;

/**
 * Classe ParticipantCourseRelais représentant un participant à une course de relais
 */
public class ParticipantCourseRelais extends Participant{
    private String nomEquipe;


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
    public ParticipantCourseRelais(int id, String nom, String prenom, int idCategorie, char sexe, String email, String ville, boolean certification, String numTel, String dateNaissance, boolean licence, String nomEquipe){
        super(id, nom, prenom, idCategorie, sexe, email, ville, certification, numTel, dateNaissance, licence);
        this.nomEquipe=nomEquipe;
    }

    /**
     * Getter du nom de l'équipe du participant
     * @return le nom de l'équipe du participant
     */
    public String getNomEquipe(){
        return this.nomEquipe;
    }

    /**
     * Setter du nom de l'équipe du participant
     * @param nomEquipe le nouveau nom de l'équipe du participant
     */
    public void setNomEquipe(String nomEquipe){
        this.nomEquipe=nomEquipe;
    }

    @Override
    public String toString() {
        return super.toString() + ", nomEquipe='" + this.nomEquipe; 
}


}