package fr.formation;

import java.util.Scanner;

import fr.formation.model.Animal;
import fr.formation.repo.IRepositoryAnimal;
import fr.formation.repo.sql.RepositoryAnimalSql;

public class ApplicationAnimalSql {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("\nBienvenue sur Stravacat, le réseau social de vos compagnons à poils préférés !");

        IRepositoryAnimal repoAnimalSql = new RepositoryAnimalSql();

        System.out.println("\n-- Rechercher un animal par son pseudo :\nEntrez un pseudo :");
        try {
            String pseudo = sc.nextLine();
            if (repoAnimalSql.findByPseudo(pseudo).isPresent()) {
                Animal animal = repoAnimalSql.findByPseudo(pseudo).get();
                System.out.println("Animal trouvé !\nIl s'appelle " + animal.getPseudo() + ", il a "
                        + animal.getAge() + " ans et " + animal.getNbPatounes() + " patounes.");
                System.out.println("Voulez-vous mettre à jour une de ces informations ?");
                if (Navigation.choixOuiNon()){
                    repoAnimalSql.updateEntry(animal);                    
                }
            } else {
                System.out.println("Animal non trouvé :(\nVoulez-vous créer un nouvel animal ?");
                if (Navigation.choixOuiNon()) {
                    repoAnimalSql.createEntry();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// Afficher tous les animaux
// Afficher les animaux dans une ville/ville choisi
// Mettre à jour son profil animal
// Supprimer une info du profil/supprimer son compte