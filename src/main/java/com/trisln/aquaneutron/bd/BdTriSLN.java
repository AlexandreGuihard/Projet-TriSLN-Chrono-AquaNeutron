package com.trisln.aquaneutron.bd;

import com.trisln.aquaneutron.modele.*;
import com.trisln.aquaneutron.modele.Exceptions.NoSuchUserException;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import com.trisln.aquaneutron.modele.Exceptions.NoSuchUserException;

public class BdTriSLN {
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
     * Getter de l'id de la catégorie à partir de son nom de catégorie
     * @param categorie le nom de la catégorie
     * @return l'id de la catégorie
     * @throws SQLException
     */
    public int getIdCategorie(String categorie) throws SQLException{
        int idCategorie = 0;
        Statement st=connexion.createStatement();
        ResultSet categ=st.executeQuery("select getIdCategorie('"+categorie+"', null)");
        if(categ.next()){
            idCategorie=categ.getInt(1);
        }
        st.close();
        return idCategorie;
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
        ResultSet leFormat=st.executeQuery("select getFormatFromId("+idFormat+")");
        if(leFormat.next()){
            format=leFormat.getString(1);
        }
        st.close();
        return format;
    }

    public Integer getIdFormat(String format) throws SQLException{
        Integer idFormat = null;
        Statement st=connexion.createStatement();
        ResultSet leFormat=st.executeQuery("select getIdFormatFromFormat('"+format+"')");
        if(leFormat.next()){
            idFormat=leFormat.getInt(1);
        }
        st.close();
        return idFormat;
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
     * @param numDossard le numéro du dossart du participant
     * @param course la course en cours
     * @return true si le participant participe à la course et false sinon
     * @throws SQLException
     */
    public boolean isParticipantOfCourse(int numDossard, Course course) throws SQLException{
        int idEpreuve = course.getId();
        Statement st=connexion.createStatement();
        ResultSet isParticipNonLicenceIndiv=st.executeQuery("select isParticipantOfCourse("+numDossard+","+idEpreuve+")");
        if(isParticipNonLicenceIndiv.next()){
            return isParticipNonLicenceIndiv.getBoolean(1);
        }
        return false;
    }

    /**
     * @return la liste des participants à une course bien précise
     * @throws SQLException
     */
    public List<Participant> getParticipantsACourse(int idEpreuve) throws SQLException {
        List<Participant> participants = new ArrayList<>();
        Statement st = this.connexion.createStatement();
        String query = "SELECT * FROM PARTICIPANT JOIN PARTICIPER ON PARTICIPANT.id_Participant = PARTICIPER.id_Participant WHERE PARTICIPER.id_Epreuve = " + idEpreuve;
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            int idParticipant = rs.getInt(1);
            String nom = rs.getString(2);
            String prenom = rs.getString(3);
            int idCategorie = rs.getInt(4);
            String categorie=getCategorie(idCategorie);
            String sousCategorie=getSousCategorie(idCategorie);
            String sexe = rs.getString(5);
            char sexeChar = sexe.charAt(0);
            String email = rs.getString(6);
            String ville = rs.getString(7);
            boolean certification = rs.getBoolean(8);
            String numTel = rs.getString(9);
            String club = rs.getString(10);
            int numLicence = rs.getInt(11);
            String dateNaissance = rs.getString(12);
            String nomEquipe = rs.getString(13);
            boolean licence = rs.getBoolean(14);
            if(isParticipantsLicenceIndiv(club, nomEquipe, licence, numLicence)){
                Participant participant = new ParticipantLicenceCourseIndiv(idParticipant, nom, prenom, categorie, sousCategorie, sexeChar, email, ville, certification, numTel, club, numLicence, dateNaissance);
                participants.add(participant);
                continue;
            }
            if(isParticipantsNonLicenceIndiv(club, nomEquipe, licence, numLicence)){
                Participant participant = new ParticipantNonLicenceCourseIndiv(idParticipant, nom, prenom, categorie, sousCategorie, sexeChar, email, ville, certification, numTel, dateNaissance);
                participants.add(participant);
                continue;
            }
            if(isParticipantsRelais(club, nomEquipe, licence, numLicence)){
                Participant participant = new ParticipantCourseRelais(idParticipant, nom, prenom, categorie, sousCategorie, sexeChar, email, ville, certification, numTel, dateNaissance, nomEquipe, licence);
                participants.add(participant);
                continue;
            }
        }
        st.close();  
        return participants;
    }
    

    public Participant getParticipantFromId(int id) throws SQLException{
        List<Participant> participantsRelais=getParticipantsCourseRelais();
        List<Participant> participantsLicence=getParticipantsLicenceCourseIndividuelles();
        List<Participant> participantsNonLicence=getParticipantsNonLicenceCourseIndividuelles();
        for(Participant p:participantsRelais){
            if(p.getId()==id){
                return p;
            }
        }
        for(Participant p:participantsLicence){
            if(p.getId()==id){
                return p;
            }
        }
        for(Participant p:participantsNonLicence){
            if(p.getId()==id){
                return p;
            }
        }
        return null;
    }

