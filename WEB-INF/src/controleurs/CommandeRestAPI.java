package controleurs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.CommandeDAO;
import dao.UsersDAO;
import dto.Commande;

@WebServlet("/commandes/*")
public class CommandeRestAPI extends HttpServlet {

	private CommandeDAO commandeDAO = new CommandeDAO();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json;charset=UTF-8");
		PrintWriter out = res.getWriter();
		ObjectMapper objectMapper = new ObjectMapper();

		String info = req.getPathInfo();
		String jsonString = null;
		if(info == null || info.equals("/")) {
			try {
				jsonString = objectMapper.writeValueAsString(commandeDAO.getAllCommandes());
			} catch (JsonProcessingException | SQLException e) {
				e.printStackTrace();
			}	
		}
		else{
			String[] parts = info.split("/");
			if(parts.length == 2) {
				try {
					jsonString = objectMapper.writeValueAsString(commandeDAO.getCommandeByIdCo(Integer.valueOf(parts[1])));
				}catch (Exception e) {
					res.sendError(404, " cet objet n'existe pas !");
				}
			}
			else if(parts.length == 3) {
				if(parts[2].equals("prixfinal")) {
					try {
						jsonString = objectMapper.writeValueAsString(commandeDAO.getCommandeByIdCo(Integer.valueOf(parts[1])).getPrixFinalC());
					}catch (Exception e) {
						res.sendError(404, " cet objet n'existe pas !");
					}
				}
				else {
					jsonString = null;	
				}
			}
			else {
				jsonString = null;	
			}
		}
		out.println(jsonString);
		out.close();
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String authorization = req.getHeader("Authorization");
		if (authorization == null || !authorization.startsWith("Bearer ") || !UsersDAO.verifierUtilisateur(authorization)){
			res.sendError(403);
			return;
		}
		res.setContentType("application/json;charset=UTF-8");
		PrintWriter out = res.getWriter();

		StringBuilder data = new StringBuilder();
		BufferedReader reader = req.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			data.append(line);
		}

		ObjectMapper mapper = new ObjectMapper();
		Commande newCommande = mapper.readValue(data.toString(), Commande.class);
		try {
			commandeDAO.createCommande(newCommande);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println(data.toString());
	}




	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String authorization = req.getHeader("Authorization");
		if (authorization == null || !authorization.startsWith("Bearer ") || !UsersDAO.verifierUtilisateur(authorization)){
			res.sendError(403);
			return;
		}
		res.setContentType("application/json;charset=UTF-8");
		PrintWriter out = res.getWriter();
		ObjectMapper objectMapper = new ObjectMapper();

		String info = req.getPathInfo();
		String jsonString = null;
		if(info == null) {
			res.sendError(404, "indiquer un numero pour supprimer");
		}
		else{
			String[] parts = info.split("/");
			String param1 = parts[1];
			try {
				System.out.println("heyy");

				jsonString = objectMapper.writeValueAsString(commandeDAO.deleteCommandeByIdCo(Integer.valueOf(param1)));
				System.out.println("2heyy");
			}catch (Exception e) {
				res.sendError(404, " cet objet n'existe pas !");
			}
		}
		if(jsonString.equals("false")) {
			res.sendError(404, " cet objet ne peux pas être supprimé !");
		}
		else {
			out.println(jsonString);
			out.println("supprimé !");
		}
		out.close();
	}
}