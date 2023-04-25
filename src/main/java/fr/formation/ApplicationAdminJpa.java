package fr.formation;

import java.util.List;
import java.util.Scanner;

import fr.formation.model.Animal;
import fr.formation.repo.IRepositoryAnimal;
import fr.formation.repo.sql.RepositoryAnimalSql;

public class ApplicationAdminJpa extends ApplicationAnimalSql {

    public static Scanner scadmin = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("\nVous avez lancé le programme avec les droits d'administrateur.");

        IRepositoryAnimal repoAnimalSql = new RepositoryAnimalSql();

        System.out.println("\n-- Affichage des animaux présents sur le site :");
        List<Animal> animaux = repoAnimalSql.findAll();
        for (Animal animal : animaux) {
            System.out.println("Animal #"+animal.getId()+" : "+animal.getPseudo()+", "+animal.getAge()+" ans.");
        }
        
        System.out.println("\nVoulez-vous supprimer un animal ?");

        if(Navigation.choixOuiNon()){
            repoAnimalSql.deleteEntry();
        }
        System.out.println("Merci d'être passé Monsieur l'Administrateur, ça fait toujours plaisir !\nA bientôt !");
    }

}
