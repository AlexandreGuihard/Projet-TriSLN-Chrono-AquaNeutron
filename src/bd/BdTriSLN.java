package src.bd;
import src.modele.*;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class BdTriSLN{
    private ConnexionMySQL connexion;
    /**
     * Constructeur de la classe de la bd
     * @param connexion la connexion au serveur
     */
    public BdTriSLN(ConnexionMySQL connexion){
        this.connexion=connexion;
    }

    /**
     * Getter de la catégorie à partir de son id
     * @param idCategorie l'id de la catégorie
     * @return la catégorie
     * @throws SQLException
     */
    public String getCategorie(int idCategorie) throws SQLException{
        String categorie=null;
        Statement st=connexion.createStatement();
        ResultSet categ=st.executeQuery("select getCategorieFromId("+idCategorie+")");
        if(categ.next()){
            categorie=categ.getString(1);
        }
        st.close();
        return categorie;
    }

    /**
     * Getter de la sous catégorie à partir de son id
     * @param idCategorie l'id de la catégorie
     * @return la sous catégorie
     * @throws SQLException
     */
    public String getSousCategorie(int idCategorie) throws SQLException{
        String sousCategorie=null;
        Statement st=connexion.createStatement();
        ResultSet sousCateg=st.executeQuery("select getSousCategorieFromId("+idCategorie+")");
        if(sousCateg.next()){
            sousCategorie=sousCateg.getString(1);
        }
        return sousCategorie;
    }

    public String getFormat(int idFormat) throws SQLException{
        String format=null;
        Statement st=connexion.createStatement();
        ResultSet leFormat=st.executeQuery("select getFormat("+idFormat+")");
        if(leFormat.next()){
            format=leFormat.getString(1);
        }
        return format;
    }

    /**
     * @param club le club du participant (null dans le cas d'un participant relais)
     * @param nomEquipe le nom d'équipe du participant
     * @param licence true dans le cas d'un participant relais
     * @param numLicence le numéro de licence (null dans le cas d'un participant relais)
     * @return true si c'est un participant à un relais sinon false
     * @throws SQLException
     */
    public boolean isParticipantsRelais(String club, String nomEquipe, boolean licence, int numLicence) throws SQLException{
        Statement st=connexion.createStatement();
        ResultSet isParticipRelais=st.executeQuery("select isParticipantsRelais('"+club+"','"+nomEquipe+"',"+ licence+","+numLicence+")");
        if(isParticipRelais.next()){
            return isParticipRelais.getBoolean(1);
        }
        return false;
    }

    /**
     * @param club le club du participant
     * @param nomEquipe le nom d'équipe du participant (null dans le cas d'un participant avec licence)
     * @param licence false dans le cas d'un participant avec licence individuelle
     * @param numLicence le numéro de licence du participant
     * @return true si c'est un participant avec une licence individuelle sinon false
     * @throws SQLException
     */
    public boolean isParticipantsLicenceIndiv(String club, String nomEquipe, boolean licence, int numLicence) throws SQLException{
        Statement st=connexion.createStatement();
        ResultSet isParticipLicenceIndiv=st.executeQuery("select isParticipantsLicenceIndiv('"+club+"','"+nomEquipe+"',"+ licence+","+numLicence+")");
        if(isParticipLicenceIndiv.next()){
            return isParticipLicenceIndiv.getBoolean(1);
        }
        return false;
    }

    /**
     * @param club le club du participant (null dans le cas d'un participant sans licence individuelle)
     * @param nomEquipe le nom d'équipe du participant (null dans le cas d'un participant sans licence individuelle)
     * @param licence false dans le cas d'un participant sans licence individuelle
     * @param numLicence le numéro de licence du participant (null dans le cas d'un participant sans licence individuelle)
     * @return true si c'est un participant sans licence individuelle sinon false
     * @throws SQLException
     */
    public boolean isParticipantsNonLicenceIndiv(String club, String nomEquipe, boolean licence, int numLicence) throws SQLException{
        Statement st=connexion.createStatement();
        ResultSet isParticipNonLicenceIndiv=st.executeQuery("select isParticipantsNonLicenceIndiv('"+club+"','"+nomEquipe+"',"+ licence+","+numLicence+")");
        if(isParticipNonLicenceIndiv.next()){
            return isParticipNonLicenceIndiv.getBoolean(1);
        }
        return false;
    }

    /**
     * @return la liste des participants à une course avec relais de la bd
     * @throws SQLException
     */
    public List<Participant> getParticipantsCourseRelais() throws SQLException{
        List<Participant> participantsCourseRelais=new ArrayList<>();
        Statement st=this.connexion.createStatement();
        ResultSet participants=st.executeQuery("select * from PARTICIPANT");
        while(participants.next()){
            int idP=participants.getInt(1);
            String nom=participants.getString(2);
            String prenom=participants.getString(3);
            int idCategorie = participants.getInt(4);
            String categorie=getCategorie(idCategorie);
            String sousCategorie=getSousCategorie(idCategorie);
            char sexe=participants.getString(5).charAt(0);
            String email=participants.getString(6);
            String ville=participants.getString(7);
            boolean certification=participants.getBoolean(8);
            String tel=participants.getString(9);
            String club=participants.getString(10);
            int numLicence=participants.getInt(11);
            String dateNaissance = participants.getString(12);
            String nomEquipe=participants.getString(13);
            boolean licence=participants.getBoolean(14);
            if(isParticipantsRelais(club, nomEquipe, licence, numLicence)){
                Participant participant = new ParticipantCourseRelais(idP, nom, prenom, categorie, sousCategorie, sexe, email, ville, certification, tel, dateNaissance, nomEquipe, licence);
                participantsCourseRelais.add(participant);
            }
        }
        st.close();
        return participantsCourseRelais;
    }

    /**
     * @return la liste des participants à une course avec licence individuelle de la bd
     * @throws SQLException
     */
    public List<Participant> getParticipantsLicenceCourseIndividuelles() throws SQLException{
        List<Participant> participantsLicenceCourseIndiv=new ArrayList<>();
        Statement st=this.connexion.createStatement();
        ResultSet participants=st.executeQuery("select * from PARTICIPANT");
        while(participants.next()){
            int idP=participants.getInt(1);
            String nom=participants.getString(2);
            String prenom=participants.getString(3);
            int idCategorie = participants.getInt(4);
            String categorie=getCategorie(idCategorie);
            String sousCategorie=getSousCategorie(idCategorie);
            char sexe=participants.getString(5).charAt(0);
            String email=participants.getString(6);
            String ville=participants.getString(7);
            boolean certification=participants.getBoolean(8);
            String tel=participants.getString(9);
            String club=participants.getString(10);
            int numLicence=participants.getInt(11);
            String dateNaissance = participants.getString(12);
            String nomEquipe=participants.getString(13);
            boolean licence=participants.getBoolean(14);
            if(isParticipantsLicenceIndiv(club, nomEquipe, licence, numLicence)){
                Participant participant=new ParticipantLicenceCourseIndiv(idP, nom, prenom, categorie, sousCategorie, sexe, email, ville, certification, tel, club, numLicence, dateNaissance);
                participantsLicenceCourseIndiv.add(participant);
            }
        }
        st.close();
        return participantsLicenceCourseIndiv;
    }

    /**
     * @return la liste des participants sans licence individuelle de la bd
     * @throws SQLException
     */
    public List<Participant> getParticipantsNonLicenceCourseIndividuelles() throws SQLException{
        List<Participant> participantsNonLicenceCourseIndividuelles=new ArrayList<>();
        Statement st=this.connexion.createStatement();
        ResultSet participants=st.executeQuery("select * from PARTICIPANT");
        while(participants.next()){
            try{
                int idP=participants.getInt(1);
                String nom=participants.getString(2);
                String prenom=participants.getString(3);
                int idCategorie = participants.getInt(4);
                String categorie=getCategorie(idCategorie);
                String sousCategorie=getSousCategorie(idCategorie);
                char sexe=participants.getString(5).charAt(0);
                String email=participants.getString(6);
                String ville=participants.getString(7);
                boolean certification=participants.getBoolean(8);
                String tel=participants.getString(9);
                String club=participants.getString(10);
                int numLicence=participants.getInt(11);
                String dateNaissance = participants.getString(12);
                String nomEquipe=participants.getString(13);
                boolean licence=participants.getBoolean(14);
                if(isParticipantsNonLicenceIndiv(club, nomEquipe, licence, numLicence)){
                    Participant participant=new ParticipantNonLicenceCourseIndiv(idP, nom, prenom, categorie, sousCategorie, sexe, email, ville, certification, tel, dateNaissance);
                    participantsNonLicenceCourseIndividuelles.add(participant);
                }
            }
            catch(SQLException e){
                System.err.println(e);
            }
        }
        return participantsNonLicenceCourseIndividuelles;
    }

    /**
     * @return la liste des courses de la bd
     * @throws SQLException
     */
    public List<Course> getCourses() throws SQLException{
        List<Course> courses=new ArrayList<>();
        Statement st=this.connexion.createStatement();
        ResultSet epreuves=st.executeQuery("select * from EPREUVE");
        while(epreuves.next()){
            int idE=epreuves.getInt(1);
            String nom=epreuves.getString(2);
            int idFormat=epreuves.getInt(3);
            String format=getFormat(idFormat);
            int idCategorie=epreuves.getInt(4);
            String categorie=getCategorie(idCategorie);
            String heureDepart=epreuves.getString(5);
            double prix=epreuves.getDouble(6);
            Course course=new Course(idE, nom, format, categorie, heureDepart, prix);
            courses.add(course);
        }
        st.close();
        return courses;
    }

    /**
     * @return la liste des classements de la bd
     * @throws SQLException
     */
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
                int idCategorie=participant.getInt(4);
                String categorie=getCategorie(idCategorie);
                String sousCategorie=getSousCategorie(idCategorie);
                char sexe=participant.getString(5).charAt(0);
                String email=participant.getString(6);
                String ville=participant.getString(7);
                boolean certification=participant.getBoolean(8);
                String tel=participant.getString(9);
                String club=participant.getString(10);
                int numLicence=participant.getInt(11);
                String dateDeNaissance=participant.getString(12);
                String nomEquipe=participant.getString(13);
                boolean licence=participant.getBoolean(14);

                if(isParticipantsRelais(club, nomEquipe, licence, numLicence)){
                    leParticipant=new ParticipantCourseRelais(idP, nom, prenom, categorie, sousCategorie, sexe, email, ville, certification, tel, dateDeNaissance, nomEquipe, licence);
                }
                else if(isParticipantsLicenceIndiv(club, nomEquipe, licence, numLicence)){
                    leParticipant=new ParticipantLicenceCourseIndiv(idP, nom, prenom, categorie, sousCategorie, sexe, email, ville, certification, tel, club, numLicence, dateDeNaissance);
                }
                else{
                    leParticipant=new ParticipantNonLicenceCourseIndiv(idP, nom, prenom, categorie, sousCategorie, sexe, email, ville, certification, tel, dateDeNaissance);
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

    /**
     * Ajoute une course dans la bd
     * @param course la course ajoutée dans la bd
     * @throws SQLException
     */
    public void ajouterCourse(String nomCourse, String format, String categorie, String heureDepart, double prix) throws SQLException{
        PreparedStatement addCourse=this.connexion.prepareStatement("insert into EPREUVE values(?, ?, ?, ?, ?, ?)");
        
        addCourse.setString(1, nomCourse);
        addCourse.setString(2, format);
        addCourse.setString(3, categorie);
        addCourse.setString(4, heureDepart);
        addCourse.setDouble(5, prix);

        addCourse.executeUpdate();
        addCourse.close();
    }

    /**
     * Ajoute un participant à la bd
     * @param participant le participant ajoutée à la bd
     * @throws SQLException
     */
    public void ajouterParticipant(Participant participant) throws SQLException{
        PreparedStatement st=connexion.prepareStatement("call addParticipant(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        
        String nom=participant.getNom();
        String prenom=participant.getPrenom();
        String categorie=participant.getCategorie();
        String sousCategorie=participant.getSousCategorie();
        String sexe=participant.getSexe()+"";
        String email=participant.getEmail();
        String ville=participant.getVille();
        boolean certification=participant.getCertification();
        String tel=participant.getTel();
        String club=participant.getClub();
        Integer numLicence=participant.getNumLicence();
        if(numLicence==null){
            numLicence=-1;
        }
        String dateNaissance=participant.getDateNaissance();
        String nomEquipe=participant.getNomEquipe();
        boolean licence=participant.getLicence();

        st.setString(1, nom);
        st.setString(2, prenom);
        st.setString(3, categorie);
        st.setString(4, sousCategorie);
        st.setString(5, sexe);
        st.setString(6, email);
        st.setString(7, ville);
        st.setBoolean(8, certification);
        st.setString(9, tel);
        st.setString(10, club);
        st.setInt(11, numLicence);
        st.setString(12, dateNaissance);
        st.setString(13, nomEquipe);
        st.setBoolean(14, licence);
        
        st.executeUpdate();
        st.close();
    }

    /**
     * Supprime un participant de la bd à partir de son id
     * @param idParticipant l'id du participant supprimé
     * @throws SQLException
     */
    public void supprimerParticipant(int idParticipant) throws SQLException{
        Statement st=connexion.createStatement();
        st.executeUpdate("call deleteParticipant("+idParticipant+")");
        st.close();
    }

    /**
     * Modifie les informations d'un participant
     * @param participant le participant avec les informations modifiées
     * @throws SQLException
     */
    public void modifierParticipant(Participant participant) throws SQLException{
        PreparedStatement st=connexion.prepareStatement("call updateParticipant(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,?, ?)");
        
        int idP=participant.getId();
        String nom=participant.getNom();
        String prenom=participant.getPrenom();
        String categorie=participant.getCategorie();
        String sousCategorie=participant.getSousCategorie();
        String sexe=participant.getSexe()+"";
        String email=participant.getEmail();
        String ville=participant.getVille();
        boolean certification=participant.getCertification();
        String tel=participant.getTel();
        String club=participant.getClub();
        Integer numLicence=participant.getNumLicence();
        String dateNaissance=participant.getDateNaissance();
        String nomEquipe=participant.getNomEquipe();

        st.setInt(1, idP);
        st.setString(2, nom);
        st.setString(3, prenom);
        st.setString(4, categorie);
        st.setString(5, sousCategorie);
        st.setString(6, sexe);
        st.setString(7, email);
        st.setString(8, ville);
        st.setBoolean(9, certification);
        st.setString(10, tel);
        st.setString(11, club);
        st.setInt(12, numLicence);
        st.setString(13, dateNaissance);
        st.setString(14, nomEquipe);
        
        st.executeUpdate();
        st.close();
    }

    /**
     * Vérifie que l'identifiant est dans la bd et que le mot de passe est correct
     * @param identifiant l'identifiant rentré sur la page de connexion
     * @param motDePasse le mot de passe rentré sur la page de connexion
     * @return true si l'identifiant et le mot de passe correspondent à ceux dans la bd
     */
    public boolean verifConnexion(String identifiant, String motDePasse) throws SQLException{
        Statement st=this.connexion.createStatement();
        ResultSet rs=st.executeQuery("select * from UTILISATEUR where identifiant='"+identifiant+"'");
        if(rs.next()){
            String motDePasseBd=rs.getString(2);
            return motDePasseBd.equals(motDePasse);
        }
        return false;
    }
        
        
}