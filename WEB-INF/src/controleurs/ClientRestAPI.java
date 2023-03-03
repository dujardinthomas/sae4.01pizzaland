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

import dao.ClientDAO;
import dao.UsersDAO;
import dto.Client;

@WebServlet("/clients/*")
public class ClientRestAPI extends HttpServlet {

	private ClientDAO clienDAO = new ClientDAO();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json;charset=UTF-8");
		PrintWriter out = res.getWriter();
		ObjectMapper objectMapper = new ObjectMapper();

		String info = req.getPathInfo();
		String jsonString = null;
		if(info == null || info.equals("/")) {
			try {
				jsonString = objectMapper.writeValueAsString(clienDAO.getAllClients());
			} catch (JsonProcessingException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		else{
			String[] parts = info.split("/");
			String param1 = parts[1];
			try {
				jsonString = objectMapper.writeValueAsString(clienDAO.getClientByIdC(Integer.valueOf(param1)));
			}catch (Exception e) {
				res.sendError(404, " cet objet n'existe pas !");
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
		Client newClient = mapper.readValue(data.toString(), Client.class);
		try {
			clienDAO.createClient(newClient);
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

				jsonString = objectMapper.writeValueAsString(clienDAO.deleteClient(Integer.valueOf(param1)));
				System.out.println("2heyy");
			}catch (Exception e) {
				res.sendError(404, " cet objet n'existe pas !");
			}
		}
		out.println(jsonString);
		out.println("supprimé !");
		out.close();
	}
}