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
        }
    }

    public boolean estConnecte(){
        return this.role != "spectateur";
    }
}
