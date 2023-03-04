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

import dao.IngredientDAO;
import dao.UsersDAO;
import dto.Ingredient;

@WebServlet("/ingredients/*")
public class IngredientRestAPI extends HttpServlet {

	private IngredientDAO ingrDAO = new IngredientDAO();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json;charset=UTF-8");
		PrintWriter out = res.getWriter();
		ObjectMapper objectMapper = new ObjectMapper();

		String info = req.getPathInfo();
		String jsonString = null;
		if(info == null || info.equals("/")) {
			try {
				jsonString = objectMapper.writeValueAsString(ingrDAO.getAllIngredients());
			} catch (JsonProcessingException | SQLException e) {
				e.printStackTrace();
			}	
		}
		else{
			String[] parts = info.split("/");
			if(parts.length == 2) {
				try {
					jsonString = objectMapper.writeValueAsString(ingrDAO.getIngredientByIdI(Integer.valueOf(parts[1])));
				}catch (Exception e) {
					res.sendError(404, " cet objet n'existe pas !");
				}
			}
			else if(parts.length == 3) {
				if(parts[2].equals("name")) {
					try {
						jsonString = objectMapper.writeValueAsString(ingrDAO.getIngredientByIdI(Integer.valueOf(parts[1])).getNameI());
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

		String info = req.getPathInfo();
		boolean result = false;
		if(info == null || info.equals("/")) {
			ObjectMapper mapper = new ObjectMapper();
			Ingredient newIngredient = mapper.readValue(data.toString(), Ingredient.class);
			try {
				 result = ingrDAO.createIngredient(newIngredient);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(result == false) {
			res.sendError(404, " cet objet ne peux pas être crée !");
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
				jsonString = objectMapper.writeValueAsString(ingrDAO.deleteIngredientById(Integer.valueOf(param1)));
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