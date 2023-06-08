package fr.formation.api.request;

import java.util.List;

import org.springframework.beans.BeanUtils;

import fr.formation.model.Actualite;
import fr.formation.model.Animal;
import fr.formation.model.Parcours;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class AnimalRequest {

    @NotBlank
    private String pseudo;

    @NotBlank
    private String email;
    
    private String password;
    
    @Positive
    private int age;
    
    @NotBlank
    private String espece;
    
    private String ville;

    private List<Parcours> parcours;

    private List<Actualite> actualites;

	public Animal convertToAnimal(){
		Animal animal = new Animal();
		BeanUtils.copyProperties(this, animal);
		return animal;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public List<Parcours> getParcours() {
		return parcours;
	}

	public void setParcours(List<Parcours> parcours) {
		this.parcours = parcours;
	}

	public List<Actualite> getActualites() {
		return actualites;
	}

	public void setActualites(List<Actualite> actualites) {
		this.actualites = actualites;
	}

	
}
