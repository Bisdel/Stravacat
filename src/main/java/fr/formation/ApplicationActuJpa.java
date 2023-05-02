package fr.formation;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import fr.formation.model.Actualite;
import fr.formation.repo.IActualiteRepository;
import fr.formation.repo.jpa.RepositoryActualiteJpa;

public class ApplicationActuJpa {
    public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		int choix = 0;	
		do {
			System.out.println("-------------------------------");
			
			System.out.println("-- 1. Lister les actualités");
			System.out.println("-- 2. Ajouter une actualité");
			System.out.println("-- 3. Afficher les actualités d'un animal");
			System.out.println("-- 4. Modifier une actualité");
			System.out.println("-- 5. Supprimer une actualité");	
			System.out.println("-- 0. Quitter");
			
			System.out.println("-------------------------------");		
			System.out.println("Selectionner une action");
			
			choix = sc.nextInt();
			sc.nextLine();
			switch (choix) {
				case 1: listerActualites(); break;
				case 2: ajouterActualite(); break;
				case 3: afficherActualite(); break;
				case 4: modifierActualite(); break;
				case 5: supprimerActualite(); break;			
			}
		} while (choix != 0);	
		sc.close();
	}

    private static void listerActualites() {
        IActualiteRepository repoActualite = new RepositoryActualiteJpa();
		System.out.println("-------------------------------");
        repoActualite.findAll().forEach(System.out::println);
    }

    private static void ajouterActualite() {
        IActualiteRepository repoActualite = new RepositoryActualiteJpa();
		System.out.println("-------------------------------");
        repoActualite.createEntry();
	}
    private static void afficherActualite() {
        IActualiteRepository repoActualite = new RepositoryActualiteJpa();
		try {
            System.out.println("-------------------------------");
			System.out.println("Saisir l'id de l'animal pour afficher son actualité");
			int animalId = sc.nextInt();
			
			List<Actualite> actualites = repoActualite.findByAnimalId(animalId);
			if (actualites.isEmpty()) {
				System.out.println("L'animal est inexistant ou le fil d'actu est vide");
			}
            else {
                actualites.forEach(System.out::println);
			}			
		}
		catch (InputMismatchException ex) {
			ex.printStackTrace();
		}
    }

    private static void modifierActualite() {
		System.out.println("-------------------------------");

    }

    private static void supprimerActualite() {
		afficherActualite();
        IActualiteRepository repoActualite = new RepositoryActualiteJpa();
        System.out.println("-------------------------------");
        System.out.println("Suppression d'une actualité d'un animal :");
        repoActualite.deleteEntry();
    }
}
