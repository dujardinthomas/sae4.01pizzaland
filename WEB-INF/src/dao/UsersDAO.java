package dao;

import connexion.JwtManager;
import io.jsonwebtoken.Claims;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersDAO {
	
	private static DS ds = new DS();
	private static Connection con;


    // Méthode pour vérifier si l'utilisateur existe dans la base de données
    public static boolean verifierUtilisateur(String token) {
        try {
            String login = JwtManager.getlogin(token);
            con = ds.getConnection();
    		Statement stmt = con.createStatement();
            String query = "select pwd from users where login='"+login+"'";
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()){
                Claims test = JwtManager.decodeJWT(token, rs.getString("pwd"));
            }
            con.close();
            System.out.println("All is ok ! Authentification");
            return true;
        } catch (Exception e) {
            System.out.println("ERREUR 3\n" + e.getMessage());
            return false;
        }
    }

    public static boolean isPresent(String login, String password) {
        try {
            con = ds.getConnection();
    		Statement stmt = con.createStatement();
            String query = "select * from users where login='"+login+"' AND pwd='"+password+"'";
            ResultSet rs = stmt.executeQuery(query);
            con.close();
            System.out.println("All is ok !");
            System.out.println(query);
            return rs.next();
        } catch (SQLException e) {
            System.out.println("ERREUR 3\n" + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