    /**
     * @return la liste des participants Ã  une course avec relais de la bd
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
            String format=epreuves.getString(3);
            String categorie=epreuves.getString(4);
            String heureDepart=epreuves.getString(5);
            double prix=epreuves.getDouble(6);
            Course course=new Course(idE, nom, format, categorie, heureDepart, prix);
            courses.add(course);
        }
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
     * @throws SQLException
     */
    public void ajouterCourse(String nomCourse, String format, String categorie, String heureDepart, double prix) throws SQLException{
        int newId = 1;
        Statement stmt = this.connexion.createStatement();
        ResultSet rs = stmt.executeQuery("select MAX(id_Epreuve) FROM EPREUVE");
        if (rs.next()){
            newId = rs.getInt(1) + 1;
        }
        int idCategorie = this.getIdCategorie(categorie);
        System.out.println(idCategorie);
        // int idFormat = this.getIdFormat(format);
        PreparedStatement addCourse=this.connexion.prepareStatement("insert into EPREUVE values(?, ?, ?, ?, ?, ?)");     
        addCourse.setInt(1, newId);
        addCourse.setString(2, nomCourse);
        addCourse.setString(3, format);
        addCourse.setInt(4, idCategorie);
        addCourse.setString(5, heureDepart);
        addCourse.setDouble(6, prix);

        addCourse.executeUpdate();
        addCourse.close();
    }

