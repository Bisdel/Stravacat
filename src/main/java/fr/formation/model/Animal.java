package fr.formation.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "animal")
public class Animal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "anim_id", nullable = false)
    private int id;

    @Column(name = "anim_pseudo", nullable = false)
    private String pseudo;

    @Column(name = "anim_password", nullable = false)
    private String password;
    
    @Column(name = "anim_age", nullable = false)
    private int age;
    
    @Column(name = "anim_espece", nullable = false)
    private String espece;
    
    @ManyToOne
    @JoinColumn(name = "anim_ville_id", nullable = false)
    private Ville ville;

    @OneToMany(mappedBy = "animal")
    private List<Parcours> parcours;

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
