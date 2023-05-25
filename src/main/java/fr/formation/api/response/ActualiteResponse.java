package fr.formation.api.response;

import fr.formation.model.Animal;

public class ActualiteResponse {
    private int id;
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
}