    public Utilisateur getUtilisateurFromIdentifiant(String identifiant) throws SQLException{
        Statement st=this.connexion.createStatement();
        ResultSet utilisateur=st.executeQuery("select * from UTILISATEUR where identifiant='"+identifiant+"'");
        if(utilisateur.next()){
            String email=utilisateur.getString(2);
            String role=utilisateur.getString(4);
            String nom=utilisateur.getString(5);
            String prenom=utilisateur.getString(6);
            return new Utilisateur(identifiant, email, role, nom, prenom);
        }
        return null;
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

    public String tempsEnSTring(int NombreSecondes){
        int heures = NombreSecondes / 3600;
        int minutes = (NombreSecondes % 3600) / 60;
        int secondes = NombreSecondes % 60;
        String temps = String.format("%02d:%02d:%02d", heures, minutes, secondes);
        return temps;
    }

    public void genererClassement(int idParticipant, int posGeneral, int idEpreuve, int tempsCourse, String club) throws SQLException{
        String timeString = tempsEnSTring(tempsCourse);

        String queryCateg = "select getPositionCategorie(?, ?)";
        PreparedStatement preparSt = connexion.prepareStatement(queryCateg);
        preparSt.setInt(1, idParticipant);
        preparSt.setInt(2, idEpreuve);
        ResultSet rs = preparSt.executeQuery();
        int posCategorie = 0;
        if (rs.next()) {
            posCategorie = rs.getInt(1);
        }
        rs.close();
        preparSt.close();

        String queryClub = "select getPositionClub(?, ?, ?)";
        PreparedStatement preparState = connexion.prepareStatement(queryClub);
        preparState.setInt(1, idParticipant);
        preparState.setString(2, club);
        preparState.setInt(3, idEpreuve);
        ResultSet reSet = preparState.executeQuery();
        int posClub = 0;
        if (reSet.next()) {
            posClub = reSet.getInt(1);
        }
        reSet.close();
        preparState.close();

        
        PreparedStatement classementPst=connexion.prepareStatement("insert into CLASSEMENT (pos_generale, pos_categorie, pos_club, temps) values(?, ?, ?, ?)");
        classementPst.setInt(1, posGeneral);
        classementPst.setInt(2, posCategorie);
        classementPst.setInt(3, posClub);
        classementPst.setTime(4, java.sql.Time.valueOf(timeString));
        classementPst.executeUpdate();
        classementPst.close();

        Statement st=this.connexion.createStatement();
        ResultSet resultS=st.executeQuery("select max(id_Classement) from CLASSEMENT");
        int idClassement= 0;
        while (resultS.next()){
            idClassement =resultS.getInt(1);
        }
        PreparedStatement genererPst=connexion.prepareStatement("insert into GENERER values(?, ?, ?)");
        genererPst.setInt(1, idClassement);
        genererPst.setInt(2, idEpreuve);
        genererPst.setInt(3, idParticipant);
        genererPst.executeUpdate();
        genererPst.close();
    }

    /**
     * Récupère le participant à partir du numéro de son dossard
     * @param leDossard le numéro du dossard
     * @return le participant qui possède le dossard au bon numéro
     * @throws SQLException
     */
    public Participant getParticipantByDossard(int leDossard) throws SQLException {
        Statement st=this.connexion.createStatement();
        Participant participant = null;
        String sql = "select * FROM PARTICIPANT JOIN DOSSARD on PARTICIPANT.id_Participant = DOSSARD.id_Participant WHERE DOSSARD.num_dossard = "+ leDossard;
        ResultSet rs=st.executeQuery(sql);
        while (rs.next()) {
            int idParticipant = rs.getInt(1);
            String nom = rs.getString(2);
            String prenom = rs.getString(3);
            int idCategorie = rs.getInt(4);
            String categorie=getCategorie(idCategorie);
            String sousCategorie=getSousCategorie(idCategorie);
            String sexe = rs.getString(5);
            char sexeChar = sexe.charAt(0);
            String email = rs.getString(6);
            String ville = rs.getString(7);
            boolean certification = rs.getBoolean(8);
            String numTel = rs.getString(9);
            String club = rs.getString(10);
            int numLicence = rs.getInt(11);
            String dateNaissance = rs.getString(12);
            String nomEquipe = rs.getString(13);
            boolean licence = rs.getBoolean(14);
            if(isParticipantsLicenceIndiv(club, nomEquipe, licence, numLicence)){
                participant = new ParticipantLicenceCourseIndiv(idParticipant, nom, prenom, categorie, sousCategorie, sexeChar, email, ville, certification, numTel, club, numLicence, dateNaissance);
                continue;
            }
            if(isParticipantsNonLicenceIndiv(club, nomEquipe, licence, numLicence)){
                participant = new ParticipantNonLicenceCourseIndiv(idParticipant, nom, prenom, categorie, sousCategorie, sexeChar, email, ville, certification, numTel, dateNaissance);
                continue;
            }
            if(isParticipantsRelais(club, nomEquipe, licence, numLicence)){
                participant = new ParticipantCourseRelais(idParticipant, nom, prenom, categorie, sousCategorie, sexeChar, email, ville, certification, numTel, dateNaissance, nomEquipe, licence);
                continue;
            }
        }
        st.close();
        return participant;
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
    public boolean verifConnexion(String identifiant, String motDePasse){
        try{
        Statement st=this.connexion.createStatement();
        ResultSet rs=st.executeQuery("select * from UTILISATEUR where identifiant='"+identifiant+"'");
        if(rs.next()){
            String motDePasseBd=rs.getString(3);
            return motDePasseBd.equals(motDePasse);
        }
        return false;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public String getEmail(String identifiant) throws SQLException, NoSuchUserException {
        Statement s = this.connexion.createStatement();
        ResultSet rs = s.executeQuery("select email from UTILISATEUR where identifiant = '" + identifiant + "'");
        if (rs.next()) {
            return rs.getString(1);
        } else {
            throw new NoSuchUserException();
        }
    }

    public List<String> getEmailAdresses() throws SQLException {
        List<String> emails = new ArrayList<>();
        Statement s = this.connexion.createStatement();
        ResultSet rs = s.executeQuery("select email from UTILISATEUR");
        while (rs.next()) {
            String email = rs.getString(1);
            emails.add(email);
        }
        return emails;
    }

    public String getRoleUtilisateur(String identifiant) throws SQLException, NoSuchUserException {
        Statement s = this.connexion.createStatement();
        ResultSet rs = s.executeQuery("select * from UTILISATEUR where identifiant='" + identifiant + "'");
        if (rs.next()) {
            return rs.getString(2);
        } else {
            throw new NoSuchUserException();
        }
    }

    public String getIdentifiantByEmail(String email) throws SQLException, NoSuchUserException {
        PreparedStatement ps = this.connexion.prepareStatement("select identifiant from UTILISATEUR where email = ?");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getString(1);
        } else {
            throw new NoSuchUserException();
        }
    }

    public void changePassword(String identifiant, String newPassword) throws SQLException {
        PreparedStatement ps = this.connexion.prepareStatement("update UTILISATEUR set mot_de_passe = ? where identifiant= ?");
        ps.setString(1, newPassword);
        ps.setString(2, identifiant);
        ps.executeUpdate();
    }

    public void enregistrerToken(String identifiant, String token) throws SQLException {
        PreparedStatement ps = this.connexion.prepareStatement("update UTILISATEUR set token_reinit = ? where identifiant = ?");
        ps.setString(1, token);
        ps.setString(2, identifiant);
        ps.executeUpdate();
    }

    public void ajouterBenevole(String identifiant, String mdp, String email) throws SQLException {
        PreparedStatement ps = this.connexion.prepareStatement("insert into UTILISATEUR values (?,?,?,?)");
        ps.setString(1, identifiant);
        ps.setString(2, "benevol");
        ps.setString(3, mdp);
        ps.setString(4, email);
        ps.executeUpdate();
    }

    public void updateParticipant(Participant participant) throws SQLException{
        PreparedStatement ps=this.connexion.prepareStatement("call updateParticipant(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        ps.setInt(1, participant.getId());
        ps.setString(2, participant.getNom());
        ps.setString(3, participant.getPrenom());
        ps.setString(4, participant.getSexe()+"");
        ps.setString(5, participant.getDateNaissance());
        ps.setString(6, participant.getCategorie());
        ps.setString(7, participant.getSousCategorie());
        ps.setString(8, participant.getClub());
        ps.setString(9, participant.getNomEquipe());
        ps.setString(10, participant.getEmail());
        ps.setString(11, participant.getTel());
        ps.setBoolean(12, participant.getCertification());
        ps.setInt(13, participant.getNumLicence());
        ps.setString(14, participant.getVille());
        ps.setBoolean(15, participant.getLicence());
        ps.executeUpdate();
    }
}