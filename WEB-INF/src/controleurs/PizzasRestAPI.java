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
import dao.PizzaDAO;
import dao.Pizza_IngredientsDAO;
import dao.UsersDAO;
import dto.Ingredient;
import dto.Pizza;

@WebServlet("/pizzas/*")
public class PizzasRestAPI extends HttpServlet {

	private PizzaDAO pizzDAO = new PizzaDAO();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json;charset=UTF-8");
		PrintWriter out = res.getWriter();
		ObjectMapper objectMapper = new ObjectMapper();

		String info = req.getPathInfo();
		String jsonString = "hellooo";
		if(info == null || info.equals("/")) {
			try {
				jsonString = objectMapper.writeValueAsString(pizzDAO.getAllPizzas());
			} catch (JsonProcessingException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			String[] parts = info.split("/");
			if(parts.length == 2) {
				try {
					jsonString = objectMapper.writeValueAsString(pizzDAO.getPizzaByIdP(Integer.valueOf(parts[1])));
				}catch (Exception e) {
					res.sendError(404, " cet objet n'existe pas !");
				}
			}
			else if(parts.length == 3) {
				if(parts[2].equals("prixfinal")) {
					try {
						jsonString = objectMapper.writeValueAsString(pizzDAO.getPizzaByIdP(Integer.valueOf(parts[1])).getPrixFinalP());
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
			Pizza newPizza = mapper.readValue(data.toString(), Pizza.class);
			try {
				result = pizzDAO.createPizza(newPizza);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else{
			String[] parts = info.split("/");
			//if(parts.length == 2) {
				try {
					ObjectMapper objectMapper = new ObjectMapper();
					Ingredient ingredient = objectMapper.readValue(data.toString(), Ingredient.class);
					System.out.println("on ajoute " + ingredient + " a la pizza " + parts[1]);
					Pizza_IngredientsDAO pizzIngrDAO = new Pizza_IngredientsDAO();
					result = pizzIngrDAO.createPizzaIngredient(Integer.parseInt(parts[1]), ingredient.getIdI());
				}catch (Exception e) {
					res.sendError(404, " cet objet n'existe pas !");
				}
		//	}

		}
		if(!result) {
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
		boolean result = false;
		if(info == null || info.equals("/")) {
			res.sendError(404, "indiquer un numero pour supprimer");
		}
		else{
			String[] parts = info.split("/");
			if(parts.length == 2) {
				try {
					jsonString = objectMapper.writeValueAsString(pizzDAO.deletePizzaByIdP(Integer.valueOf(parts[1])));
				}catch (Exception e) {
					res.sendError(404, " cet objet n'existe pas !");
				}
			}

			else if(parts.length == 3) {
				StringBuilder data = new StringBuilder();
				BufferedReader reader = req.getReader();
				String line;
				while ((line = reader.readLine()) != null) {
					data.append(line);
				}
				try {
					//ObjectMapper mapper = new ObjectMapper();
					//Ingredient ingredient = mapper.readValue(data.toString(), Ingredient.class);

					Pizza_IngredientsDAO pizzIngrDAO = new Pizza_IngredientsDAO();

					result = pizzIngrDAO.deletePizzaIngredient(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
					System.out.println("on supprime ingre " + result);
				}catch (Exception e) {
					res.sendError(404, " cet objet n'existe pas !");
				}
			}
			else {
				jsonString = null;
			}
		}
		if((jsonString == null || jsonString.equals("false")) && !result) {
			res.sendError(404, " cet objet ne peux pas être supprimé !");
		}
		else {
			out.println(jsonString);
			out.println("supprimé !");
		}
		out.close();
	}
}