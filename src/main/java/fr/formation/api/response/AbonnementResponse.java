package fr.formation.api.response;

import org.springframework.beans.BeanUtils;

import fr.formation.model.Abonnement;
import jakarta.validation.constraints.NotBlank;

public class AbonnementResponse {

	@NotBlank
	private int id;
	@NotBlank
	private int animal_id;
	@NotBlank
	private String pseudo;
	@NotBlank
	private int age;
	@NotBlank
	private String espece;
	@NotBlank
	private int Ville_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAnimal_id() {
		return animal_id;
	}

	public void setAnimal_id(int animal_id) {
		this.animal_id = animal_id;
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

	public int getVille_id() {
		return Ville_id;
	}

	public void setVille_id(int ville_id) {
		Ville_id = ville_id;
	}

	public static AbonnementResponse convert(Abonnement abonnement) {
		AbonnementResponse response = new AbonnementResponse();
		BeanUtils.copyProperties(abonnement, response);
		return response;
	}

}