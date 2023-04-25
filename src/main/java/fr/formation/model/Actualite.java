package fr.formation.model;

import java.time.LocalDateTime;

public class Actualite {
    private int actu_id;
    private LocalDateTime actu_timestamp;
    private String actu_coordonneesgps; // VARCHAR(50)
    private String actu_description; 
    private int actu_animal_id;
    private int actu_ville_id;
    private boolean actu_isPrivate;
//  private JSONB actu_contactsIdentifies;
    
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
	public int getActu_animal_id() {
		return actu_animal_id;
	}
	public void setActu_animal_id(int actu_animal_id) {
		this.actu_animal_id = actu_animal_id;
	}
	public int getActu_ville_id() {
		return actu_ville_id;
	}
	public void setActu_ville_id(int actu_ville_id) {
		this.actu_ville_id = actu_ville_id;
	}
	public Boolean getActu_isPrivate() {
		return actu_isPrivate;
	}
	public void setActu_isPrivate(Boolean actu_isPrivate) {
		this.actu_isPrivate = actu_isPrivate;
	}
}
