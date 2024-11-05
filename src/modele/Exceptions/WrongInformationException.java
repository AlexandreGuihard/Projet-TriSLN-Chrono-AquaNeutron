package modele.Exceptions;
/**
 * Classe pour avertir que les informations saisies sont mauvaises
 */

public class WrongInformationException extends Exception {
    public WrongInformationException(){
        super("Les information ne sont pas compatible avec celle attendues");
    }
}