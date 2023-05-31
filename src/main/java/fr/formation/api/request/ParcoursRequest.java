package fr.formation.api.request;
import fr.formation.model.Parcours;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ParcoursRequest {
    
    @NotNull
    private int id;

    @NotBlank
    private String villeParcours;

    @NotBlank
    private LocalDateTime datePublicationParcours;

    private int traceGpsParcours;

    @NotBlank
    private Time tempsParcours;

    @NotBlank
    private List<Parcours> parcours;

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

    public void setTempsParcours(Time tempsParcours) {
        this.tempsParcours = tempsParcours;
    }

    public List<Parcours> getParcours() {
        return parcours;
    }

    public void setParcours(List<Parcours> parcours) {
        this.parcours = parcours;
    }



}
