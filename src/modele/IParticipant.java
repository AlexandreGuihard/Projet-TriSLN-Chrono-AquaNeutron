package src.modele;

/**
 * Interface contenant les getters et setters sur les attributs variant selon le type de participant (relais, licence individuelle, sans licence individuelle)
 */
public interface IParticipant {
    // Implémentées dans ParticipantLicenceCourseIndiv
    public String getClub();
    public void setClub(String club);
    public Integer getNumLicence();
    public void setNumLicence(int numLicence);
    // Implémentées dans ParticipantCourseRelais
    public String getNomEquipe();
    public void setNomEquipe(String nomEquipe);
    public boolean getLicence();
    public void setLicence(boolean licence);
}