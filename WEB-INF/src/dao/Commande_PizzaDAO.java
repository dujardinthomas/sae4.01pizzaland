package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.Ingredient;
import dto.Pizza;

public class Commande_PizzaDAO {

	private DS ds = new DS();
	private Connection con;

	private PizzaDAO pizzDAO = new PizzaDAO();

/*
 * CREATE
 * READ
 * UPDATE
 * DELETE
 */
	public boolean createCommandePizza(int commande_id, int pizza_id ) throws SQLException{
		boolean res = false; //on cree un boolean pour pouvoir fermer la connexion
		con = ds.getConnection();
		String query = "insert into commande_pizza values (?, ?)";

		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, commande_id);
		ps.setInt(2, pizza_id);
		if(ps.executeUpdate() != 0){
			res = true;
		}
		try {con.close();} catch(Exception e2) {}
		return res;
	}
	
	public List<Pizza> getCommandePizza(int commande_id) throws SQLException{
		con = ds.getConnection();
		List<Pizza> pizzas = new ArrayList<Pizza>();
		Statement stmt = con.createStatement();
		String query = "select * from commande_pizza where commande_id=" + commande_id;
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next()){
			pizzas.add(pizzDAO.findByIdP(rs.getInt("pizza_id")));
		}
		return pizzas;

	}
	
	
	public boolean updateCommandePizza(int commande_id, int colonne1, int colonne2 ) throws SQLException{
		boolean res = false; //on cree un boolean pour pouvoir fermer la connexion
		con = ds.getConnection();
		String query = "update commande_pizza set ? = ? where commande_id = " + commande_id;

		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, colonne1);
		ps.setInt(2, colonne2);
		if(ps.executeUpdate() != 0){
			res = true;
		}
		try {con.close();} catch(Exception e2) {}
		return res;
	}

	
	public boolean deleteCommandePizza(int commande_id) throws SQLException{
		boolean res=false;
		con = ds.getConnection();
		String query = "delete from commande_pizza where commande_id = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, commande_id);
		if(ps.executeUpdate() != 0){
			res = true;
		}
		try {con.close();} catch(Exception e2) {}
		return res;
	}
	

}

