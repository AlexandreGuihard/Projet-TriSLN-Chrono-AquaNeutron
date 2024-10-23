package modele.Exceptions;

public class ChronoNotStartedException extends Exception {
    public ChronoNotStartedException(){
        super("Le chronometre n'a pas démarré");
    }
}
