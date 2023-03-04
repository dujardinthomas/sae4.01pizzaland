package dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pizza{

	@JsonProperty
	private int idP;

	@JsonProperty
	private String nomP;

	@JsonProperty
	private String pate;

	@JsonProperty
	private double prixBaseP;

	private double prixFinalP;

	@JsonProperty
	private List<Ingredient> ingredients;

	public Pizza(int idP, String nomP, String pate, double prixBaseP, List<Ingredient> ingredients) {
		this.idP = idP;
		this.nomP = nomP;
		this.pate = pate;
		this.prixBaseP = prixBaseP;
		this.ingredients = ingredients;
		this.prixFinalP = calculerPrixFinal();
		
	}

	private double calculerPrixFinal() {
		double prixIngredient = 0;
		for (Ingredient ingr : this.ingredients) {
			prixIngredient += ingr.getPrixI();
		}
		return prixIngredient + this.prixBaseP;
	}

	public Pizza() {
	}

	public int getIdP() {
		return idP;
	}

	public void setIdP(int idP) {
		this.idP = idP;
	}

	public String getNomP() {
		return nomP;
	}

	public static void main(String[] args) {
		System.out.println("vhvhjvhjf rtfdjxnb");
	}

	public void setNomP(String nomP) {
		this.nomP = nomP;
	}

	public String getPate() {
		return pate;
	}

	public void setPate(String pate) {
		this.pate = pate;
	}

	public double getPrixBaseP() {
		return prixBaseP;
	}

	public void setPrixBaseP(double prixBaseP) {
		this.prixBaseP = prixBaseP;
	}

	public double getPrixFinalP() {
		return prixFinalP;
	}

	public void setPrixFinalP(double prixFinalP) {
		this.prixFinalP = prixFinalP;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	@Override
	public String toString() {
		return "Pizza [idP=" + idP + ", nomP=" + nomP + ", pate=" + pate + ", prixBaseP=" + prixBaseP + ", prixFinalP="
				+ prixFinalP + ", ingredients=" + ingredients + "]";
	}
	
	
}