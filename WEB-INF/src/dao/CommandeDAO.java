package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dto.Commande;

public class CommandeDAO {
	
	private DS ds = new DS();
	private Connection con;
	
	private Commande_PizzaDAO commande_pizzDAO = new Commande_PizzaDAO();
	
	//CRUD : CREATE READ UPDATE DELETE

		public boolean createCommande(Commande c) throws SQLException{
			boolean res = false; //on cree un boolean pour pouvoir fermer la connexion
			con = ds.getConnection();
			String query = "insert into commandes values (?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			int idCo = c.getIdCo();
			ps.setInt(1, idCo);
			ps.setInt(2, c.getClient_id());
			ps.setDate(3, Date.valueOf(c.getDateC()));
			if(ps.executeUpdate() != 0){
				res = true;
			}
			System.out.println("commande ajouté !!");
			if(!(c.getPizzas() == null)){
				for(int i=0; i<c.getPizzas().size(); i++){
					res = commande_pizzDAO.createCommandePizza(idCo, c.getPizzas().get(i).getIdP());
					System.out.println("commande pizza ajouté !!");
				}
			}

			System.out.println("commande entierement finit !!! youpi ");
			try {con.close();} catch(Exception e2) {}
			return res;
		}

		
		public List<Commande> getAllCommandes() throws SQLException{
			con = ds.getConnection();
			List<Commande> commandes = new ArrayList<Commande>();
			Statement stmt = con.createStatement();
			String query = "select * from commandes";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				int idC = rs.getInt("idCo");
				commandes.add(new Commande(idC, rs.getInt("client_id"), rs.getString("dateco"), commande_pizzDAO.getAllCommandePizza(idC)));
			}
			return commandes;
		}
		
		public Commande getCommandeByIdCo(int idCo) throws SQLException{
			Commande c = null;
			con = ds.getConnection();
			Statement stmt = con.createStatement();
			String query = "select * from commandes where idco = " + idCo;
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				int idC = rs.getInt("idCo");
				c = new Commande(idC, rs.getInt("client_id"), rs.getString("dateco"), commande_pizzDAO.getAllCommandePizza(idC));
			}
			return c;
		}
		
		
		public boolean updateCommandeByIdCo(int idCo, int colonne1, int colonne2 ) throws SQLException{
			boolean res = false; //on cree un boolean pour pouvoir fermer la connexion
			con = ds.getConnection();
			String query = "update commandes set ? = ? where idCo = " + idCo;
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, colonne1);
			ps.setInt(2, colonne2);
			if(ps.executeUpdate() != 0){
				res = true;
			}
			try {con.close();} catch(Exception e2) {}
			return res;
		}

		
		public boolean deleteCommandeByIdCo(int idCo) throws SQLException{
			boolean res=false;
			con = ds.getConnection();
			String query = "delete from commandes where idCo = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, idCo);
			if(ps.executeUpdate() != 0){
				res = true;
			}
			commande_pizzDAO.deleteCommandePizza(idCo);
			System.out.println("commande pizza supprimé !!");
			System.out.println("commande entierement supprimé !!! youpi ");
			try {con.close();} catch(Exception e2) {}
			return res;
		}
		
}
