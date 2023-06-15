package fr.formation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity // Annotation @Entity indique que cette classe est une entité persistante
@Table(name = "abonne") // Annotation @Table spécifie le nom de la table correspondante dans la base de
						// données
public class Abonnes {
	// Annotation @Id indique que le champ id est la clé primaire de l'entité
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "abon_id")
	private int id;

	// Annotation @Column spécifie que le champ est mappé à une colonne de la table
	@Column(name = "abon_pseudo", length = 20, nullable = false)
	// Annotation @NotBlank spécifie que le champ ne peut pas être vide
	@NotBlank(message = "Le pseudo est obligatoire")
	private String pseudo;

	@Column(name = "abon_age")
	private int age;

	@Column(name = "abon_espece")
	private String espece;

	@Column(name = "abon_ville_id")
	private int ville_id;

	@Column(name = "abon_animal_id")
	private int animal_id;

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

}
