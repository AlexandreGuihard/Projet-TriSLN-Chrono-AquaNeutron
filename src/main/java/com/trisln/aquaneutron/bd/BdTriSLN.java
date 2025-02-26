package com.trisln.aquaneutron.bd;

import com.trisln.aquaneutron.modele.*;
import com.trisln.aquaneutron.modele.Exceptions.NoSuchUserException;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class BdTriSLN {
    private ConnexionMySQL connexion;

    public BdTriSLN(ConnexionMySQL connexion) {
        this.connexion = connexion;
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
     * @param nomEquipe le nom d'Ã©quipe du participant
     * @param licence true dans le cas d'un participant relais
     * @param numLicence le numÃ©ro de licence (null dans le cas d'un participant relais)
     * @return true si c'est un participant Ã  un relais sinon false
     * @throws SQLException
     */
    public boolean isParticipantsRelais(String club, String nomEquipe, boolean licence, int numLicence) throws SQLException{
        Statement st=connexion.createStatement();
        ResultSet isParticipRelais=st.executeQuery("select isParticipantsRelais('"+club+"','"+nomEquipe+"',"+ licence+", "+numLicence+")");
        if(isParticipRelais.next()){
            return isParticipRelais.getBoolean(1);
        }
        return false;
    }

    /**
     * @param club le club du participant
     * @param nomEquipe le nom d'Ã©quipe du participant (null dans le cas d'un participant avec licence)
     * @param licence false dans le cas d'un participant avec licence individuelle
     * @param numLicence le numÃ©ro de licence du participant
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
     * @param nomEquipe le nom d'Ã©quipe du participant (null dans le cas d'un participant sans licence individuelle)
     * @param licence false dans le cas d'un participant sans licence individuelle
     * @param numLicence le numÃ©ro de licence du participant (null dans le cas d'un participant sans licence individuelle)
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
     * @return la liste des participants Ã  une course avec licence individuelle de la bd
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

    public List<Course> getCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();
        Statement st = this.connexion.createStatement();
        ResultSet rs = st.executeQuery("select * from COURSE");
        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String format = rs.getString("format");
            String categorie = rs.getString("categorie");
            String heureDepart = rs.getString("heureDepart");
            double prix = rs.getDouble("prix");
            Course course = new Course(id, nom, format, categorie, heureDepart, prix);
            courses.add(course);
        }
        return courses;
    }

    ///**
    // * @return la liste des classements de la bd
    // * @throws SQLException
    // */
    //public List<Classement> getClassements() throws SQLException{
    //    List<Classement> classements=new ArrayList<>();
    //    Statement st=this.connexion.createStatement();
    //    ResultSet lesClassements=st.executeQuery("select * from CLASSEMENT");
    //    Participant leParticipant=null;
    //    while(lesClassements.next()){
    //        int idC=lesClassements.getInt(1);
    //        ResultSet participant=st.executeQuery("select * from PARTICIPANT natural join GENERER where id_Classement="+idC);
    //        if(participant.next()){
    //            int idP=participant.getInt(1);
    //            String nom=participant.getString(2);
    //            String prenom=participant.getString(3);
    //            int idCategorie=participant.getInt(4);
    //            String categorie=getCategorie(idCategorie);
    //            String sousCategorie=getSousCategorie(idCategorie);
    //            char sexe=participant.getString(5).charAt(0);
    //            String email=participant.getString(6);
    //            String ville=participant.getString(7);
    //            boolean certification=participant.getBoolean(8);
    //            String tel=participant.getString(9);
    //            String club=participant.getString(10);
    //            int numLicence=participant.getInt(11);
    //            String dateDeNaissance=participant.getString(12);
    //            String nomEquipe=participant.getString(13);
    //            boolean licence=participant.getBoolean(14);
//
    //            if(isParticipantsRelais(club, nomEquipe, licence, numLicence)){
    //                leParticipant=new ParticipantCourseRelais(idP, nom, prenom, categorie, sousCategorie, sexe, email, ville, certification, tel, dateDeNaissance, nomEquipe, licence);
    //            }
    //            else if(isParticipantsLicenceIndiv(club, nomEquipe, licence, numLicence)){
    //                leParticipant=new ParticipantLicenceCourseIndiv(idP, nom, prenom, categorie, sousCategorie, sexe, email, ville, certification, tel, club, numLicence, dateDeNaissance);
    //            }
    //            else{
    //                leParticipant=new ParticipantNonLicenceCourseIndiv(idP, nom, prenom, categorie, sousCategorie, sexe, email, ville, certification, tel, dateDeNaissance);
    //            }
    //        }
    //        String genreBD = genre.equals("homme") ? "H" : genre.equals("femme") ? "F" : genre;
    //        query.append("P.sexe = '").append(genreBD).append("' ");
    //    }
//
    //    query.append("ORDER BY C.pos_generale");
//
    //    System.out.println("Requête SQL générée : " + query.toString());
//
    //    ResultSet lesClassements = st.executeQuery(query.toString());
    //    Participant leParticipant = null;
//
    //    while (lesClassements.next()) {
    //        int idC = lesClassements.getInt("id_Classement");
    //        int idP = lesClassements.getInt("id_Participant");
    //        String nom = lesClassements.getString("nom");
    //        String prenom = lesClassements.getString("prenom");
    //        String categorieP = lesClassements.getString("idCategorie");
    //        char sexe = lesClassements.getString("sexe").charAt(0);
    //        String email = lesClassements.getString("email");
    //        String ville = lesClassements.getString("ville");
    //        String certification = lesClassements.getString("certification");
    //        int tel = lesClassements.getInt("num_Tel");
    //        String dateDeNaissance = lesClassements.getString("date_Naissance");
    //        String club = lesClassements.getString("club");
    //        String licence = lesClassements.getString("num_Licence");
//
    //        if (this.estUnParticipantCourseRelais(licence)) {
    //            String nomEquipe = lesClassements.getString("nom_Equipe");
    //            leParticipant = new ParticipantCourseRelais(idP, nom, prenom, categorieP, sexe, email, ville, certification, tel, nomEquipe, licence);
    //        } else if (this.estUnParticipantLicenceIndividuel(club)) {
    //            int numLicence = lesClassements.getInt("num_Licence");
    //            leParticipant = new ParticipantLicenceCourseIndiv(idP, nom, prenom, categorieP, sexe, email, ville, certification, tel, club, numLicence, dateDeNaissance);
    //        } else {
    //            leParticipant = new ParticipantNonLicenceCourseIndiv(idP, nom, prenom, categorieP, sexe, email, ville, certification, tel, dateDeNaissance);
    //        }
//
    //        int posGeneral = lesClassements.getInt("pos_generale");
    //        String posCategorie = lesClassements.getString("pos_categorie");
    //        int posClub = lesClassements.getInt("pos_club");
    //        String temps = lesClassements.getString("temps");
    //        Classement classement = new Classement(idC, posGeneral, posCategorie, posClub, temps, leParticipant);
    //        classements.add(classement);
    //    }
    //    return classements;
    //}

    public void ajouterCourse(Course course) throws SQLException {
        String query = "INSERT INTO COURSE (id, nom, format, categorie, heureDepart, prix) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = this.connexion.prepareStatement(query);
        ps.setInt(1, course.getId());
        ps.setString(2, course.getNom());
        ps.setString(3, course.getFormat());
        ps.setString(4, course.getCategorie());
        ps.setString(5, course.getHeureDepart());
        ps.setDouble(6, course.getPrix());
        ps.executeUpdate();
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
}