package modele;
import java.util.ArrayList;
import java.util.List;
import modele.Participant;

public class Classement {
    private List<Participant> lesParticipants;
    private int id;
    private String posCategorie;
    private int posClub;

    public Classement(int id, String posCategorie, int posClub){
        this.id = id;
        this.posCategorie = posCategorie;
        this.posClub = posClub;
        this.lesParticipants = new ArrayList<Participant>();
    }

    public List<Participant> getLesParticipants() {
        return lesParticipants;
    }

    public int getId() {
        return id;
    }

    public String getPosCategorie() {
        return posCategorie;
    }

    public int getPosClub() {
        return posClub;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPosCategorie(String posCategorie) {
        this.posCategorie = posCategorie;
    }

    public void setPosClub(int posClub) {
        this.posClub = posClub;
    }

    public void addParticipant(Participant participant) {
        this.lesParticipants.add(participant);
    }
}
