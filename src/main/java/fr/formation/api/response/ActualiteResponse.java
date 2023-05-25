package fr.formation.api.response;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import fr.formation.model.Actualite;

public class ActualiteResponse {
    private int id;
    private String description;
    private String pseudo;
    private String ville;
    private LocalDateTime date;

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
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public static ActualiteResponse convert(Actualite actualite) {
		ActualiteResponse response = new ActualiteResponse();	
		BeanUtils.copyProperties(actualite, response);	
		return response;
	}
}
