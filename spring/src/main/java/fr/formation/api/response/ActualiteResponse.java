package fr.formation.api.response;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import fr.formation.model.Actualite;

public class ActualiteResponse {
    private int actu_id;
    private String actu_description;
    private boolean actu_isprivate;
    private LocalDateTime actu_timestamp;
    private AnimalResponse animal;
    private VilleResponse ville;

    public boolean isActu_isprivate() {
        return actu_isprivate;
    }

    public void setActu_isprivate(boolean actu_isprivate) {
        this.actu_isprivate = actu_isprivate;
    }

    public int getActu_id() {
        return actu_id;
    }

    public void setActu_id(int actu_id) {
        this.actu_id = actu_id;
    }

    public String getActu_description() {
        return actu_description;
    }

    public void setActu_description(String actu_description) {
        this.actu_description = actu_description;
    }

    public LocalDateTime getActu_timestamp() {
        return actu_timestamp;
    }

    public void setActu_timestamp(LocalDateTime actu_timestamp) {
        this.actu_timestamp = actu_timestamp;
    }

    public AnimalResponse getAnimal() {
        return animal;
    }

    public void setAnimal(AnimalResponse animal) {
        this.animal = animal;
    }

    public VilleResponse getVille() {
        return ville;
    }

    public void setVille(VilleResponse ville) {
        this.ville = ville;
    }
    
    public static ActualiteResponse convert(Actualite actualite) {
        ActualiteResponse response = new ActualiteResponse();	
        BeanUtils.copyProperties(actualite, response);
        response.setAnimal(AnimalResponse.convert(actualite.getAnimal()));
		response.setVille(VilleResponse.convert(actualite.getVille()));
        return response;
    }
}
