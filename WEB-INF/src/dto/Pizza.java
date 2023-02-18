package dto;

public class Pizza {
	
	private String nom;
	private String pate;
	private int prix;
	
	public Pizza(String nom, String pate, int prix) {
		super();
		this.nom = nom;
		this.pate = pate;
		this.prix = prix;
	}
	
	public Pizza() {	
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPate() {
		return pate;
	}

	public void setPate(String pate) {
		this.pate = pate;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	@Override
	public String toString() {
		return "Pizza [nom=" + nom + ", pate=" + pate + ", prix=" + prix + "]";
	}
}
