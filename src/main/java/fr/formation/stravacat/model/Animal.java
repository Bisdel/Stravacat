package fr.formation.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import fr.formation.api.Views;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "anim_id", nullable = false)
    @JsonView(Views.Common.class)
    private int id;

    @JsonView(Views.Animal.class)
    @Column(name = "anim_pseudo", nullable = false)
    @NotBlank
    private String pseudo;

    @JsonView(Views.AnimalDetail.class)
    @Column(name = "anim_email", nullable = false)
    @NotBlank
    private String email;
    
    @Column(name = "anim_password", nullable = false)
    private String password;
    
    @JsonView(Views.Animal.class)
    @Column(name = "anim_age", nullable = false)
    @Positive
    private int age;
    
    @JsonView(Views.Animal.class)
    @Column(name = "anim_espece", nullable = false)
    @NotBlank
    private String espece;
    
    @JsonView(Views.AnimalDetail.class)
    @ManyToOne
    @JoinColumn(name = "anim_ville_id", nullable = false)
    @Valid
    private Ville ville;

    @JsonView(Views.AnimalDetail.class)
    @OneToMany(mappedBy = "animal")
    private List<Parcours> parcours;

    @JsonView(Views.AnimalDetail.class)
    @OneToMany(mappedBy = "animal")
    private List<Actualite> actualites;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEspece() {
        return espece;
    }

    public void setEspece(String espece) {
        this.espece = espece;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public List<Parcours> getParcours() {
        return parcours;
    }

    public void setParcours(List<Parcours> parcours) {
        this.parcours = parcours;
    }

    public List<Actualite> getActualites() {
        return actualites;
    }

    public void setActualites(List<Actualite> actualites) {
        this.actualites = actualites;
    }
}
