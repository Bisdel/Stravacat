package fr.formation.api.request;

import jakarta.validation.constraints.NotBlank;

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
