package fr.formation;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import fr.formation.model.Actualite;
import fr.formation.repo.IActualiteRepository;
import fr.formation.repo.sql.RepositoryActualiteSql;

public class ApplicationActu {
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

	public static void listerActualites() {
		
		IActualiteRepository repoActualite = new RepositoryActualiteSql();
		
		List<Actualite> actualites = repoActualite.findAll();	
		for (Actualite a : actualites) {
//			if (!a.getActu_isPrivate()) {
				System.out.println("Actu#" + a.getActu_id() + " - " + a.getActu_timestamp().toLocalDate() + " à " + a.getActu_timestamp().toLocalTime() + ", " + a.getActu_description());
//			}
		}
	}
	
	public static void ajouterActualite() {
		IActualiteRepository repoActualite = new RepositoryActualiteSql();
		Actualite actualite = new Actualite();
		
		System.out.println("Description :");
		actualite.setActu_description(sc.nextLine());
		
		System.out.println("Id actu_animal_id :");
		actualite.setActu_animal_id(sc.nextInt());
		
		System.out.println("Id actu_quartier_id :");
		actualite.setActu_quartier_id(sc.nextInt());
		
		System.out.println("Privé ? (true/false) :");		
		actualite.setActu_isPrivate(sc.nextBoolean());
		
		repoActualite.createEntry();
		
		System.out.println("Actualité #" + actualite.getActu_id() + " ajoutée");
		System.out.println("Actu#" + actualite.getActu_id() + " - " + actualite.getActu_timestamp() + ", " + actualite.getActu_description());
	}
	
	public static void afficherActualite() {
		IActualiteRepository repoActualite = new RepositoryActualiteSql();
		try {
			System.out.println("Saisis l'id de l'animal l'ancien");
			int animalId = sc.nextInt();
			List<Actualite> actualites = repoActualite.findByAnimalId(animalId);
			
			for (Actualite a : actualites)
				if (a.getActu_isPrivate()) {
					String confid = "privé";
					System.out.println("Actu#" + a.getActu_id() + " Statut " + confid + " - " + a.getActu_timestamp().toLocalDate() + " à " + a.getActu_timestamp().toLocalTime() + ", " + a.getActu_description());
				}
				else {
					String confid = "public";
					System.out.println("Actu#" + a.getActu_id() + " Statut " + confid + " - " + a.getActu_timestamp().toLocalDate() + " à " + a.getActu_timestamp().toLocalTime() + ", " + a.getActu_description());
				}				
		}
		catch (InputMismatchException ex) {
			ex.printStackTrace();
			System.out.println("Pas d'animal donc pas d'actu");
		}
	}
	
	public static void modifierActualite() {
		afficherActualite();
		IActualiteRepository repoActualite = new RepositoryActualiteSql();
		System.out.println("-------------------------------");
		try {
            Actualite actualite = new Actualite();
			System.out.println("actu_animal_id, actu_quartier_id, description, confidentialité");
			
            actualite.setActu_animal_id(sc.nextInt());
			actualite.setActu_quartier_id(sc.nextInt());
			sc.nextLine();
			actualite.setActu_description(sc.nextLine());
            actualite.setActu_isPrivate(sc.nextBoolean());
			repoActualite.updateEntry(actualite);
		}
		catch (InputMismatchException ex) {
			ex.printStackTrace();
			System.out.println("Id inexistant !");
		}
		listerActualites();
	}
	
	public static void supprimerActualite() {
		afficherActualite();
		IActualiteRepository repoActualite = new RepositoryActualiteSql();
		System.out.println("-------------------------------");	
		try {
			repoActualite.deleteEntry();
		}
		catch (InputMismatchException ex) {
			ex.printStackTrace();
			System.out.println("L'actu n'existe pas");
		}
	}
}
