package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ingredient {
	
	@JsonProperty
	private int idI;
	@JsonProperty
	private String nameI;
	@JsonProperty
	private double prixI;
	
	
	public Ingredient(int idI, String nameI, double prixI) {
		this.idI = idI;
		this.nameI = nameI;
		this.prixI = prixI;
	}


	public Ingredient() {
	}


	public int getIdI() {
		return idI;
	}


	public void setIdI(int idI) {
		this.idI = idI;
	}


	public String getNameI() {
		return nameI;
	}


	public void setNameI(String nameI) {
		this.nameI = nameI;
	}


	public double getPrixI() {
		return prixI;
	}


	public void setPrixI(double prixI) {
		this.prixI = prixI;
	}


	@Override
	public String toString() {
		return "Ingredient [idI=" + idI + ", nameI=" + nameI + ", prixI=" + prixI + "]";
	}
	
	
}
