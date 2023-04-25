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
    @Column(name = "anim_id")
    private int id;

    @Column(name = "anim_pseudo")
    private String pseudo;

    @Column(name = "anim_password")
    private String password;
    
    @Column(name = "anim_age")
    private int age;
    
    @Column(name = "anim_nb_patounes")
    private int nbPatounes;
    
    @ManyToOne
    @JoinColumn(name = "anim_quartier_id")
    private Quartier quartier;

    @OneToMany(mappedBy = "parcours")
    private List<Parcours> parcours;

    //for SQL Apps
    private int quartierId;

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

    public int getNbPatounes() {
        return nbPatounes;
    }

    public void setNbPatounes(int nbPatounes) {
        this.nbPatounes = nbPatounes;
    }

    public Quartier getQuartier() {
        return quartier;
    }

    public void setQuartier(Quartier quartier) {
        this.quartier = quartier;
    }

    public int getQuartierId() {
        return quartierId;
    }

    public void setQuartierId(int quartierId) {
        this.quartierId = quartierId;
    }

    public List<Parcours> getParcours() {
        return parcours;
    }

    public void setParcours(List<Parcours> parcours) {
        this.parcours = parcours;
    }
}
