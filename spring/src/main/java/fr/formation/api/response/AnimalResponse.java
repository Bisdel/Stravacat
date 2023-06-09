package fr.formation.api.response;

import org.springframework.beans.BeanUtils;

import fr.formation.model.Animal;

public class AnimalResponse {
	private int id;
	private String pseudo;
	private int age;
	private String espece;
	private VilleResponse ville;

	public VilleResponse getVille() {
		return ville;
	}

	public void setVille(VilleResponse ville) {
		this.ville = ville;
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

	public static AnimalResponse convert(Animal animal){
		AnimalResponse animalResponse = new AnimalResponse();
		// Ne fonctionne pas avec Ville car != VilleResponse
		BeanUtils.copyProperties(animal, animalResponse);
		// Donc on le rajoute alamano
		animalResponse.setVille(animal.getVille().convert());
		return animalResponse;
	}

}
