package fr.formation.api.response;

import java.sql.Time;
import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import fr.formation.model.Parcours;

public class ParcoursResponse {
    private int id;
    private String villeParcours;
    private LocalDateTime datePublicationParcours;
    private String traceGpsParcours;
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
    public void setTraceGpsParcours(String traceGpsParcours) {
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
    public String getTraceGpsParcours() {
        return traceGpsParcours;
    }
    public Time getTempsParcours() {
        return tempsParcours;
    }
    public static ParcoursResponse convert(Parcours p) {
        ParcoursResponse parcoursResponse = new ParcoursResponse();
        BeanUtils.copyProperties(p, parcoursResponse);

        return parcoursResponse;
    } 

    
}
