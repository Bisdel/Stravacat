package fr.formation.model;

import java.sql.Time;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
@Table (name = "Parcours")
public class Parcours {

	//------------? A PREVOIR ?-------------
		
		// private int idQuartier;
		//private List<contacts>;
		// private JSONB traceGpsParcours;
		// private boolean isPrivateParcours;
	// -----------------------------------
		
	    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column (name = "par_id")
	private int id;

        @Column (name = "par_ville", length = 100)
	private String villeParcours;

        @Column (name = "par_quartier", length = 100)
	private String quartierParcours;

        @Column (name = "par_date")
	private LocalDateTime datePublicationParcours;

        @Column (name = "par_trace")
	private int traceGpsParcours; // en prévision du JSON

		@Column (name = "par_temps")
	private Time tempsParcours; 

		@OneToOne(mappedBy = "Animal")
	private Animal animal;



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVilleParcours() {
		return villeParcours;
	}
	public void setVilleParcours(String villeParcours) {
		this.villeParcours = villeParcours;
	}
	public String getQuartierParcours() {
		return quartierParcours;
	}
	public void setQuartierParcours(String quartierParcours) {
		this.quartierParcours = quartierParcours;
	}
	public LocalDateTime getDatePublicationParcours() {
		return datePublicationParcours;
	}
	public void setDatePublicationParcours(LocalDateTime datePublicationParcours) {
		this.datePublicationParcours = datePublicationParcours;
	}
	public int getTraceGpsParcours() {
		return traceGpsParcours;
	}
	public void setTraceGpsParcours(int traceGpsParcours) {
		this.traceGpsParcours = traceGpsParcours;
	}
	public Time getTempsParcours() {
		return tempsParcours;
	}
	public void setTempsParcours(Time tempsParcours) {
		this.tempsParcours = tempsParcours;
	}
	public Animal getAnimal() {
		return animal;
	}
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
    

}
