package src.tests;

import org.junit.*;
import static org.junit.Assert.Assertequals;

import java.sql.*;
import src.bd.BdTriSLN;
import src.bd.ConnexionMySQL;
import src.modele.Participant;
import src.modele.ParticipantCourseRelais;

import java.util.List;
import java.util.ArrayList;

public class BdTriSLNTest {
    private BdTriSLN bdTriSLN;

    public BdTriSLNTest(){
        this.bdTriSLN = new BdTriSLN(new ConnexionMySQL("servinfo-maria", "DBdelahaye", "delahaye", "delahaye") );
    }

    @Test
    public void testGetParticipantsCourseRelais(){
        boolean licence= false;
        String nomEquipe="bleu";
        int idP= 1;
        int idCategorie = 1;
        String nom= "Dupont";
        String prenom="Carle";
        char sexe= 'F';
        String email="x.carle@yahoo.fr";
        String ville="Paris";
        boolean certification= true;
        String tel="0647882565";
        String dateDeNaissance = "2001-06-15";
        Participant participant = new ParticipantCourseRelais(idP, nom, prenom, idCategorie, sexe, email, ville, certification, tel, dateDeNaissance, licence, nomEquipe);
        List<Participant> resultatUn =new ArrayList<>();
        List<Participant> resultatDeux =new ArrayList<>();
        resultatDeux = this.bdTriSLN.getParticipantsCourseRelais();
        resultatUn.add(participant);
        String compareUn = "";
        String compareDeux = "";
        for(Participant particip: resultatUn){
            compareUn = compareUn + particip.toString();
        }
        for(Participant particip: resultatDeux){
            compareDeux = compareDeux + particip.toString();
        }
        assertEquals(compareUn, compareDeux);
    }

    public static void main(String[] args) throws SQLException{
        TestsBD testbd = new TestsBD();
        System.out.println(testbd.testGetParticipantsCourseRelais());
        
    }
}