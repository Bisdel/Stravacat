package fr.formation.api.request;

import java.util.List;

import fr.formation.model.Actualite;
import fr.formation.model.Animal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class VilleRequest {

	@NotNull
	private int id;
	@NotBlank
	private String nom;
	@NotBlank
	private String ambiance;

	@NotBlank
	private List<Animal> animaux;
	@NotBlank
	private List<Actualite> actualites;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAmbiance() {
		return ambiance;
	}

	public void setAmbiance(String ambiance) {
		this.ambiance = ambiance;
	}

	public List<Animal> getAnimaux() {
		return animaux;
	}

	public void setAnimaux(List<Animal> animaux) {
		this.animaux = animaux;
	}

	public List<Actualite> getActualites() {
		return actualites;
	}

	public void setActualites(List<Actualite> actualites) {
		this.actualites = actualites;
	}

}
