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

public class Pizza_IngredientsDAO {

	private DS ds = new DS();
	private Connection con;

	private IngredientDAO ingrDAO = new IngredientDAO();

/*
 * CREATE
 * READ
 * UPDATE
 * DELETE
 */
	public boolean createIngredientsPizza(int pizza_id, int ingredient_id ) throws SQLException{
		boolean res = false; //on cree un boolean pour pouvoir fermer la connexion
		con = ds.getConnection();
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		Statement stmt = con.createStatement();
		String query = "insert into pizza_ingredients values (?, ?)";

		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, pizza_id);
		ps.setInt(2, ingredient_id);
		if(ps.executeUpdate() != 0){
			res = true;
		}
		try {con.close();} catch(Exception e2) {}
		return res;
	}
	
	public List<Ingredient> getIngredientsPizza(int pizza_id) throws SQLException{
		con = ds.getConnection();
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		Statement stmt = con.createStatement();
		String query = "select * from pizza_ingredients where pizza_id=" + pizza_id;
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next()){
			ingredients.add(ingrDAO.findByIdI(rs.getInt("ingredient_id")));
		}
		return ingredients;

	}
	
	
	public boolean updateIngredientsPizza(int pizza_id, int colonne1, int colonne2 ) throws SQLException{
		boolean res = false; //on cree un boolean pour pouvoir fermer la connexion
		con = ds.getConnection();
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		Statement stmt = con.createStatement();
		String query = "update pizza_ingredients set ? = ? where pizza_id = " + pizza_id;

		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, colonne1);
		ps.setInt(2, colonne2);
		if(ps.executeUpdate() != 0){
			res = true;
		}
		try {con.close();} catch(Exception e2) {}
		return res;
	}

	
	public boolean deleteIngredientsPizza(int pizza_id, int ingredient_id) throws SQLException{
		boolean res=false;
		con = ds.getConnection();
		String query = "delete from pizza_ingredients where pizza_id = ? and ingredient_id = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, pizza_id);
		ps.setInt(2, ingredient_id);
		if(ps.executeUpdate() != 0){
			res = true;
		}
		try {con.close();} catch(Exception e2) {}
		return res;
	}

}

