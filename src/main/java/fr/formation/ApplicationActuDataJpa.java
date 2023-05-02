package fr.formation;

import java.sql.PseudoColumnUsage;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import fr.formation.config.AppConfig;
import fr.formation.model.Actualite;
import fr.formation.model.Animal;
import fr.formation.model.Ville;
import fr.formation.repo.IActualiteRepository;
import fr.formation.repo.IAnimalRepository;
import fr.formation.repo.IVilleRepository;

@Component
public class ApplicationActuDataJpa {
    public static Scanner sc = new Scanner(System.in);
	
	@Autowired
	private IActualiteRepository repoActualite;

	@Autowired
	private IAnimalRepository repoAnimal;

	@Autowired
	private IVilleRepository repoVille;

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
		
		Actualite actualite = new Actualite();
		Ville ville = new Ville();
		Animal animal = new Animal();

		System.out.println("Entrer le nom de la ville");
        String nomVille = sc.nextLine();
        if (repoVille.findByNom(nomVille).isPresent()) {
            actualite.setVille(repoVille.findByNom(nomVille).get());
        }
        else {
			System.out.println("Ville inexistante");
			ville.setNom(nomVille);
			ville.setAmbiance(sc.nextLine());
            repoVille.save(ville);
            actualite.setVille(repoVille.findByNom(nomVille).get());
        }

        System.out.println("Entrer le pseudo de l'animal");
        String pseudo = ApplicationActuDataJpa.sc.nextLine();
        if (repoAnimal.findByPseudo(pseudo).isPresent()) {
            actualite.setAnimal(repoAnimal.findByPseudo(pseudo).get());
        }
        else {
			System.out.println("Pseudo inexistant");
			animal.setPseudo(pseudo);
			animal.setEspece(sc.nextLine());
			animal.setVille(ville);
			animal.setPassword(sc.nextLine());
			animal.setAge(sc.nextInt());
            repoAnimal.save(animal);
            actualite.setAnimal(repoAnimal.findByPseudo(pseudo).get());
        }

		System.out.println("-------------------------------");
		System.out.println("Entrer une description");
        actualite.setActu_description(sc.nextLine());
        System.out.println("Spécifier la confidentialité privée(true) ou publique(false) ?");
        actualite.setActu_isPrivate(sc.nextBoolean());
        actualite.setActu_timestamp(LocalDateTime.now());

		repoActualite.save(actualite);
		actualite.toString();
	}

    private void afficherActualite() {
		try {
            System.out.println("-------------------------------");
			System.out.println("Saisir le pseudo de l'animal pour afficher son fil d'actualité");
			String pseudo = sc.nextLine();
			
			repoActualite.findByAnimalPseudo(pseudo).ifPresentOrElse(
				a -> System.out.println("Actu#" + a.getActu_id() + " de " + a.getAnimal().getPseudo() + " - Private = "  + a.getActu_isPrivate() + " - " + a.getActu_timestamp().toLocalDate() + " à " + a.getActu_timestamp().toLocalTime() + ", " + a.getActu_description()),
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
		listerActualites();
        System.out.println("-------------------------------");
        System.out.println("Suppression d'une actualité d'un animal :");
		System.out.println("Saisir le pseudo de l'animal pour lequel une actu doit être supprimée");
		String pseudo = sc.nextLine();
		System.out.println("Saisir l'id de l'actualité à supprimer");
		int id = sc.nextInt();
		repoActualite.delete(id, pseudo);
    }
}
