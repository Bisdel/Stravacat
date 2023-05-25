package fr.formation.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonView;

import fr.formation.api.Views;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "actualite")
public class Actualite {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "actu_id")
	@JsonView(Views.Common.class)
    private int actu_id;

	@Column(name = "actu_timestamp", nullable = false)
	@JsonView(Views.Actualite.class)
    private LocalDateTime actu_timestamp;

	@Column(name = "actu_coordonneesgps", length = 50)
    private String actu_coordonneesgps;

	@Column(name = "actu_description", length = 500)
	@JsonView(Views.Actualite.class)
    private String actu_description;

	@Column(name = "actu_isPrivate", nullable = false)
    private boolean actu_isPrivate;

//  private JSONB actu_contactsIdentifies;
    
	@ManyToOne
	@JoinColumn(name = "actu_animal_id")
	@JsonView(Views.Actualite.class)
	private Animal animal;

	@ManyToOne
	@JoinColumn(name = "actu_ville_id")
	private Ville ville;

	public int getActu_id() {
		return actu_id;
	}

	public void setActu_id(int actu_id) {
		this.actu_id = actu_id;
	}

	public LocalDateTime getActu_timestamp() {
		return actu_timestamp;
	}

	public void setActu_timestamp(LocalDateTime actu_timestamp) {
		this.actu_timestamp = actu_timestamp;
	}
	
	public String getActu_coordonneesgps() {
		return actu_coordonneesgps;
	}

	public void setActu_coordonneesgps(String actu_coordonneesgps) {
		this.actu_coordonneesgps = actu_coordonneesgps;
	}

	public String getActu_description() {
		return actu_description;
	}

	public void setActu_description(String actu_description) {
		this.actu_description = actu_description;
	}

	public Boolean getActu_isPrivate() {
		return actu_isPrivate;
	}

	public void setActu_isPrivate(boolean actu_isPrivate) {
		this.actu_isPrivate = actu_isPrivate;
	}

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

	@Override
	public String toString() {
		return "Actu#" + this.actu_id + " de " + this.animal.getPseudo() + " - Private = "  + this.getActu_isPrivate() + " - " + this.getActu_timestamp().toLocalDate() + " à " + this.getActu_timestamp().toLocalTime() + ", " + this.getActu_description();
	}
}
