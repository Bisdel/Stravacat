package fr.formation.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ville")
public class Ville {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vill_id")
private int id;
	
	@Column(name = "vill_ville" , length = 30 , nullable = false)
private String ville;
	
	@Column(name = " vill_ambiance" , length = 250 , nullable=false)
private String ambiance;

	@OneToMany(mappedBy = "ville")
	private List<Animal> animaux;



public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getVille() {
	return ville;
}
public void setVille(String ville) {
	this.ville = ville;
}
public String getAmbiance() {
	return ambiance;
}
public void setAmbiance(String ambiance) {
	this.ambiance = ambiance;
}





}
