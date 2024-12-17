package src.bd;
import src.modele.*;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class BdTriSLN{
    private ConnexionMySQL connexion;
    public BdTriSLN(ConnexionMySQL connexion){
        this.connexion=connexion;
    }

    public List<Participant> getParticipantsCourseRelais() throws SQLException{
        List<Participant> participantsCourseRelais=new ArrayList<>();
        Statement st=this.connexion.createStatement();
        ResultSet participants=st.executeQuery("SELECT * FROM PARTICIPANT NATURAL JOIN PARTICIPER JOIN EPREUVE ON PARTICIPER.id_Epreuve = EPREUVE.id_Epreuve WHERE EPREUVE.format = 'relais'");
        while(participants.next()){
            try{
                int idP=participants.getInt(1);
                String nom=participants.getString(2);
                String prenom=participants.getString(3);
                int idCategorie = participants.getInt(4);
                char sexe=participants.getString(5).charAt(0);
                String email=participants.getString(6);
                String ville=participants.getString(7);
                String certification=participants.getString(8);
                int tel=participants.getInt(9);
                String club = participants.getString(10);
                int num_licence = participants.getInt(11);
                String date_naissance = participants.getString(12);
                String nomEquipe=participants.getString(13);
                boolean licence=participants.getBoolean(14);
                Participant participant = new ParticipantCourseRelais(idP, nom, prenom, idCategorie, sexe, email, ville, certification, tel, nomEquipe, licence);//,chronometre manque un constructeur qui prend en compte chrono TODO
                participantsCourseRelais.add(participant);
            }
            catch(SQLException e){
                System.err.println(e);
            }
        }
        st.close();
        return participantsCourseRelais;
    }

    public List<Participant> getParticipantsLicenceCourseIndividuelles() throws SQLException{
        List<Participant> participantsLicenceCourseIndiv=new ArrayList<>();
        Statement st=this.connexion.createStatement();
        ResultSet participants=st.executeQuery("select * from PARTICIPANT");
        while(participants.next()){
            try{
                String club=participants.getString(10);
                int numLicence=participants.getInt(11);
                String dateDeNaissance=participants.getString(12);

                int idP=participants.getInt(1);
                String nom=participants.getString(2);
                String prenom=participants.getString(3);
                String categorie=participants.getString(4);
                char sexe=participants.getString(5).charAt(0);
                String email=participants.getString(6);
                String ville=participants.getString(7);
                String certification=participants.getString(8);
                int tel=participants.getInt(9);

                Participant participant=new ParticipantLicenceCourseIndiv(idP, nom, prenom, categorie, sexe, email, ville, certification, tel, club, numLicence, dateDeNaissance);
                participantsLicenceCourseIndiv.add(participant);
            }
            catch(SQLException e){
                System.err.println(e);
            }
        }
        st.close();
        return participantsLicenceCourseIndiv;
    }

    public List<Participant> getParticipantsNonLicenceCourseIndividuelles() throws SQLException{
        List<Participant> participantsNonLicenceCourseIndividuelles=new ArrayList<>();
        Statement st=this.connexion.createStatement();
        ResultSet participants=st.executeQuery("select * from PARTICIPANT");
        while(participants.next()){
            try{
                String dateDeNaissance=participants.getString(12);

                int idP=participants.getInt(1);
                String nom=participants.getString(2);
                String prenom=participants.getString(3);
                String categorie=participants.getString(4);
                char sexe=participants.getString(5).charAt(0);
                String email=participants.getString(6);
                String ville=participants.getString(7);
                String certification=participants.getString(8);
                int tel=participants.getInt(9);

                Participant participant=new ParticipantNonLicenceCourseIndiv(idP, nom, prenom, categorie, sexe, email, ville, certification, tel, dateDeNaissance);

                participantsNonLicenceCourseIndividuelles.add(participant);
            }
            catch(SQLException e){
                System.err.println(e);
            }
        }
        return participantsNonLicenceCourseIndividuelles;
    }

    public List<Course> getCourses() throws SQLException{
        List<Course> courses=new ArrayList<>();
        Statement st=this.connexion.createStatement();
        ResultSet epreuves=st.executeQuery("select * from EPREUVE");
        while(epreuves.next()){
            int idE=epreuves.getInt(1);
            String nom=epreuves.getString(2);
            String format=epreuves.getString(3);
            String categorie=epreuves.getString(4);
            String heureDepart=epreuves.getString(5);
            double prix=epreuves.getDouble(6);
            Course course=new Course(idE, nom, format, categorie, heureDepart, prix);
            courses.add(course);
        }
        st.close();
        return courses;
    }

    public List<Classement> getClassements() throws SQLException{
        List<Classement> classements=new ArrayList<>();
        Statement st=this.connexion.createStatement();
        ResultSet lesClassements=st.executeQuery("select * from CLASSEMENT");
        Participant leParticipant=null;
        while(lesClassements.next()){
            int idC=lesClassements.getInt(1);
            ResultSet participant=st.executeQuery("select * from PARTICIPANT natural join GENERER where id_Classement="+idC);
            if(participant.next()){
                int idP=participant.getInt(1);
                String nom=participant.getString(2);
                String prenom=participant.getString(3);
                String categorie=participant.getString(4);
                char sexe=participant.getString(5).charAt(0);
                String email=participant.getString(6);
                String ville=participant.getString(7);
                String certification=participant.getString(8);
                int tel=participant.getInt(9);

                String dateDeNaissance=participant.getString(12);

                boolean licence=participant.getBoolean(14);
                String club=participant.getString(10);
                if(this.estUnParticipantCourseRelais(licence)){
                    String nomEquipe=participant.getString(13);
                    leParticipant=new ParticipantCourseRelais(idP, nom, prenom, categorie, sexe, email, ville, certification, tel, nomEquipe, licence);
                }
                else if(this.estUnParticipantLicenceIndividuel(club)){
                    int numLicence=participant.getInt(11);
                    leParticipant=new ParticipantLicenceCourseIndiv(idP, nom, prenom, categorie, sexe, email, ville, certification, tel, club, numLicence, dateDeNaissance);
                }
                else{
                    leParticipant=new ParticipantNonLicenceCourseIndiv(idP, nom, prenom, categorie, sexe, email, ville, certification, tel, dateDeNaissance);
                }
            }
            int posGeneral=lesClassements.getInt(2);
            String posCategorie=lesClassements.getString(3);
            int posClub=lesClassements.getInt(4);
            String temps=lesClassements.getString(5);
            Classement classement=new Classement(idC, posGeneral, posCategorie, posClub, temps, leParticipant);
            classements.add(classement);
        }
        return classements;
    }

    public void ajouterCourse(Course course) throws SQLException{
        PreparedStatement addCourse=this.connexion.prepareStatement("insert into EPREUVE values(?, ?, ?, ?, ?, ?)");
        int idE=course.getId();
        String nom=course.getNom();
        String format=course.getFormat();
        String categorie=course.getCategorie();
        String heureDepart=course.getHeureDepart();
        double prix=course.getPrix();
        addCourse.setInt(1, idE);
        addCourse.setString(2, nom);
        addCourse.setString(3, format);
        addCourse.setString(4, categorie);
        addCourse.setString(5, heureDepart);
        addCourse.setDouble(6, prix);
        addCourse.executeUpdate();
        addCourse.close();
    }

    public boolean verifConnexion(String identifiant, String motDePasse){
        try{
        Statement st=this.connexion.createStatement();
        ResultSet rs=st.executeQuery("select * from UTILISATEUR where identifiant='"+identifiant+"'");
        if(rs.next()){
            String motDePasseBd=rs.getString(2);
            return motDePasseBd.equals(motDePasse);
        }
        return false;
        }
        catch (Exception e){
            System.out.println("erreure");
            return false;
        }
        
        
    }
}