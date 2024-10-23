package src.modele;

import java.util.ArrayList;
import java.util.List;

import src.modele.Participant;
import src.modele.Exceptions.WrongInformationException;

public class Classement extends ArrayList<Participant>{
    private String categorie;
    private char genre;

    public Classement(String categorie, char genre) {
        super();
        this.categorie = categorie;
        this.genre = genre;
    }

    public String getCategorie(){
        return this.categorie;
    }

    public char getGenre(){
        return this.genre;
    }

    public void ajouter(Participant participant) throws WrongInformationException{
        if (participant.getCategorie() != this.categorie || this.genre != 'M' && participant.getSexe() != this.genre){
            throw new WrongInformationException();
        } else {
            this.add(participant);
        }

    }
}
