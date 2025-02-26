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

    public List<Participant> getParticipantsCourseRelais() throws SQLException {
        List<Participant> participantsCourseRelais = new ArrayList<>();
        Statement st = this.connexion.createStatement();
        ResultSet participants = st.executeQuery("select * from PARTICIPANT");
        while (participants.next()) {
            try {
                String licence = participants.getString(13);
                String nomEquipe = participants.getString(14);

                int idP = participants.getInt(1);
                String nom = participants.getString(2);
                String prenom = participants.getString(3);
                String categorie = participants.getString(4);
                char sexe = participants.getString(5).charAt(0);
                String email = participants.getString(6);
                String ville = participants.getString(7);
                String certification = participants.getString(8);
                int tel = participants.getInt(9);

                Participant participant = new ParticipantCourseRelais(idP, nom, prenom, categorie, sexe, email, ville, certification, tel, nomEquipe, licence);
                participantsCourseRelais.add(participant);
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return participantsCourseRelais;
    }

    public List<Participant> getParticipantsLicenceCourseIndividuelles() throws SQLException {
        List<Participant> participantsLicenceCourseIndiv = new ArrayList<>();
        Statement st = this.connexion.createStatement();
        ResultSet participants = st.executeQuery("select * from PARTICIPANT");
        while (participants.next()) {
            try {
                String club = participants.getString(10);
                int numLicence = participants.getInt(11);
                String dateDeNaissance = participants.getString(12);

                int idP = participants.getInt(1);
                String nom = participants.getString(2);
                String prenom = participants.getString(3);
                String categorie = participants.getString(4);
                char sexe = participants.getString(5).charAt(0);
                String email = participants.getString(6);
                String ville = participants.getString(7);
                String certification = participants.getString(8);
                int tel = participants.getInt(9);

                Participant participant = new ParticipantLicenceCourseIndiv(idP, nom, prenom, categorie, sexe, email, ville, certification, tel, club, numLicence, dateDeNaissance);
                participantsLicenceCourseIndiv.add(participant);
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        st.close();
        return participantsLicenceCourseIndiv;
    }

    public List<Participant> getParticipantsNonLicenceCourseIndividuelles() throws SQLException {
        List<Participant> participantsNonLicenceCourseIndividuelles = new ArrayList<>();
        Statement st = this.connexion.createStatement();
        ResultSet participants = st.executeQuery("select * from PARTICIPANT");
        while (participants.next()) {
            try {
                String dateDeNaissance = participants.getString(12);

                int idP = participants.getInt(1);
                String nom = participants.getString(2);
                String prenom = participants.getString(3);
                String categorie = participants.getString(4);
                char sexe = participants.getString(5).charAt(0);
                String email = participants.getString(6);
                String ville = participants.getString(7);
                String certification = participants.getString(8);
                int tel = participants.getInt(9);

                Participant participant = new ParticipantNonLicenceCourseIndiv(idP, nom, prenom, categorie, sexe, email, ville, certification, tel, dateDeNaissance);
                participantsNonLicenceCourseIndividuelles.add(participant);
            } catch (SQLException e) {
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

    public boolean estUnParticipantCourseRelais(String licence) {
        return licence != null && !licence.isEmpty();
    }

    public boolean estUnParticipantLicenceIndividuel(String club) {
        return club != null && !club.equals("");
    }

    public List<Classement> getClassements(String categorie, String genre) throws SQLException {
        List<Classement> classements = new ArrayList<>();
        Statement st = this.connexion.createStatement();
        
        StringBuilder query = new StringBuilder(
            "SELECT C.id_Classement, C.pos_generale, C.pos_categorie, C.pos_club, C.temps, P.id_Participant, P.nom, P.prenom, P.idCategorie, P.sexe, P.email, P.ville, P.certification, P.num_Tel, P.club, P.num_Licence, P.date_Naissance, P.nom_Equipe, P.licence " +
            "FROM CLASSEMENT C " +
            "JOIN GENERER G ON C.id_Classement = G.id_Classement " +
            "JOIN PARTICIPANT P ON G.id_Participant = P.id_Participant " +
            "JOIN CATEGORIE Cat ON P.idCategorie = Cat.idCategorie "
        );

        boolean hasCondition = false;
        if (!"toutes".equals(categorie)) {
            query.append("WHERE Cat.categorie = '").append(categorie).append("' ");
            hasCondition = true;
        }
        if (!"mixte".equals(genre)) {
            if (hasCondition) {
                query.append("AND ");
            } else {
                query.append("WHERE ");
            }
            String genreBD = genre.equals("homme") ? "H" : genre.equals("femme") ? "F" : genre;
            query.append("P.sexe = '").append(genreBD).append("' ");
        }

        query.append("ORDER BY C.pos_generale");

        System.out.println("Requête SQL générée : " + query.toString());

        ResultSet lesClassements = st.executeQuery(query.toString());
        Participant leParticipant = null;

        while (lesClassements.next()) {
            int idC = lesClassements.getInt("id_Classement");
            int idP = lesClassements.getInt("id_Participant");
            String nom = lesClassements.getString("nom");
            String prenom = lesClassements.getString("prenom");
            String categorieP = lesClassements.getString("idCategorie");
            char sexe = lesClassements.getString("sexe").charAt(0);
            String email = lesClassements.getString("email");
            String ville = lesClassements.getString("ville");
            String certification = lesClassements.getString("certification");
            int tel = lesClassements.getInt("num_Tel");
            String dateDeNaissance = lesClassements.getString("date_Naissance");
            String club = lesClassements.getString("club");
            String licence = lesClassements.getString("num_Licence");

            if (this.estUnParticipantCourseRelais(licence)) {
                String nomEquipe = lesClassements.getString("nom_Equipe");
                leParticipant = new ParticipantCourseRelais(idP, nom, prenom, categorieP, sexe, email, ville, certification, tel, nomEquipe, licence);
            } else if (this.estUnParticipantLicenceIndividuel(club)) {
                int numLicence = lesClassements.getInt("num_Licence");
                leParticipant = new ParticipantLicenceCourseIndiv(idP, nom, prenom, categorieP, sexe, email, ville, certification, tel, club, numLicence, dateDeNaissance);
            } else {
                leParticipant = new ParticipantNonLicenceCourseIndiv(idP, nom, prenom, categorieP, sexe, email, ville, certification, tel, dateDeNaissance);
            }

            int posGeneral = lesClassements.getInt("pos_generale");
            String posCategorie = lesClassements.getString("pos_categorie");
            int posClub = lesClassements.getInt("pos_club");
            String temps = lesClassements.getString("temps");
            Classement classement = new Classement(idC, posGeneral, posCategorie, posClub, temps, leParticipant);
            classements.add(classement);
        }
        return classements;
    }

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

    public boolean verifConnexion(String identifiant, String motDePasse) {
        try {
            Statement st = this.connexion.createStatement();
            ResultSet rs = st.executeQuery("select * from UTILISATEUR where identifiant='" + identifiant + "'");
            if (rs.next()) {
                String motDePasseBd = rs.getString(1);
                return motDePasseBd.equals(motDePasse);
            }
            return false;
        } catch (Exception e) {
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