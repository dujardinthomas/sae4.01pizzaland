package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Client {
	
	@JsonProperty
	private int idC;
	
	@JsonProperty
	private String nomC;
	
	@JsonProperty
	private String adresseC;

	public Client(int idC, String nomC, String adresseC) {
		this.idC = idC;
		this.nomC = nomC;
		this.adresseC = adresseC;
	}

	public Client() {
	}

	public int getIdC() {
		return idC;
	}

	public void setIdC(int idC) {
		this.idC = idC;
	}

	public String getNomC() {
		return nomC;
	}

	public void setNomC(String nomC) {
		this.nomC = nomC;
	}

	public String getAdresseC() {
		return adresseC;
	}

	public void setAdresseC(String adresseC) {
		this.adresseC = adresseC;
	}

}
