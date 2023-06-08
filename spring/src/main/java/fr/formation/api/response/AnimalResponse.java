package fr.formation.api.response;

public class AnimalResponse {
	private int id;
	private String pseudo;
	private int age;
	private String espece;
	private VilleResponse villeResponse;

	public VilleResponse getVilleResponse() {
		return villeResponse;
	}

	public void setVilleResponse(VilleResponse villeResponse) {
		this.villeResponse = villeResponse;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEspece() {
		return espece;
	}

	public void setEspece(String espece) {
		this.espece = espece;
	}

}
