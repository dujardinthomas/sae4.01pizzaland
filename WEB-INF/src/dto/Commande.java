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
	
	private double prixFinalC;

	public Commande(int idCo, int client_id, Date date, List<Pizza> pizzas) {
		this.idCo = idCo;
		this.client_id = client_id;
		this.date = date;
		this.pizzas = pizzas;
		this.prixFinalC = calculerPrixFinal();
	}

	public Commande() {
	}
	
	private double calculerPrixFinal() {
		double prixPizzas = 0;
		for (Pizza pizza : this.pizzas) {
			prixPizzas += pizza.getPrixFinalP();
		}
		return prixPizzas;
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

	@Override
	public String toString() {
		return "Commande [idCo=" + idCo + ", client_id=" + client_id + ", date=" + date + ", pizzas=" + pizzas + "]";
	}
	
	

}
