package com.trisln.aquaneutron.bd;
import com.trisln.aquaneutron.modele.*;
import com.trisln.aquaneutron.modele.Exceptions.NoSuchUserException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BdTriSLN{

    private static final Map<String, String> monthMap = new HashMap<>();
    static {
        monthMap.put("janvier", "01");
        monthMap.put("février", "02");
        monthMap.put("mars", "03");
        monthMap.put("avril", "04");
        monthMap.put("mai", "05");
        monthMap.put("juin", "06");
        monthMap.put("juillet", "07");
        monthMap.put("août", "08");
        monthMap.put("septembre", "09");
        monthMap.put("octobre", "10");
        monthMap.put("novembre", "11");
        monthMap.put("décembre", "12");
    }

    private ConnexionMySQL connexion;
    public BdTriSLN(ConnexionMySQL connexion){
        this.connexion=connexion;
    }

    public List<Participant> getParticipantsCourseRelais() throws SQLException{
        List<Participant> participantsCourseRelais=new ArrayList<>();
        Statement st=this.connexion.createStatement();
        ResultSet participants=st.executeQuery("select * from PARTICIPANT");
        while(participants.next()){
            try{
                boolean licence=participants.getBoolean(13);
                String nomEquipe=participants.getString(14);

                int idP=participants.getInt(1);
                String nom=participants.getString(2);
                String prenom=participants.getString(3);
                String categorie=participants.getString(4);
                char sexe=participants.getString(5).charAt(0);
                String email=participants.getString(6);
                String ville=participants.getString(7);
                String certification=participants.getString(8);
                int tel=participants.getInt(9);
                Participant participant = new ParticipantCourseRelais(idP, nom, prenom, categorie, sexe, email, ville, certification, tel, nomEquipe, licence);//,chronometre manque un constructeur qui prend en compte chrono TODO
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

    public boolean estUnParticipantCourseRelais(boolean licence){
        return licence;
    }

    public boolean estUnParticipantLicenceIndividuel(String club){
        return !club.equals("");
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

    public void lectureFichier(File fichier) {
        try{
            if(fichier.getName().toLowerCase().endsWith(".csv")){
                traitementCSV(fichier);
            }

            else if(fichier.getName().toLowerCase().endsWith(".xls")){
                traitementXLS(fichier);
            }

            else if(fichier.getName().toLowerCase().endsWith(".xlsx")){
                traitementXLSX( fichier);
            }

            System.out.println("fichier non traiter pour le moment");

        }

        catch(Exception e){
            e.printStackTrace();
        }
    
    }

    public void traitementCSV(File csv) throws IOException {
    try{
        System.out.println("traitement CSV");
        FileReader fr = new FileReader(csv);
        BufferedReader br = new BufferedReader(fr);

        boolean estPremiereLigne = false;
        for (String line = br.readLine().toLowerCase(); line != null; line = br.readLine()) {
            System.out.println(line +"\n");
            if (!estPremiereLigne) {
                estPremiereLigne = true;
                }
            else{
                List<String> partiedecoupe = new ArrayList<>(Arrays.asList(line.split(",")));
                for ( int i = 0; i < partiedecoupe.size();i++  ){
                    if ("".equals(partiedecoupe.get(i))) {
                        partiedecoupe.set(i ,"null");
                    }
                }

                while (partiedecoupe.size() < 13 && partiedecoupe.size() != 13 ) //TODO le 13 a convertir en const
                 {
                    partiedecoupe.add("null");
                }
                System.out.println(partiedecoupe +"\n");

                PreparedStatement addParticipant = this.connexion.prepareStatement("insert into PARTICIPANT values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                int idParticipant = 0; 
                String nom = "";
                String prenom = "";
                int idCategorie= 0;
                String sexe = "";
                String email =      partiedecoupe.get(5);
                String ville =      partiedecoupe.get(6);
                boolean certification = false;
                String numTel = "";
                String club = partiedecoupe.get(9);
                boolean licence = false;
                int numLicence = 0;
                String dateNaissance = "";
                String nomEquipe =  partiedecoupe.get(12);
                
                if ("null".equals(partiedecoupe.get(0))) {
                    System.err.println("un id des participant nest pas un entier");
                    return; //TODO faire des alertes
                } else{
                    idParticipant = Integer.parseInt(partiedecoupe.get(0));
                }

                if ("null".equals(partiedecoupe.get(1))) {
                    System.err.println("nom de la personne non trouver");
                    return ; //TODO faire des alertes
                } else{
                    nom = partiedecoupe.get(1);
                }                
                
                if ("null".equals(partiedecoupe.get(2))) {
                    System.err.println("prenom de la personne non trouver");
                    return ; //TODO faire des alertes
                } else{
                    prenom = partiedecoupe.get(2);
                }

                if ("null".equals(partiedecoupe.get(3))) {
                    System.err.println("id Categorie non trouver");
                    return ; //TODO faire des alertes
                }else{
                    idCategorie = Integer.parseInt(partiedecoupe.get(3));
                }

                if ("null".equals(partiedecoupe.get(4))) {
                    System.err.println("sexe non trouver");
                    return ; //TODO faire des alertes
                }else{
                    sexe = partiedecoupe.get(4);
                }
                
                if ("null".equals(partiedecoupe.get(7))) {
                    certification = false;
                }else{
                    certification = true;
                }

                if ("null".equals(partiedecoupe.get(8))) {
                    System.err.println("telephone non trouver");
                    return ; //TODO faire des alertes
                }else{
                    numTel = partiedecoupe.get(8);
                }

                if ("null".equals(partiedecoupe.get(10))) {
                    System.err.println("id numLicence non trouver");
                    licence = false;
                }else{
                    numLicence = Integer.parseInt(partiedecoupe.get(10));
                    licence = true;
                }

                if ("null".equals(partiedecoupe.get(11))) {
                    System.err.println("il manque une date de naissance dans le fichier");
                    return;
                }
                else{
                    dateNaissance = partiedecoupe.get(11).replace("/","-").toLowerCase();
                    String[] dateParts = dateNaissance.split("-");
                    String jour = dateParts[1];
                    String mois = monthMap.get(dateParts[0].toLowerCase());;
                    if (jour.length() == 1) {
                        jour = "0" + jour;
                    }
                    if (mois.length() == 1) {
                        mois = "0" + mois;
                    }
                    // Retourner la date au format SQL 'YYYY-MM-DD'
                    dateNaissance =  dateParts[2] + "-" + mois + "-" + jour;
                }
                
                System.out.println("la lecture de la ligne c'est bien passer");

                addParticipant.setInt(1, idParticipant);
                addParticipant.setString(2, nom);
                addParticipant.setString(3, prenom);
                addParticipant.setInt(4, idCategorie);
                addParticipant.setString(5, sexe);
                addParticipant.setString(6, email);
                addParticipant.setString(7, ville);
                addParticipant.setBoolean(8, certification);
                addParticipant.setString(9, numTel);
                addParticipant.setString(10, club);
                addParticipant.setInt(11, numLicence);
                addParticipant.setString(12, dateNaissance);
                addParticipant.setString(13, nomEquipe);
                addParticipant.setBoolean(14, licence);
                addParticipant.executeUpdate();
                addParticipant.close();
                System.out.println("import ok");
                }           
        }
            br.close();
            fr.close();
        } catch(SQLException e){
            e.printStackTrace();

        }   
    }


    public void traitementXLS(File csv) {
        try{
            System.out.println("traitement XLS");
            FileReader fr = new FileReader(csv);
            BufferedReader br = new BufferedReader(fr);
            int i =0;

            for (String line = br.readLine(); line != null; line = br.readLine()) {
                System.out.println("wait");
                
            }
            br.close();
            fr.close();
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public void traitementXLSX(File csv) {
        try{

            System.out.println("traitement XLSX");
            FileReader fr = new FileReader(csv);
            BufferedReader br = new BufferedReader(fr);
            
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                System.out.println("wait");
            }

            br.close();
            fr.close();

        }catch(IOException e){
            e.printStackTrace();

        }

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

    public String getEmail(String identifiant) throws SQLException, NoSuchUserException{
        Statement s = this.connexion.createStatement();
        ResultSet rs = s.executeQuery("select email from UTILISATEUR where identifiant = '"+identifiant+"'");
        if (rs.next()){
            return rs.getString(1);
        } else {
            throw new NoSuchUserException();
        }
    }

    public List<String> getEmailAdresses() throws SQLException{
        List<String> emails = new ArrayList<>();
        Statement s = this.connexion.createStatement();
        ResultSet rs = s.executeQuery("select email from UTILISATEUR");
        while (rs.next()){
            String email = rs.getString(1);
            emails.add(email);
        }
        return emails;
    }

    public String getRoleUtilisateur(String identifiant) throws SQLException, NoSuchUserException{
        Statement s = this.connexion.createStatement();
        ResultSet rs = s.executeQuery("select * from UTILISATEUR where identifiant='"+identifiant+"'");
        if(rs.next()){
            return rs.getString(2);
        } else {
            throw new NoSuchUserException();
        }
    }

    public void changePassword(String identifiant, String newPassword) throws SQLException{
        PreparedStatement ps = this.connexion.prepareStatement("update UTILISATEUR set mot_de_passe = ? where identifiant= ?");
        ps.setString(1, newPassword);
        ps.setString(2, identifiant);
        ps.executeUpdate();
    }

    public void enregistrerToken(String identifiant, String token) throws SQLException{
        PreparedStatement ps = this.connexion.prepareStatement("update UTILISATEUR set token_reinit = ? where identifiant = ?");
        ps.setString(1, token);
        ps.setString(2, identifiant);
        ps.executeUpdate();
    }

    public void ajouterBenevole(String identifiant, String mdp, String email) throws SQLException{
        PreparedStatement ps = this.connexion.prepareStatement("insert into UTILISATEUR values (?,?,?,?)");
        ps.setString(1, identifiant);
        ps.setString(2, "benevol");
        ps.setString(3, mdp);
        ps.setString(4, email);
        ps.executeUpdate();
    }
}