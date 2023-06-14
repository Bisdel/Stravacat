package fr.formation.api.request;

import java.sql.Time;
import java.time.LocalDateTime;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class ParcoursRequest {

    private int id;

    @NotBlank
    private String villeParcours;

    @Valid
    private LocalDateTime datePublicationParcours;

    @NotBlank
    private String traceGpsParcours;

    @Valid
    private Time tempsParcours;

    @Positive
    private int animalId;

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

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

}
