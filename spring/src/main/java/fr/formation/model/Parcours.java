package fr.formation.model;
import java.sql.Time;
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
		@JsonView(Views.Parcours.class)
	private int id;

		@ManyToOne
        @JoinColumn (name = "parc_ville_id")
		@JsonView(Views.Parcours.class)
	private Ville ville;

        @Column (name = "parc_date")
		@JsonView(Views.Parcours.class)
	private LocalDateTime datePublicationParcours;

        @Column (name = "parc_trace", columnDefinition="text")
		@JsonView(Views.Parcours.class)
	private String traceGpsParcours;

		@Column (name = "parc_temps")
		@JsonView(Views.Parcours.class)
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

		public Ville getVille() {
			return ville;
		}

		public void setVille(Ville villeParcours) {
			this.ville = villeParcours;
		}

		public LocalDateTime getDatePublicationParcours() {
			return datePublicationParcours;
		}

		public void setDatePublicationParcours(LocalDateTime datePublicationParcours) {
			this.datePublicationParcours = datePublicationParcours;
		}

		public String getTraceGpsParcours() {
			return traceGpsParcours;
		}

		public void setTraceGpsParcours(String traceGpsParcours) {
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
