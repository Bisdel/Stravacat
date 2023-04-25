package fr.formation.model;

public class Animal {
    private int id;
    private String pseudo;
    private String password;
    private int age;
    private int nbPatounes;
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

    public int getQuartierId() {
        return quartierId;
    }

    public void setQuartierId(int quartierId) {
        this.quartierId = quartierId;
    }

}
