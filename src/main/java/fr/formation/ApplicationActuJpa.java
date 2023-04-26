package fr.formation;

import java.util.Scanner;

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
			System.out.println("-- 3. Afficher les actualités d'un pseudo");
			System.out.println("-- 4. Modifier une actualité");
			System.out.println("-- 5. Supprimer une actualité");	
			System.out.println("-- 0. Quitter");
			
			System.out.println("-------------------------------");		
			System.out.println("Choisis ce que tu veux faire l'ancien !");
			
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
        repoActualite.findAll().forEach(System.out::println);
    }

    private static void ajouterActualite() {
    }

    private static void afficherActualite() {
    }

    private static void modifierActualite() {
    }

    private static void supprimerActualite() {
    }

}
