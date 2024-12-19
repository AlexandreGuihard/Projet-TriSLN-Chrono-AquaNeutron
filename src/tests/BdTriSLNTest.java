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
        this.bdTriSLN = new BdTriSLN(new ConnexionMySQL("servinfo-maria", "DBdelahaye", "delahaye", "delahaye") );
    }

    @Test
    public void testGetParticipantsCourseRelais() throws SQLException{
        Participant participantUn = new ParticipantCourseRelais(1, "Dupont", "Carle", "MP", null, 'F', "x.carle@yahoo.fr", "Paris", true, "0647882565", "2001-01-20", "bleu", true);
        Participant participantDeux = new ParticipantCourseRelais(3, "Sparkis", "Devoid", "S", "S1", 'M', "devoid.Sparkis@yahoo.fr", "Angers", false, "0695476132", "2001-02-03", "jaune", true);
        Participant participantTrois = new ParticipantCourseRelais(9, "Pdf", "Amine", "CA", null, 'M', "gen@gmail.com", "Orleans", true, "0638991660", "2002-01-18", "rouge", true);
        List<Participant> listeParticipants =new ArrayList<>();
        List<Participant> listeParticipantReel =new ArrayList<>();
        listeParticipantReel = this.bdTriSLN.getParticipantsCourseRelais();
        listeParticipants.add(participantUn);
        listeParticipants.add(participantDeux);
        listeParticipants.add(participantTrois);
        String compareReel = "";
        String compareVoulu = "";
        for(Participant particip: listeParticipants){
            compareVoulu = compareVoulu + particip.toString() + "\n";
        }
        for(Participant particip: listeParticipantReel){
            compareReel = compareReel + particip.toString() + "\n";
        }
        assertEquals(compareVoulu, compareReel);
    }
    
    @Test
    public void testGetCourses() throws SQLException{
        List<Course> listeCourses = new ArrayList<>();
        Course courseUn = new Course(1, "Course feur", "Relais", "PU", "12:00:00", 130);
        Course courseDeux = new Course(2, "Marathon X", "S", "CA", "08:30:00", 200);
        Course courseTrois = new Course(3, "Trail des montagnes", "xs", "S", "06:00:00", 150);
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
            compareVoulu = compareVoulu + course.toString() + "\n";
        }
        for(Course course: lesCoursesReelles){
            compareReel = compareReel + course.toString()+ "\n";
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