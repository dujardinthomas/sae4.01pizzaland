package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.Client;

public class ClientDAO {

	private DS ds = new DS();
	private Connection con;

/*
 * CREATE
 * READ
 * UPDATE
 * DELETE
 */
	public boolean createClient(Client c) throws SQLException{
		boolean res = false; //on cree un boolean pour pouvoir fermer la connexion
		con = ds.getConnection();
		Statement stmt = con.createStatement();
		String query = "insert into clients values (?, ?, ?)";

		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, c.getIdC());
		ps.setString(2, c.getNomC());
		ps.setString(3, c.getAdresseC());
		if(ps.executeUpdate() != 0){
			res = true;
		}
		try {con.close();} catch(Exception e2) {}
		return res;
	}
	
	public List<Client> getAllClients() throws SQLException{
		con = ds.getConnection();
		List<Client> clients = new ArrayList<Client>();
		Statement stmt = con.createStatement();
		String query = "select * from clients";
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next()){
			clients.add(new Client(rs.getInt("idC"), rs.getString("nomC"), rs.getString("adresseC")));
		}
		return clients;
	}
	
	public Client getClientByIdC(int idC) throws SQLException{
		Client c = null;
		con = ds.getConnection();
		Statement stmt = con.createStatement();
		String query = "select * from clients where idc = " + idC;
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next()){
			c = new Client(rs.getInt("idC"), rs.getString("nomC"), rs.getString("adresseC"));
		}
		return c;
	}
	
	
	public boolean updateClient(int idC, int colonne1, int colonne2 ) throws SQLException{
		boolean res = false; //on cree un boolean pour pouvoir fermer la connexion
		con = ds.getConnection();
		String query = "update clients set ? = ? where idC = " + idC;
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, colonne1);
		ps.setInt(2, colonne2);
		if(ps.executeUpdate() != 0){
			res = true;
		}
		try {con.close();} catch(Exception e2) {}
		return res;
	}

	
	public boolean deleteClient(int idC) throws SQLException{
		boolean res=false;
		con = ds.getConnection();
		String query = "delete from clients where idC = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, idC);
		if(ps.executeUpdate() != 0){
			res = true;
		}
		try {con.close();} catch(Exception e2) {}
		return res;
	}
	

}

