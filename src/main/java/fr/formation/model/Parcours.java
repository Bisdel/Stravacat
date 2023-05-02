package fr.formation.model;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "parcours")
public class Parcours {

	//------------? A PREVOIR ?-------------
		
		// private int idVille;
		//private List<contacts>;
		// private JSON traceGpsParcours;
		// private boolean isPrivateParcours;
	// -----------------------------------
		
	    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column (name = "parc_id")
	private int id;

        @Column (name = "parc_ville", length = 100)
	private String villeParcours;


        @Column (name = "parc_date")
	private LocalDateTime datePublicationParcours;

        @Column (name = "parc_trace")
	private int traceGpsParcours; // JSON ??

		@Column (name = "parc_temps")
	private Time tempsParcours; 

		@ManyToOne
		@JoinColumn(name = "parc_animal_id")
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


		public Animal getAnimal() {
			return animal;
		}

		public void setAnimal(Animal animal) {
			this.animal = animal;
		}

		public void setTempsParcours(Time tempsParcours) {
			this.tempsParcours = tempsParcours;
		}



}
