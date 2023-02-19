package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pizza{
	
	@JsonProperty
	private int idP;
	
	@JsonProperty
	private String nomP;
	
	@JsonProperty
	private String pate;
	
	@JsonProperty
	private double prixP;
	
	
	public Pizza(int idP, String nomP, String pate, double prixP) {
		this.idP = idP;
		this.nomP = nomP;
		this.pate = pate;
		this.prixP = prixP;
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


	public void setNomP(String nomP) {
		this.nomP = nomP;
	}


	public String getPate() {
		return pate;
	}


	public void setPate(String pate) {
		this.pate = pate;
	}


	public double getPrixP() {
		return prixP;
	}


	public void setPrixP(double prixP) {
		this.prixP = prixP;
	}


	@Override
	public String toString() {
		return "Pizza [idP=" + idP + ", nomP=" + nomP + ", pate=" + pate + ", prixP=" + prixP + "]";
	}
	
	
	
	
	
	
	
}