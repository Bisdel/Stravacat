package fr.formation.api.response;

import java.util.List;

import org.springframework.beans.BeanUtils;

import fr.formation.model.Actualite;
import fr.formation.model.Animal;
import fr.formation.model.Ville;
import io.micrometer.common.lang.NonNull;

public class VilleResponse {

	private int id;
	private String nom;
	private String ambiance;

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

	public static VilleResponse convert(Ville ville) {
		VilleResponse response = new VilleResponse();
		BeanUtils.copyProperties(ville, response);
		return response;
	}

}
