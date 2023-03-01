//CLASSE DS A METTRE DANS WEB-INF/src/dao
//PUIS A DECOMMENTER SA METHODE DE CONNEXION
//CETTE CLASSE NE SERA PAS IMPORTER DANS LE GIT (.GITIGNORE)
package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DS {

//DECOMMENTER LA METHODE ET RESPECTER L'INDENTATION	
	
//	THOMAS PC
//	public Connection getConnection(){
//		String url = "jdbc:postgresql://localhost/thoma";
//		String nom = "thoma";
//		String mdp = "thoma";
//		String driver = "org.postgresql.Driver";
//		Connection con = null;
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url,nom,mdp);
//		} catch (Exception e){
//			e.printStackTrace();
//		}
//		return con;
//	}

	//SUR IUT THOMAS
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

	//SIMON PC
	//     public Connection getConnection(){
	//         String url = "jdbc:postgresql://localhost/postgres";
	//         String nom = "postgres";
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

	//SUR IUT SIMON
//	public Connection getConnection(){
//		String url = "jdbc:postgresql://psqlserv/but2";
//		String nom = "simonbarbeauetu";
//		String mdp = "moi";
//		String driver = "org.postgresql.Driver";
//		Connection con = null;
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url,nom,mdp);
//		} catch (Exception e){
//			e.printStackTrace();
//		}
//		return con;
//	}
}
