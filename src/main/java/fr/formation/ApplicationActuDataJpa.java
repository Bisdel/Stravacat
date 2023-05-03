package fr.formation;

import java.sql.PseudoColumnUsage;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
		Animal animal = new Animal();
		Ville ville = new Ville();

        System.out.println("Entrer le pseudo de l'animal");
        String pseudo = sc.nextLine();
        if (repoAnimal.findByPseudo(pseudo).isPresent()) {
            actualite.setAnimal(repoAnimal.findByPseudo(pseudo).get());

			System.out.println("Entrer le nom de la ville");
			String nomVille = sc.nextLine();
			if (repoVille.findByNom(nomVille).isPresent()) {
				actualite.setVille(repoVille.findByNom(nomVille).get());
			}
			else {
				System.out.println("Ville inexistante");
				ville.setNom(nomVille);
				System.out.println("Entrer l'ambiance de la ville");
				ville.setAmbiance(sc.nextLine());
				repoVille.save(ville);
				actualite.setVille(repoVille.findByNom(nomVille).get());
			}
			animal.setVille(ville);
        }
        else {
			System.out.println("Pseudo inexistant");
			animal.setPseudo(pseudo);
			System.out.println("Quel est l'espèce de ce nouvel animal ?");
			animal.setEspece(sc.nextLine());
			System.out.println("Entrer un mot de passe");
			animal.setPassword(sc.nextLine());
			System.out.println("Quel âge a ce nouvel animal ?");
			animal.setAge(sc.nextInt());
			sc.nextLine();

			System.out.println("Entrer le nom de la ville");
			String nomVille = sc.nextLine();
			if (repoVille.findByNom(nomVille).isPresent()) {
				actualite.setVille(repoVille.findByNom(nomVille).get());
			}
			else {
				System.out.println("Ville inexistante");
				ville.setNom(nomVille);
				System.out.println("Entrer l'ambiance de la ville");
				ville.setAmbiance(sc.nextLine());
				repoVille.save(ville);
				actualite.setVille(repoVille.findByNom(nomVille).get());
			}
			animal.setVille(ville);

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
		System.out.println("Actualité créée avec succés :\n" + actualite);
	}

    private void afficherActualite() {
		try {
            System.out.println("-------------------------------");
			System.out.println("Saisir le pseudo de l'animal pour afficher son fil d'actualité");
			String pseudo = sc.nextLine();
			
			if (repoAnimal.findByPseudo(pseudo).isPresent())  {
				Animal animal = repoAnimal.findByPseudo(pseudo).get();
				if (repoActualite.findByActuAnimalId(animal.getId()).isEmpty()) {
					System.out.println(pseudo + " : le fil d'actu est vide");
				}
				else
					for (Actualite a : repoActualite.findByActuAnimalId(animal.getId())) {
						System.out.println(a);
					}	
			}
			else {
				System.out.println("Erreur : pseudo " + pseudo + " inexistant");
			}		
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
    }

    private void modifierActualite() {	
        try{
			System.out.println("-------------------------------");
			System.out.println("Saisir le pseudo de l'animal pour afficher son fil d'actualité");
			String pseudo = sc.nextLine();
			
			if (repoAnimal.findByPseudo(pseudo).isPresent())  {
				Animal animal = repoAnimal.findByPseudo(pseudo).get();
				if (repoActualite.findByActuAnimalId(animal.getId()).isEmpty()) {
					System.out.println(pseudo + " : le fil d'actu est vide");
				}
				else {
					for (Actualite a : repoActualite.findByActuAnimalId(animal.getId())) {
						System.out.println(a);
					}

					System.out.println("Sélectionner l'id de l'actualité à modifier");
					int idActu = sc.nextInt();
					sc.nextLine();
					Actualite actualite = repoActualite.findById(idActu).get();

					System.out.println("Modifications : description / confidentialite / ville ?");
					int choix = 0;	
					do {
						System.out.println("-------------------------------");
						
						System.out.println("-- 1. Modifier la description");
						System.out.println("-- 2. Modififier la confidentialité");
						System.out.println("-- 3. Modifier la ville");
						System.out.println("-- 0. Quitter");
						
						System.out.println("-------------------------------");		
						System.out.println("Selectionner une action");
						
						choix = sc.nextInt();
						sc.nextLine();

						if (choix == 1) {
							System.out.println("Saisir la nouvelle description");
							String newDescription = sc.nextLine();
							actualite.setActu_description(newDescription);
							
							System.out.println("Modification validée : \n" + newDescription);
						}
						else if (choix == 2) {
							System.out.println("Saisir la nouvelle confidentialite privée('true') ou publique('false')");
							boolean newConfidentialite = sc.nextBoolean();
							actualite.setActu_isPrivate(newConfidentialite);

							System.out.println("Modification confidentialité validée en " + newConfidentialite);
						}
						else if (choix == 3) {
							System.out.println("Saisir la nouvelle ville");
							Ville ville = new Ville();

							String nomVille = sc.nextLine();
							if (repoVille.findByNom(nomVille).isPresent()) {
								actualite.setVille(repoVille.findByNom(nomVille).get());
							}
							else {
								System.out.println("Ville inexistante");
								ville.setNom(nomVille);
								System.out.println("Entrer l'ambiance de la ville");
								ville.setAmbiance(sc.nextLine());
								repoVille.save(ville);
								actualite.setVille(repoVille.findByNom(nomVille).get());
							}

							System.out.println("Modification de la ville validée en " + nomVille);
						}
						actualite.setActu_timestamp(LocalDateTime.now());
						repoActualite.save(actualite);
						System.out.println(actualite);

					} while (choix != 0);						
				}	
			}
			else {
				System.out.println("Erreur : pseudo " + pseudo + " inexistant");
			}
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void supprimerActualite() {
        System.out.println("-------------------------------");
        System.out.println("Suppression d'une actualité d'un animal");
		afficherActualite();
		System.out.println("-------------------------------");
		System.out.println("Saisir l'id de l'actualité à supprimer");
		int id = sc.nextInt();
		if (repoActualite.findById(id).isPresent()) {
			repoActualite.deleteById(id);
			System.out.println("Actualité supprimée avec succès !");
		}
		else {
			System.out.println("Actualité introuvable ou inexistante !");
		}
    }
}