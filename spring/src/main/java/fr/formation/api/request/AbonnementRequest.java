package fr.formation.api.request;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotBlank;

public class AbonnementRequest {

	@NonNull
	private int animal_id;
	@NotBlank
	private String pseudo;
	@NonNull
	private int age;
	@NotBlank
	private String espece;
	@NonNull
	private int ville_id;

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
		return ville_id;
	}

	public void setVille_id(int ville_id) {
		this.ville_id = ville_id;
	}
}
