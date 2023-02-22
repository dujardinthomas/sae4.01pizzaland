package controleurs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.IngredientDAO;
import dto.Ingredient;

@WebServlet("/Ingredients/*")
public class IngredientRestAPI extends HttpServlet {

	private IngredientDAO ingrDAO = new IngredientDAO();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json;charset=UTF-8");
		PrintWriter out = res.getWriter();
		ObjectMapper objectMapper = new ObjectMapper();

		String info = req.getPathInfo();
		String jsonString = null;
		if(info == null) {
			try {
				jsonString = objectMapper.writeValueAsString(ingrDAO.findAll());
			} catch (JsonProcessingException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		else{
			String[] parts = info.split("/");
			String param1 = parts[1];
			try {
				jsonString = objectMapper.writeValueAsString(ingrDAO.findByIdI(Integer.valueOf(param1)));
			}catch (Exception e) {
				res.sendError(404, " cet objet n'existe pas !");
			}
		}
		out.println(jsonString);
		out.close();
	}


	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json;charset=UTF-8");
		PrintWriter out = res.getWriter();

		StringBuilder data = new StringBuilder();
		BufferedReader reader = req.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			data.append(line);
		}

		ObjectMapper mapper = new ObjectMapper();
		Ingredient newIngredient = mapper.readValue(data.toString(), Ingredient.class);
		try {
			ingrDAO.createIngredient(newIngredient);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println(data.toString());
	}




	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
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
		out.println(jsonString);
		out.println("supprimé !");
		out.close();
	}
}