package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ingredient {
	
	@JsonProperty
	private int idI;
	@JsonProperty
	private String nameI;
	
	public Ingredient(int id, String name) {
		this.idI = id;
		this.nameI = name;
	}
	
	public Ingredient() {
	}
	
	public int getIdI() {
		return idI;
	}
	public void setIdI(int id) {
		this.idI = id;
	}
	
	public String getNameI() {
		return nameI;
	}
	public void setNameI(String name) {
		this.nameI = name;
	}
	@Override
	public String toString() {
		return "Ingredient [id = " + idI + ", name = " + nameI + "]";
	}
	
}
