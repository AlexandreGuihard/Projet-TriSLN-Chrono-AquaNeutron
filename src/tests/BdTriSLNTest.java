package src.tests;

import org.junit.*;
import static org.junit.Assert.assertEquals;


import java.sql.*;
import src.bd.BdTriSLN;
import src.bd.ConnexionMySQL;
import src.modele.Participant;
import src.modele.ParticipantCourseRelais;
import src.modele.Classement;
import src.modele.Course;

import java.util.List;
import java.util.ArrayList;

public class BdTriSLNTest {
    private BdTriSLN bdTriSLN;

    public BdTriSLNTest(){
        this.bdTriSLN = new BdTriSLN(new ConnexionMySQL("servinfo-maria", "DBguihard", "guihard", "guihard") );
    }

    // @Test
    // public void testGetParticipantsCourseRelais() throws SQLException{
    //     boolean licence= false;
    //     String nomEquipe="bleu";
    //     int idP= 1;
    //     String Categorie = "1";
    //     String nom= "Dupont";
    //     String prenom="Carle";
    //     char sexe= 'F';
    //     String email="x.carle@yahoo.fr";
    //     String ville="Paris";
    //     boolean certification= true;
    //     String tel="0647882565";
    //     String dateDeNaissance = "2001-06-15";
    //     Participant participant = new ParticipantCourseRelais(idP, nom, prenom, Categorie, sexe, email, ville, certification, tel, dateDeNaissance, licence, nomEquipe);
    //     List<Participant> resultatUn =new ArrayList<>();
    //     List<Participant> resultatDeux =new ArrayList<>();
    //     resultatDeux = this.bdTriSLN.getParticipantsCourseRelais();
    //     resultatUn.add(participant);
    //     String compareUn = "";
    //     String compareDeux = "";
    //     for(Participant particip: resultatUn){
    //         compareUn = compareUn + particip.toString();
    //     }
    //     for(Participant particip: resultatDeux){
    //         compareDeux = compareDeux + particip.toString();
    //     }
    //     assertEquals(compareUn, compareDeux);
    // }

    @Test
    public void testGetCourses() throws SQLException{
        List<Course> listeCourses = new ArrayList<>();
        Course courseUn = new Course(1, "Course feur", "Relais", "PU", "12:00:00", 130);
        Course courseDeux = new Course(2, "Marathon X", "S", "CA", "08:30:00", 200);
        Course courseTrois = new Course(3, "Trail des montagnes", "XS", "S", "06:00:00", 150);
        Course courseQuatre = new Course(4, "Sprint 100m", "M", "MI", "10:00:00", 50);
        Course courseCinq = new Course(5, "Course nocturne", "XS", "PO", "21:00:00", 100);
        Course courseSix = new Course(6, "Randonnée pour débutants", "S", "CA", "09:00:00", 75);
        Course courseSept = new Course(7, "Ultra-marathon", "Relais", "S", "04:30:00", 300);
        listeCourses.add(courseUn);
        listeCourses.add(courseDeux);
        listeCourses.add(courseTrois);
        listeCourses.add(courseQuatre);
        listeCourses.add(courseCinq);
        listeCourses.add(courseSix);
        listeCourses.add(courseSept);
        List<Course> lesCoursesReelles = new ArrayList<>();
        lesCoursesReelles = this.bdTriSLN.getCourses();
        String compareReel = "";
        String compareVoulu = "";
        for(Course course: listeCourses){
            compareVoulu = compareVoulu + course.toString();
        }
        for(Course course: lesCoursesReelles){
            compareReel = compareReel + course.toString();
        }
        assertEquals(compareVoulu, compareReel);
    }

    @Test
    public void testGetClassements(){
        List<Classement> listeClassements = new ArrayList<>();
        

    }

    @Test
    public void testVerifConnexion() throws SQLException{
        assertEquals(true, this.bdTriSLN.verifConnexion("test", "test"));
        assertEquals(true, this.bdTriSLN.verifConnexion("admin", "admin"));
        assertEquals(true, this.bdTriSLN.verifConnexion("siecle", "siecle"));
    }
}