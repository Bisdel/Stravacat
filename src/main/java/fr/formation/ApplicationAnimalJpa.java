package fr.formation;

import java.util.Scanner;

import fr.formation.model.Animal;
import fr.formation.repo.IRepositoryAnimal;
import fr.formation.repo.jpa.RepositoryAnimalJpa;

public class ApplicationAnimalJpa {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        IRepositoryAnimal repoAnimalJpa = new RepositoryAnimalJpa();

        System.out.println("\nBienvenue sur Stravacat, le réseau social de vos compagnons à poils préférés !");
        System.out.println("\n-- Rechercher un animal par son pseudo :\nEntrez un pseudo :");
        try {
            String pseudo = sc.nextLine();
            if (repoAnimalJpa.findByPseudo(pseudo).isPresent()) {
                Animal animal = repoAnimalJpa.findByPseudo(pseudo).get();
                System.out.println("Animal trouvé !\nIl s'appelle " + animal.getPseudo() + ", il a "
                        + animal.getAge() + " ans et " + animal.getEspece() + " espece.");
                System.out.println("Voulez-vous mettre à jour une de ces informations ?");
                if (Navigation.choixOuiNon()){
                    repoAnimalJpa.updateEntry(animal);                    
                }
            } else {
                System.out.println("Animal non trouvé :(\nVoulez-vous créer un nouvel animal ?");
                if (Navigation.choixOuiNon()) {
                    repoAnimalJpa.createEntry();
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