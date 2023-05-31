package fr.formation.request;

import jakarta.validation.constraints.NotBlank;

public class AnimalLogin {

    @NotBlank
    private String pseudo;
    
    @NotBlank
    private String password;
    
    public String getPseudo() {
        return pseudo;
    }
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    
}
