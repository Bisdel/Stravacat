package fr.formation.repo.jpa;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Optional;
import fr.formation.Application;
import fr.formation.ApplicationAdmin;
import fr.formation.model.Animal;
import fr.formation.repo.IRepositoryAnimal;

import java.lang.String;

public class RepositoryAnimalJpa extends AbstractRepositoryJpa implements IRepositoryAnimal {

    @Override
    public List<Animal> findAll() { // est-ce qu'on print dans app ou dans repo ?
        List<Animal> animaux = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * from animal ORDER BY anim_id ASC");

            while (results.next()) {
                Animal animal = new Animal();
                animal.setAnim_id(results.getInt("anim_id"));
                animal.setAnim_pseudo(results.getString("anim_pseudo"));
                animal.setAnim_password(results.getString("anim_password"));
                animal.setAnim_age(results.getInt("anim_age"));
                animal.setAnim_nb_patounes(results.getInt("anim_nb_patounes"));
                animal.setAnim_quartier_id(results.getInt("anim_quartier_id"));
                animaux.add(animal);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animaux;
    }

    @Override
    public Optional<Animal> findByPseudo(String pseudo) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM animal WHERE anim_pseudo = ?");
            statement.setString(1, pseudo);
            ResultSet result = statement.executeQuery();
            result.next();
            Animal animal = new Animal();
            animal.setAnim_id(result.getInt("anim_id"));
            animal.setAnim_pseudo(result.getString("anim_pseudo"));
            animal.setAnim_password(result.getString("anim_password"));
            animal.setAnim_age(result.getInt("anim_age"));
            animal.setAnim_nb_patounes(result.getInt("anim_nb_patounes"));
            animal.setAnim_quartier_id(result.getInt("anim_quartier_id"));
            return Optional.of(animal);

        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    @Override
    public void createEntry(String pseudo) {
        System.out.println(
                "Veuillez entrer les informations de votre nouvel animal, au format motdepasse - age - nombredepatounes");
        Animal animal = new Animal();
        try  {
            String infos = Application.sc.nextLine();
            animal.setAnim_pseudo(pseudo);
            animal.setAnim_password(String.format(infos.split(" - ")[0]));
            animal.setAnim_age(Integer.parseInt(infos.split(" - ")[1]));
            animal.setAnim_nb_patounes(Integer.parseInt(infos.split(" - ")[2]));
        } catch (NumberFormatException e) {
            System.out.println("Le format de l'age et/ou du nombre de patounes est incorrect.");
        } catch (IllegalFormatException e) {
            System.out.println("Le format du pseudo et/ou du mot de passe est incorrect.");
        }
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO animal(anim_pseudo, anim_password, anim_age, anim_nb_patounes) VALUES(?,?,?,?)");
            statement.setString(1, animal.getAnim_pseudo());
            statement.setString(2, animal.getAnim_password());
            statement.setInt(3, animal.getAnim_age());
            statement.setInt(4, animal.getAnim_nb_patounes());
            statement.executeUpdate();
            System.out.println("Votre nouvel animal a bien été créé !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateEntry(Animal animal) {
        boolean saisieValide = false;
        while(saisieValide == false){
            System.out.println("\nVeuillez entrer les informations à modifier, au format champ(pseudo/motdepasse/age/patounes) - donnée, exemple : pseudo - Franklinlatortue)");
            String infos = Application.sc.nextLine();
            // tries to match a field to update

            // saisieValide = true;

            // if (infos.startsWith("pseudo - ")) {
            //     animal.setAnim_pseudo(infos.substring(9));
            // }

            // // else if ;...

            // else {
            //     System.out.println("Mauvaise saisie");
            //     saisieValide = false;
            try{    
                try{
                    animal.setAnim_pseudo(String.format(infos.split("pseudo - ")[1]));
                    saisieValide = true;
                } catch (ArrayIndexOutOfBoundsException e) {
                    // ignores error thrown for the fields not filled by user to update
                } try{
                    animal.setAnim_password(String.format(infos.split("motdepasse - ")[1]));
                    saisieValide = true;
                } catch (ArrayIndexOutOfBoundsException e) {
                    // ignores error thrown for the fields not filled by user to update
                } try{
                    animal.setAnim_age(Integer.parseInt(infos.split("age - ")[1]));
                    saisieValide = true;
                } catch (ArrayIndexOutOfBoundsException e) {
                    // ignores error thrown for the fields not filled by user to update
                } try{
                    animal.setAnim_nb_patounes(Integer.parseInt(infos.split("patounes - ")[1]));
                    saisieValide = true;
                } catch (ArrayIndexOutOfBoundsException e) {
                    // ignores error thrown for the fields not filled by user to update
                }
            }
            catch (Exception e){
                System.out.println("\nUne erreur s'est glissée dans votre saisie. Veuillez bien respecter le format de saisie des données à modifier :)");
            }
        }
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE animal SET anim_pseudo = ?, anim_password = ?, anim_age = ?, anim_nb_patounes = ? WHERE anim_id = ?");
            statement.setString(1, animal.getAnim_pseudo());
            statement.setString(2, animal.getAnim_password());
            statement.setInt(3, animal.getAnim_age());
            statement.setInt(4, animal.getAnim_nb_patounes());
            statement.setInt(5, animal.getAnim_id());
            statement.executeUpdate();
            System.out.println("Les informations de votre animal ont bien été modifiées !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEntry() {
        boolean saisieValide = false;
        while(saisieValide == false){
            System.out.println("Entrez le pseudo à supprimer :");
            String pseudo = ApplicationAdmin.scadmin.nextLine();
            try {
                PreparedStatement verifStatement = connection.prepareStatement("SELECT * FROM animal WHERE anim_pseudo = ?");
                verifStatement.setString(1, pseudo);
                if(verifStatement.executeQuery().next() == false){
                    System.out.println("Le pseudo entré n'existe pas dans la base de données.");
                }
                else{
                    PreparedStatement statement = connection.prepareStatement(
                            "DELETE FROM animal WHERE anim_pseudo = ?");
                    statement.setString(1, pseudo);
                    statement.executeUpdate();
                    System.out.println("L'animal a bien été supprimé de la base de données.");
                    saisieValide = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}