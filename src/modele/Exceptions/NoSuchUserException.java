package src.modele.Exceptions;

/**
 * Classe pour avertir que l'utilisateur n'existe pas
 */
public class NoSuchUserException extends Exception{
    public NoSuchUserException(){
        super("Cette utilisateur n'existe pas");
    }
}
