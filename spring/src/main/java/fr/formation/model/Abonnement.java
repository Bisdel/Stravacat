package fr.formation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "abonnement")
public class Abonnement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "abnm_id")
	private int id;

	@Column(name = "abnm_animal_id")
	private int animal_id;

	@Column(name = "abnm_pseudo", length = 50, nullable = false)
	private String pseudo;

	@Column(name = "abnm_age", nullable = false)
	private int age;

	@Column(name = "abnm_espece")
	private String espece;

	// ajouter un join column
	@Column(name = "abnm_ville_id", nullable = false)
	private int ville_id;

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
		return ville_id;
	}

	public void setVille_id(int ville_id) {
		this.ville_id = ville_id;
	}

}
