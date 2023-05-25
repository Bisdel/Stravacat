package fr.formation.api.request;

import jakarta.validation.constraints.NotBlank;

public class ActualiteRequest {
    @NotBlank
    private String description;

    @NotBlank
    private String ville;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

}
