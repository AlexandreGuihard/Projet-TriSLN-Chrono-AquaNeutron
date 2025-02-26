package com.trisln.aquaneutron.bd;


import com.trisln.aquaneutron.modele.*;
import com.trisln.aquaneutron.modele.Exceptions.NoSuchUserException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;



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
        
        String genreCondition = !"mixte".equalsIgnoreCase(genre) ? "AND P.sexe = '" + (genre.equalsIgnoreCase("homme") ? "H" : "F") + "'" : "";
        String categorieCondition = !"toutes".equalsIgnoreCase(categorie) ? "AND Cat.categorie = '" + categorie + "'" : "";

        String query = "SELECT C.id_Classement, C.pos_generale AS Positions, C.temps AS Temps, CONCAT(P.nom, ' ', P.prenom) AS Nom_Prénom, " +
                "P.club AS Club_Equipe, D.num_dossard AS Dossard, Cat.categorie AS Catégorie, C.pos_categorie AS Classements_Catégorie, " +
                "P.num_Licence AS Licence, P.id_Participant " +
                "FROM CLASSEMENT C " +
                "JOIN GENERER G ON C.id_Classement = G.id_Classement " +
                "JOIN PARTICIPANT P ON G.id_Participant = P.id_Participant " +
                "JOIN DOSSARD D ON P.id_Participant = D.id_Participant " +
                "JOIN CATEGORIE Cat ON P.idCategorie = Cat.idCategorie " +
                "WHERE 1=1 " + genreCondition + " " + categorieCondition + " ORDER BY C.pos_generale";

        System.out.println("Requête SQL générée : " + query);

        ResultSet lesClassements = st.executeQuery(query);

        while (lesClassements.next()) {
            int idC = lesClassements.getInt("id_Classement");
            int idP = lesClassements.getInt("id_Participant");
            String nom = lesClassements.getString("Nom_Prénom");
            String prenom = "";
            String club = lesClassements.getString("Club_Equipe");
            String licence = lesClassements.getString("Licence");
            String categorieP = lesClassements.getString("Catégorie");
            char sexe = genre.equals("homme") ? 'M' : genre.equals("femme") ? 'F' : 'M';
            String email = "";
            String ville = "";
            String certification = "";
            int tel = 0;
            String dateDeNaissance = "";

            Participant leParticipant;
            if (this.estUnParticipantCourseRelais(licence)) {
                String nomEquipe = "";
                leParticipant = new ParticipantCourseRelais(idP, nom, prenom, categorieP, sexe, email, ville, certification, tel, nomEquipe, licence);
            } else if (this.estUnParticipantLicenceIndividuel(club)) {
                leParticipant = new ParticipantLicenceCourseIndiv(idP, nom, prenom, categorieP, sexe, email, ville, certification, tel, club, Integer.parseInt(licence), dateDeNaissance);
            } else {
                leParticipant = new ParticipantNonLicenceCourseIndiv(idP, nom, prenom, categorieP, sexe, email, ville, certification, tel, dateDeNaissance);
            }

            int posGeneral = lesClassements.getInt("Positions");
            String posCategorie = lesClassements.getString("Classements_Catégorie");
            String temps = lesClassements.getString("Temps");
            Classement classement = new Classement(idC, posGeneral, posCategorie, 0, temps, leParticipant);
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

    public void genererPdfClassement(String host, String user, String password, String database, String genre, String categorie) throws SQLException, IOException {
        String formattedGenre = genre.toLowerCase().replace(" ", "_");
        String formattedCategorie = categorie.toLowerCase().replace(" ", "_");
        String outputFile = "pdf/Classement_" + formattedGenre + "_" + formattedCategorie + ".pdf";

        File pdfDir = new File("pdf");
        if (!pdfDir.exists()) {
            pdfDir.mkdirs();
        }

        Connection conn = DriverManager.getConnection("jdbc:mariadb://" + host + ":3306/" + database, user, password);
        Statement stmt = conn.createStatement();

        String genreCondition = !"mixte".equalsIgnoreCase(genre) ? "AND P.sexe = '" + (genre.equalsIgnoreCase("homme") ? "H" : "F") + "'" : "";
        String categorieCondition = !"toutes".equalsIgnoreCase(categorie) ? "AND Cat.categorie = '" + categorie + "'" : "";

        String query = "SELECT C.pos_generale AS Positions, C.temps AS Temps, CONCAT(P.nom, ' ', P.prenom) AS Nom_Prénom, " +
                "P.club AS Club_Equipe, D.num_dossard AS Dossard, Cat.categorie AS Catégorie, C.pos_categorie AS Classements_Catégorie, " +
                "P.num_Licence AS Licence " +
                "FROM CLASSEMENT C " +
                "JOIN GENERER G ON C.id_Classement = G.id_Classement " +
                "JOIN PARTICIPANT P ON G.id_Participant = P.id_Participant " +
                "JOIN DOSSARD D ON P.id_Participant = D.id_Participant " +
                "JOIN CATEGORIE Cat ON P.idCategorie = Cat.idCategorie " +
                "WHERE 1=1 " + genreCondition + " " + categorieCondition + " ORDER BY C.pos_generale";

        ResultSet rs = stmt.executeQuery(query);

        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);
        float margin = 40;
        float yStart = page.getMediaBox().getHeight() - margin;
        float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
        float yPosition = yStart;
        float rowHeight = 18;
        float cellMargin = 5f;

        // Titre du classement
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.beginText();
        contentStream.newLineAtOffset(margin, yPosition);
        contentStream.showText("Classement - Catégorie: " + categorie + " | Genre: " + genre);
        contentStream.endText();
        yPosition -= 25;

        // Définition des colonnes avec ajustement des largeurs
        String[] headers = {"Pos", "Temps", "Nom Prénom", "Club", "Dossard", "Catégorie", "Classement Catégorie", "Licence"};
        float[] columnWidths = {30, 50, 100, 90, 40, 60, 90, 60};

        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 8);
        // Dessiner l'en-tête du tableau
        float nextX = margin;
        for (int i = 0; i < headers.length; i++) {
            contentStream.beginText();
            contentStream.newLineAtOffset(nextX + 5, yPosition + 5);
            contentStream.showText(headers[i]);
            contentStream.endText();
            nextX += columnWidths[i];
        }
        // Dessiner les bordures de l'en-tête
        dessinerBordures(contentStream, margin, yPosition, columnWidths, rowHeight);
        yPosition -= rowHeight;

        // Remplissage des données
        contentStream.setFont(PDType1Font.HELVETICA, 6);
        while (rs.next()) {
            if (yPosition < margin + rowHeight) {
                contentStream.close();
                page = new PDPage(PDRectangle.A4);
                document.addPage(page);
                contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);
                contentStream.setFont(PDType1Font.HELVETICA, 6);
                yPosition = yStart - rowHeight;
                // Redessiner l'en-tête sur la nouvelle page
                nextX = margin;
                for (int i = 0; i < headers.length; i++) {
                    contentStream.beginText();
                    contentStream.newLineAtOffset(nextX + 5, yPosition + 5);
                    contentStream.showText(headers[i]);
                    contentStream.endText();
                    nextX += columnWidths[i];
                }
                dessinerBordures(contentStream, margin, yPosition, columnWidths, rowHeight);
                yPosition -= rowHeight;
            }

            String[] row = {
                    rs.getString("Positions"),
                    rs.getString("Temps"),
                    rs.getString("Nom_Prénom"),
                    rs.getString("Club_Equipe"),
                    rs.getString("Dossard"),
                    rs.getString("Catégorie"),
                    rs.getString("Classements_Catégorie"),
                    rs.getString("Licence")
            };

            nextX = margin;
            for (int i = 0; i < row.length; i++) {
                contentStream.beginText();
                contentStream.newLineAtOffset(nextX + 5, yPosition + 5);
                contentStream.showText(row[i] != null ? row[i] : "");
                contentStream.endText();
                nextX += columnWidths[i];
            }
            dessinerBordures(contentStream, margin, yPosition, columnWidths, rowHeight);
            yPosition -= rowHeight;
        }

        contentStream.close();
        document.save(outputFile);
        document.close();

        rs.close();
        stmt.close();
        conn.close();

        new Thread(() -> {
            try {
                afficherPdf(outputFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void dessinerBordures(PDPageContentStream contentStream, float margin, float yPosition, float[] columnWidths, float rowHeight) throws IOException {
        float nextX = margin;

        for (float width : columnWidths) {
            contentStream.moveTo(nextX, yPosition + rowHeight);
            contentStream.lineTo(nextX, yPosition);
            contentStream.stroke();
            nextX += width;
        }

        contentStream.moveTo(nextX, yPosition + rowHeight);
        contentStream.lineTo(nextX, yPosition);
        contentStream.stroke();
        contentStream.moveTo(margin, yPosition);
        contentStream.lineTo(margin + nextX - margin, yPosition);
        contentStream.stroke();
    }

    private void afficherPdf(String filePath) throws IOException {
        if (Desktop.isDesktopSupported()) {
            File pdfFile = new File(filePath);
            if (pdfFile.exists()) {
                Desktop.getDesktop().open(pdfFile);
            }
        }
    }
}