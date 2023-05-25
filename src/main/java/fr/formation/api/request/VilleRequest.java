package fr.formation.api.request;

import java.util.List;

import fr.formation.model.Actualite;
import fr.formation.model.Animal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class VilleRequest {

	@NotBlank
	private String nom;
	@NotBlank
	private String ambiance;

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

}
