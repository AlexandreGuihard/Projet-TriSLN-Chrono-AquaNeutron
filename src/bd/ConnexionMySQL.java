package src.bd;
import java.sql.*;

public class ConnexionMySQL {
	Connection mysql=null;
    boolean connecte=false;
    
	/**
	 * Constructeur de la classe. Tente une connexion au serveur
	 * @param nomServeur le nom du serveur mariadb
	 * @param nomBase le nom de la base utilisée
	 * @param nomLogin le login utilisé
	 * @param motDePasse le mot de passe du login
	 */
	public ConnexionMySQL(String nomServeur, String nomBase, String nomLogin, String motDePasse) {
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver MySQL non trouve\b ?");
			mysql=null;
			return;
		}
		try {
			mysql = DriverManager.getConnection(
					"jdbc:mysql://"+nomServeur+":3306/"+nomBase,nomLogin, motDePasse);
			connecte=true;
		} catch (SQLException e) {
			System.out.println("Echec de connexion!"); 
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
	 * @return true si on est connecté à la bd sinon false
	 */
    public boolean getConnecte(){
        return this.connecte;
    }

	/**
	 * Création du statement utilisé pour une requête sql
	 * @return le statement
	 * @throws SQLException
	 */
	public Statement createStatement() throws SQLException {
		return this.mysql.createStatement();
	}

	/**
	 * Création du prepared statement utilisé pour une requête sql
	 * @param requete la requête sql
	 * @return le prepared statement
	 * @throws SQLException
	 */
	public PreparedStatement prepareStatement(String requete) throws SQLException{
		return this.mysql.prepareStatement(requete);
	}
	
	/**
	 * @return l'état de la connexion au serveur sql
	 */
	@Override
	public String toString(){
		return connecte?"Connecté à la bd":"Non connecté à la bd";
	}
}