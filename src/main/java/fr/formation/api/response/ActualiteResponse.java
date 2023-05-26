package fr.formation.api.response;

import org.springframework.beans.BeanUtils;

import fr.formation.model.Actualite;

public class ActualiteResponse {
    private int actu_id;
    private String actu_description;
    private String pseudo;
    private String ville;
    // private LocalDateTime actu_timestamp;

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

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    // public LocalDateTime getActu_timestamp() {
    //     return actu_timestamp;
    // }

    // public void setActu_timestamp(LocalDateTime actu_timestamp) {
    //     this.actu_timestamp = actu_timestamp;
    // }

    public static ActualiteResponse convert(Actualite actualite) {
        ActualiteResponse response = new ActualiteResponse();	
        BeanUtils.copyProperties(actualite, response);	
        return response;
    }
}
