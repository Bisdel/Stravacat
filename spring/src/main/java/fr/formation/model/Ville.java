package fr.formation.model;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.formation.api.response.VilleResponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "ville")
public class Ville {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vill_id")
	private int id;

	@NotBlank
	@Column(name = "vill_nom", length = 30, nullable = false)
	private String nom;

	@OneToMany(mappedBy = "ville")
	@JsonIgnore
	private List<Animal> animaux;

	@OneToMany(mappedBy = "ville")
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

	public VilleResponse convert() {
		VilleResponse villeResponse = new VilleResponse();
		BeanUtils.copyProperties(this, villeResponse);
		return villeResponse;
	}
}
