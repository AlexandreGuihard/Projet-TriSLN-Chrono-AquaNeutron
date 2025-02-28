package com.trisln.aquaneutron.bd;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class CryptoUtils {
    
    // Génère une clé AES de 128 bits
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // Taille de la clé en bits
        return keyGenerator.generateKey();
    }

    // Chiffre les données en utilisant AES
    public static String encrypt(String data, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData); // Encodage en base64 pour une représentation textuelle
    }

    // Déchiffre les données en utilisant AES
    public static String decrypt(String encryptedData, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedData = cipher.doFinal(decodedData);
        return new String(decryptedData);
    }
}

/* 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public static void main(String[] args) {
    try {
        // Générer la clé AES
        SecretKey secretKey = CryptoUtils.generateKey();

        // Chiffrer les données
        String dataToEncrypt = "Données sensibles à crypter";
        String encryptedData = CryptoUtils.encrypt(dataToEncrypt, secretKey);

        // Connexion à MySQL
        String url = "jdbc:mysql://localhost:3306/ma_base_de_donnees";
        String user = "root";
        String password = "motdepasse";
        Connection conn = DriverManager.getConnection(url, user, password);

        // Préparer la requête d'insertion
        String query = "INSERT INTO ma_table (data) VALUES (?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, encryptedData);  // Insérer les données chiffrées
        stmt.executeUpdate();

        System.out.println("Données insérées dans la base de données.");

        stmt.close();
        conn.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySQLDecryptionExample {

    public static void main(String[] args) {
        try {
            // Générer la même clé AES que celle utilisée pour le chiffrement
            SecretKey secretKey = CryptoUtils.generateKey();

            // Connexion à MySQL
            String url = "jdbc:mysql://localhost:3306/ma_base_de_donnees";
            String user = "root";
            String password = "motdepasse";
            Connection conn = DriverManager.getConnection(url, user, password);

            // Préparer la requête de sélection
            String query = "SELECT data FROM ma_table WHERE id = 1"; // Supposons que tu veuilles récupérer la donnée avec id = 1
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String encryptedData = rs.getString("data");

                // Déchiffrer les données
                String decryptedData = CryptoUtils.decrypt(encryptedData, secretKey);
                System.out.println("Données déchiffrées : " + decryptedData);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


/*Nous allons utiliser l'algorithme de chiffrement AES (Advanced Encryption Standard) avec la bibliothèque javax.crypto.
 Voici un exemple complet de chiffrement/déchiffrement des données avant d'interagir avec la base de données MySQL. */