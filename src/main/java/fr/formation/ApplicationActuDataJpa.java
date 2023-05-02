package fr.formation;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import fr.formation.config.AppConfig;
import fr.formation.model.Actualite;
import fr.formation.repo.IActualiteRepository;

@Component
public class ApplicationActuDataJpa {
    public static Scanner sc = new Scanner(System.in);
	
	@Autowired
	private IActualiteRepository repoActualite;

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		ApplicationActuDataJpa app = context.getBean(ApplicationActuDataJpa.class);
		app.run();
		context.close();
	}

	public void run() {
		int choix = 0;	
		do {
			System.out.println("-------------------------------");
			
			System.out.println("-- 1. Lister les actualités");
			System.out.println("-- 2. Ajouter une actualité");
			System.out.println("-- 3. Afficher le fil d'actualité d'un animal");
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

    private void listerActualites() { 
		System.out.println("-------------------------------");
        repoActualite.findAll().forEach(System.out::println);
    }

    private void ajouterActualite() {
		System.out.println("-------------------------------");
	}

    private void afficherActualite() {
		try {
            System.out.println("-------------------------------");
			System.out.println("Saisir le pseudo de l'animal pour afficher son fil d'actualité");
			String pseudo = sc.nextLine();
			
			repoActualite.findByAnimalPseudo(pseudo).ifPresentOrElse(
				a -> System.out.println("Actu#" + a.getActu_id() + " - Private = "  + a.getActu_isPrivate() + " - " + a.getActu_timestamp().toLocalDate() + " à " + a.getActu_timestamp().toLocalTime() + ", " + a.getActu_description()),
				() -> System.out.println("L'animal est inexistant ou son fil d'actu est vide")
			);		
		}
		catch (InputMismatchException ex) {
			ex.printStackTrace();
		}
    }

    private void modifierActualite() {
	}

    private void supprimerActualite() {
        System.out.println("-------------------------------");
        System.out.println("Suppression d'une actualité d'un animal :");
    }
}
