package src.modele.Exceptions;

public class WrongInformationException extends Exception {
    public WrongInformationException(){
        super("Les information ne sont pas compatible avec celle attendues");
    }
}
