package fr.formation.api.response;

import java.sql.Time;
import java.time.LocalDateTime;

public class ParcoursDetailResponse {
    private int id;
    private String villeParcours;
    private LocalDateTime datePublicationParcours;
    private int traceGpsParcours;
    private Time tempsParcours;

    
    public void setId(int id) {
        this.id = id;
    }
    public void setVilleParcours(String villeParcours) {
        this.villeParcours = villeParcours;
    }
    public void setDatePublicationParcours(LocalDateTime datePublicationParcours) {
        this.datePublicationParcours = datePublicationParcours;
    }
    public void setTraceGpsParcours(int traceGpsParcours) {
        this.traceGpsParcours = traceGpsParcours;
    }
    public void setTempsParcours(Time tempsParcours) {
        this.tempsParcours = tempsParcours;
    }
    public int getId() {
        return id;
    }
    public String getVilleParcours() {
        return villeParcours;
    }
    public LocalDateTime getDatePublicationParcours() {
        return datePublicationParcours;
    }
    public int getTraceGpsParcours() {
        return traceGpsParcours;
    }
    public Time getTempsParcours() {
        return tempsParcours;
    } 

    
}
