package src.modele;

import java.util.ArrayList;
import java.util.List;

import modele.Participant;
import modele.Exceptions.WrongInformationException;
import modele.Participant;

public class Classement{
    private int id;
    private int posGeneral;
    private String posCategorie;
    private int posClub;
    private String temps;
    private Participant participant;

    public Classement(int id, int posGeneral, String posCategorie, int posClub, String temps){ 
        this.id = id;
        this.posGeneral = posGeneral;
        this.posCategorie = posCategorie;
        this.posClub = posClub;
        this.temps = temps;
    }

    public int getId() {
        return id;
    }

    public int getPosGeneral() {
        return posGeneral;
    }

    public String getPosCategorie() {
        return posCategorie;
    }

    public int getPosClub() {
        return posClub;
    }

    public String getTemps() {
        return temps;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPosGeneral(int posGeneral) {
        this.posGeneral = posGeneral;
    }

    public void setPosCategorie(String posCategorie) {
        this.posCategorie = posCategorie;
    }

    public void setPosClub(int posClub) {
        this.posClub = posClub;
    }
    public void setTemps(String temps) {
        this.temps = temps;
    }
    public void setParticipant(Participant participant) {
        this.participant = participant;
    }
}
