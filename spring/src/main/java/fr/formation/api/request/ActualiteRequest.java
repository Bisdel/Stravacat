package fr.formation.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class ActualiteRequest {

    private boolean actu_isprivate;

    @NotBlank
    private String actu_description;

    @Positive
    private int ville;

    @Positive
    private int animal;

    public int getVille() {
        return ville;
    }

    public void setVille(int ville) {
        this.ville = ville;
    }

    public int getAnimal() {
        return animal;
    }

    public void setAnimalId(int animal) {
        this.animal = animal;
    }

    public boolean isActu_isprivate() {
        return actu_isprivate;
    }

    public void setActu_isprivate(boolean actu_isprivate) {
        this.actu_isprivate = actu_isprivate;
    }

    public String getActu_description() {
        return actu_description;
    }

    public void setActu_description(String actu_description) {
        this.actu_description = actu_description;
    }



}
