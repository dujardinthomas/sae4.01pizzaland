package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ingredient {
	
	@JsonProperty
	private int id;
	@JsonProperty
	private String name;
	
	public Ingredient(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Ingredient() {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Ingredient [id = " + id + ", name = " + name + "]";
	}
	
}
