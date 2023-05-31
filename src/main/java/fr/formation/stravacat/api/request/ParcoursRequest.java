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



}
