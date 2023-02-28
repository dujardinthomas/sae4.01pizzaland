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

public class PizzaDAO {

	private DS ds = new DS();
	private Pizza_IngredientsDAO pizza_ingrDAO = new Pizza_IngredientsDAO();
	private Connection con;

	//CRUD : CREATE READ UPDATE DELETE

	public boolean createPizza(Pizza p) throws SQLException{
		boolean res = false; //on cree un boolean pour pouvoir fermer la connexion
		con = ds.getConnection();
		//	Statement stmt = con.createStatement();
		String query = "insert into pizzas values (?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, p.getIdP());
		ps.setString(2, p.getNomP());
		ps.setString(3, p.getPate());
		ps.setDouble(4, p.getPrixBaseP());
		if(ps.executeUpdate() != 0){
			res = true;
		}
		try {con.close();} catch(Exception e2) {}
		return res;
	}

	///////////////////////////////////////////////////////////////
	////////////////// READ ///////////////////////////////////////
	///////////////////////////////////////////////////////////////

	public List<Pizza> findAll() throws SQLException{
		con = ds.getConnection();
		List<Pizza> pizzaAll = new ArrayList<Pizza>();
		Statement stmt = con.createStatement();
		String query = "select * from pizzas";
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next()){
			int idP = rs.getInt("idP");
			String nomP = rs.getString("nomP");
			String pate = rs.getString("pate");
			double prixBaseP = rs.getDouble("prixBaseP");
			pizzaAll.add(new Pizza(idP, nomP, pate, prixBaseP, pizza_ingrDAO.getIngredientsPizza(idP)));
		}
		try {con.close();} catch(Exception e2) {}
		System.out.println(pizzaAll);
		return pizzaAll;
	}

	private Pizza select(String requete) throws SQLException{
		Pizza pizz = null;
		con = ds.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(requete);
		if(rs.next()){
			int idP = rs.getInt("idP");
			String nomP = rs.getString("nomP");
			String pate = rs.getString("pate");
			double prixBaseP = rs.getDouble("prixBaseP");
			pizz = new Pizza(idP, nomP, pate, prixBaseP, pizza_ingrDAO.getIngredientsPizza(idP));
		}
		try {con.close();} catch(Exception e2) {}
		return pizz;
	}
	
	public Pizza findByIdP(int idP) throws SQLException{
		String query = "select * from pizzas where idP="+idP;
		return select(query);
	}

	public Pizza findByNomP(String nomP) throws SQLException{
		String query = "select * from pizzas where nomP='" + nomP+"'";
		return select(query);
	}

	

	///////////////////////////////////////////////////////////////
	////////////////// UPDATE ///////////////////////////////////////
	///////////////////////////////////////////////////////////////

	public boolean updatePizza(String nomColonne, String newValeur, Pizza pizz) throws SQLException{
		boolean res=false;
		con = ds.getConnection();
		//	Statement stmt = con.createStatement();
		String query = "update pizzas set ? = ? where ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, nomColonne);
		ps.setString(2, newValeur);
		ps.setInt(3, pizz.getIdP());
		if(ps.executeUpdate() != 0){
			res = true;
		}
		try {con.close();} catch(Exception e2) {}
		return res;
	}


	///////////////////////////////////////////////////////////////
	////////////////// DELETE ///////////////////////////////////////
	///////////////////////////////////////////////////////////////

	public boolean deletePizza(Pizza pizz) throws SQLException{
		boolean res=false;
		con = ds.getConnection();
		//	Statement stmt = con.createStatement();
		String query = "delete from pizzas where idP = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, pizz.getIdP());
		if(ps.executeUpdate() != 0){
			res = true;
		}
		try {con.close();} catch(Exception e2) {}
		return res;
	}

	public boolean deletePizzaByIdP(int number) throws SQLException {
		boolean res=false;
		con = ds.getConnection();
		//	Statement stmt = con.createStatement();
		String query = "delete from pizzas where idP = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, number);
		if(ps.executeUpdate() != 0){
			res = true;
		}
		try {con.close();} catch(Exception e2) {}
		return res;
	}

}

