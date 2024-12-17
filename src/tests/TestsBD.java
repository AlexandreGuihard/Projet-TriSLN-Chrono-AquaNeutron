package src.tests;

import java.sql.*;
import src.bd.BdTriSLN;
import src.bd.ConnexionMySQL;
import src.modele.Participant;
import java.util.List;
import java.util.ArrayList;

public class TestsBD {
    private BdTriSLN bdTriSLN;

    public TestsBD(){
        this.bdTriSLN = new BdTriSLN(new ConnexionMySQL("servinfo-maria", "DBguihard", "guihard", "guihard") );
    }

    public boolean testGetParticipantsCourseRelais(){
        boolean licence= false;
        String nomEquipe="bleu";
        int idP= 2;
        String nom= "Pilaf";
        String prenom="Mila";
        String categorie= "PO";
        char sexe= 'F';
        String email="mila.Pilaf@yahoo.fr";
        String ville="Orleans";
        boolean certification= true;
        String tel="0692243352";
        String club = "SQL";
        Participant participant = new Participant(idP, nom, prenom, categorie, sexe, email, ville, certification, tel, nomEquipe, licence);
        List<Participant> resultat =new ArrayList<>();
        resultat.add(participant);
        return assertEquals(this.bdTriSLN.getParticipantsCourseRelais(), resultat );
    }
    
    public static void main(String[] args) throws SQLException{
        TestsBD testbd = new TestsBD();
        Statement st= testbd.bdTriSLN.getConnexionMySQL().createStatement();
        ResultSet participants=st.executeQuery("SELECT * FROM PARTICIPANT NATURAL JOIN PARTICIPER JOIN EPREUVE ON PARTICIPER.id_Epreuve = EPREUVE.id_Epreuve WHERE EPREUVE.format = "relais"");
        while(participants.next()){
            System.out.println(participants);
        }
    }
}