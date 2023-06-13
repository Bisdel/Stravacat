package fr.formation.api.response;

import org.springframework.beans.BeanUtils;

import fr.formation.model.Abonnes;
import fr.formation.model.Animal;
import fr.formation.model.Ville;

public class AbonnesResponse {

	private int id;
	private String pseudo;
	private int age;
	private String espece;
	private int ville_id;
	private int animal_id;
	private Ville ville;
	private Animal animal;

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Ville getVille() {
		return ville;
	}

	public void setVille(Ville ville) {
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

	public int getVille_id() {
		return ville_id;
	}

	public void setVille_id(int ville_id) {
		this.ville_id = ville_id;
	}

	public int getAnimal_id() {
		return animal_id;
	}

	public void setAnimal_id(int animal_id) {
		this.animal_id = animal_id;
	}

	public static AbonnesResponse convert(Abonnes abonnes) {
		AbonnesResponse response = new AbonnesResponse();
		BeanUtils.copyProperties(abonnes, response);
		return response;
	}

}
