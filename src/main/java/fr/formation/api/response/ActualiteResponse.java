package fr.formation.api.response;

import java.time.LocalDateTime;

import fr.formation.model.Animal;

public class ActualiteResponse {
    private int id;
    private LocalDateTime actu_timestamp;
    private String description;
    private Animal animal;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Animal getAnimal() {
        return animal;
    }
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
    public LocalDateTime getActu_timestamp() {
        return actu_timestamp;
    }
    public void setActu_timestamp(LocalDateTime actu_timestamp) {
        this.actu_timestamp = actu_timestamp;
    }
}
