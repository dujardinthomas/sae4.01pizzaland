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
import dao.PizzaDAO;
import dto.Ingredient;
import dto.Pizza;

@WebServlet("/Pizzas/*")
public class PizzasRestAPI extends HttpServlet {

	private IngredientDAO ingrDAO = new IngredientDAO();
	private PizzaDAO pizzDAO = new PizzaDAO();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json;charset=UTF-8");
		PrintWriter out = res.getWriter();
		ObjectMapper objectMapper = new ObjectMapper();

		String info = req.getPathInfo();
		String jsonString = "hellooo";
		if(info == null) {

			try {
				jsonString = objectMapper.writeValueAsString(pizzDAO.findAll());
			} catch (JsonProcessingException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			String[] parts = info.split("/");
			String id = parts[1];
//			String ingredient = parts[2];
			try {
				jsonString = objectMapper.writeValueAsString(pizzDAO.findByIdP(Integer.valueOf(id)));
			//	if(!ingredient.isEmpty()) { //on ajoute l'ingredient a la table des ingredients puis dans la pizza
				//	ingrDAO.createIngredient(null);
			//	}
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
		Pizza newPizza = mapper.readValue(data.toString(), Pizza.class);
		try {
			pizzDAO.createPizza(newPizza);
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
				jsonString = objectMapper.writeValueAsString(pizzDAO.deletePizzaByIdP(Integer.valueOf(param1)));
			}catch (Exception e) {
				res.sendError(404, " cet objet n'existe pas !");
			}
		}
		out.println(jsonString);
		out.println("supprimé !");
		out.close();
	}
}