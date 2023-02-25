package dto;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Commande {
	
	@JsonProperty
	private int idCo;
	
	@JsonProperty
	private int client_id;
	
	@JsonProperty
	private Date date;

	@JsonProperty
	private List<Pizza> pizzas;

	public Commande(int idCo, int client_id, Date date, List<Pizza> pizzas) {
		this.idCo = idCo;
		this.client_id = client_id;
		this.date = date;
		this.pizzas = pizzas;
	}

	public Commande() {
	}

	public int getIdCo() {
		return idCo;
	}

	public void setIdCo(int idCo) {
		this.idCo = idCo;
	}

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

}
