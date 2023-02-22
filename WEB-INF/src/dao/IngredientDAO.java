package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.Ingredient;

public class IngredientDAO {


	private DS ds = new DS();
	private Connection con;
	
	//CRUD : CREATE READ UPDATE DELETE

	public boolean createIngredient(Ingredient ingr) throws SQLException{
		boolean res = false; //on cree un boolean pour pouvoir fermer la connexion
		con = ds.getConnection();
	//	Statement stmt = con.createStatement();
		String query = "insert into ingredients values (?,?)";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, ingr.getIdI());
		ps.setString(2, ingr.getNameI());
		if(ps.executeUpdate() != 0){
			res = true;
		}
		try {con.close();} catch(Exception e2) {}
		return res;
	}

	///////////////////////////////////////////////////////////////
	////////////////// READ ///////////////////////////////////////
	///////////////////////////////////////////////////////////////

	public List<Ingredient> findAll() throws SQLException{
		con = ds.getConnection();
		List<Ingredient> ingrAll = new ArrayList<Ingredient>();
		Statement stmt = con.createStatement();
		String query = "select * from ingredients";
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next()){
			int id = rs.getInt("idI");
			String nom = rs.getString("nameI");
			Ingredient ingrTemp= new Ingredient(id, nom);
			ingrAll.add(ingrTemp);
		}
		try {con.close();} catch(Exception e2) {}
		return ingrAll;
	}

	public Ingredient findByIdI(int id) throws SQLException{
		String query = "select * from ingredients where idI="+id;
		return select(query);
	}

	public Ingredient findByNameI(String nom) throws SQLException{
		String query = "select * from ingredients where nameI='" + nom+"'";
		return select(query);
	}

	private Ingredient select(String requete) throws SQLException{
		Ingredient ingr = null;
		con = ds.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(requete);
		if(rs.next()){
			int id = rs.getInt("idI");
			String name = rs.getString("nameI");
			ingr= new Ingredient(id, name);
		}
		try {con.close();} catch(Exception e2) {}
		return ingr;
	}

	///////////////////////////////////////////////////////////////
	////////////////// UPDATE ///////////////////////////////////////
	///////////////////////////////////////////////////////////////

	public boolean updateIngredient(String nomColonne, String newValeur, Ingredient ingr) throws SQLException{
		boolean res=false;
		con = ds.getConnection();
	//	Statement stmt = con.createStatement();
		String query = "update ingredients set ? = ? where ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, nomColonne);
		ps.setString(2, newValeur);
		ps.setInt(3, ingr.getIdI());
		if(ps.executeUpdate() != 0){
			res = true;
		}
		try {con.close();} catch(Exception e2) {}
		return res;
	}


	///////////////////////////////////////////////////////////////
	////////////////// DELETE ///////////////////////////////////////
	///////////////////////////////////////////////////////////////

	public boolean deleteIngredient(Ingredient ingr) throws SQLException{
		boolean res=false;
		con = ds.getConnection();
	//	Statement stmt = con.createStatement();
		String query = "delete from ingredients where idI = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, ingr.getIdI());
		if(ps.executeUpdate() != 0){
			res = true;
		}
		try {con.close();} catch(Exception e2) {}
		return res;
	}
	
	
	public boolean deleteIngredientById(int number) throws SQLException{
		boolean res=false;
		con = ds.getConnection();
	//	Statement stmt = con.createStatement();
		String query = "delete from ingredients where idI = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, number);
		if(ps.executeUpdate() != 0){
			res = true;
		}
		try {con.close();} catch(Exception e2) {}
		return res;
	}


}
