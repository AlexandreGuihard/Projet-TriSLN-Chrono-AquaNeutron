package com.trisln.aquaneutron.bd;
import java.sql.*;

/**
 * Classe ConnexionMySQL permettant de se connecter à une base de données MySQL
 */
public class ConnexionMySQL {
	Connection mysql=null;
    boolean connecte=false;
    
	/**
	 * Constructeur de la classe ConnexionMySQL
	 * @param nomServeur le nom du serveur
	 * @param nomBase le nom de la base de données
	 * @param nomLogin le nom de l'utilisateur
	 * @param motDePasse le mot de passe de l'utilisateur
	 * @throws SQLException
	 */
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

	/**
	 * Getter de la connexion
	 * @return la connexion
	 */
    public Connection getConnexion(){
        return this.mysql;
    }

	/**
	 * Getter de l'état de la connexion
	 * @return l'état de la connexion
	 */
    public boolean getConnecte() {
        return this.connecte;
    }

	/**
	 * Crée un statement
	 * @return le statement
	 * @throws SQLException si une erreur survient
	 */
    public Statement createStatement() throws SQLException {
        return this.mysql.createStatement();
    }

	/**
	 * Prépare une requête
	 * @param requete la requête à préparer
	 * @return la requête préparée
	 * @throws SQLException si une erreur survient
	 */
    public PreparedStatement prepareStatement(String requete) throws SQLException {
        return this.mysql.prepareStatement(requete);
    }
}