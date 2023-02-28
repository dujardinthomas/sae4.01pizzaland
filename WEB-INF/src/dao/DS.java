package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DS {
    
//    SUR PC ACER
//    public Connection getConnection(){
//        String url = "jdbc:postgresql://localhost/thoma";
//        String nom = "thoma";
//        String mdp = "thoma";
//        String driver = "org.postgresql.Driver";
//        Connection con = null;
//        try {
//            Class.forName(driver);
//            con = DriverManager.getConnection(url,nom,mdp);
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        return con;
//    }

     //SUR IUT
     public Connection getConnection(){
         String url = "jdbc:postgresql://psqlserv/but2";
         String nom = "thomasdujardin2etu";
         String mdp = "moi";
         String driver = "org.postgresql.Driver";
         Connection con = null;
         try {
             Class.forName(driver);
             con = DriverManager.getConnection(url,nom,mdp);
         } catch (Exception e){
             e.printStackTrace();
         }
         return con;
     }
     
   //SUR IUT SIMON
//     public Connection getConnection(){
//         String url = "jdbc:postgresql://psqlserv/but2";
//         String nom = "simonbarbeauetu";
//         String mdp = "moi";
//         String driver = "org.postgresql.Driver";
//         Connection con = null;
//         try {
//             Class.forName(driver);
//             con = DriverManager.getConnection(url,nom,mdp);
//         } catch (Exception e){
//             e.printStackTrace();
//         }
//         return con;
//     }
}
