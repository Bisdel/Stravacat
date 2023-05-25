package fr.formation.api.response;

import java.util.List;

import org.springframework.beans.BeanUtils;

import fr.formation.model.Actualite;
import fr.formation.model.Animal;
import fr.formation.model.Ville;

public class VilleResponse {
 
 private int id;
 private String nom;
 private String ambiance;
 
 private List<Animal> animaux;
 private List<Actualite> actualites;

public List<Actualite> getActualites() {
	return actualites;
}

public void setActualites(List<Actualite> actualites) {
	this.actualites = actualites;
}

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
 
	public static VilleResponse convert(Ville ville) {
		VilleResponse response = new VilleResponse();
		BeanUtils.copyProperties(ville, response);
		return response;
	}
	
}
