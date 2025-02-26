package com.trisln.aquaneutron.bd;
import java.sql.*;

public class ConnexionMySQL {
	Connection mysql=null;
    boolean connecte=false;
    
	public ConnexionMySQL(String nomServeur, String nomBase, String nomLogin, String motDePasse) {
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			//Class.forName("com.mysql.cj.jdbc.Driver");
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver MySQL non trouvÃ©?");
			mysql=null;
			return;
		}
		try {
			mysql = DriverManager.getConnection(
					"jdbc:mariadb://"+nomServeur+":3306/"+nomBase,nomLogin, motDePasse);
			connecte=true;
			System.out.println("connecté");
		} catch (SQLException e) {
			System.out.println("Echec de connexion à la bd!"); 
			System.out.println(e.getMessage());
			mysql=null;
			return;
		}
	}
    public Connection getConnexion(){
        return this.mysql;
    }

    public boolean getConnecte() {
        return this.connecte;
    }

    public Statement createStatement() throws SQLException {
        return this.mysql.createStatement();
    }

    public PreparedStatement prepareStatement(String requete) throws SQLException {
        return this.mysql.prepareStatement(requete);
    }
}