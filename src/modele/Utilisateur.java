package src.modele;

import java.sql.SQLException;

import src.modele.Exceptions.NoSuchUserException;
import src.vue.TriSLN;

public class Utilisateur {
    private String identifiant;
    private String role;

    public Utilisateur(){
        this.identifiant = "spectateur";
        this.role = "spectateur";
    }

    public String getRole(){
        return this.role;
    }

    public void connecter(String identifiant, String mdp) throws SQLException, NoSuchUserException{
        if (TriSLN.getBd().verifConnexion(identifiant, mdp)){
            this.identifiant = identifiant;
            this.role = TriSLN.getBd().getRoleUtilisateur(identifiant);
        } else {
            throw new NoSuchUserException();
        }
    }

    public boolean estAdmin(){
        return this.role == "admin";
    }

    public boolean estBenevol(){
        return this.role == "benevol";
    }

    public boolean estSpectateur(){
        return this.role == "spectateur";
    }
}
