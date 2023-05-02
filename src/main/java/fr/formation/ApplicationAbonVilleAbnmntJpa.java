package fr.formation;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import fr.formation.config.AppConfig;
import fr.formation.model.Abonnement;
import fr.formation.model.Abonnes;
import fr.formation.model.Menu;
import fr.formation.model.Saisie;
import fr.formation.model.Ville;
import fr.formation.repo.IAbonnementRepository;
import fr.formation.repo.IAbonnesRepository;
import fr.formation.repo.IVilleRepository;

@Component
public class ApplicationAbonVilleAbnmntJpa {
	public static Scanner sc = new Scanner(System.in);
	@Autowired
	private IVilleRepository repoVille;
	@Autowired
	private IAbonnementRepository repoAbonnement;
	@Autowired
	private IAbonnesRepository repoAbonne;

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		ApplicationAbonVilleAbnmntJpa app = context.getBean(ApplicationAbonVilleAbnmntJpa.class);
		app.run();
		context.close();
	}

	private void run() {
		int choixMenu = 0;
		do {
			choixMenu = Menu.print();
			switch (choixMenu) {
			case 1:ListerVille();break;
			case 2:ListerAbonnes();break;
			case 3:ListerAbonnement();break;
			case 4:AjouterVille();break;
			case 5:AjouterAbonnes();break;
			case 6:AjouterAbonnement();break;
			case 7:SupprimerVille();break;
			case 8:SupprimerAbonnes();break;
			case 9:SupprimerAbonnement();break;
			}
		} while (choixMenu != 0);
		Saisie.sc.close();
	}

	private void ListerVille() {
		System.out.println("---------------------");
		for (Ville v : repoVille.findAll()) {
			System.out.println("le nom de la ville " + v.getNom() + " son ambiance " + v.getAmbiance());
		}
	}

	private void ListerAbonnes() {
		System.out.println("---------------------");
		for (Abonnes a : repoAbonne.findAll()) {
			System.out.println(" age : " + a.getAge() + " animal id : " + a.getAnimal_id() + " son id : " + a.getId()
					+ " espece : " + a.getEspece() + " pseudo :" + a.getPseudo() + " ville id : " + a.getVille_id());
		}
	}

	private void ListerAbonnement() {
		System.out.println("---------------------");
		for (Abonnement a : repoAbonnement.findAll()) {
			System.out.println(" age : " + a.getAge() + " animal id : " + a.getAnimal_id() + " son id : " + a.getId()
					+ " espece : " + a.getEspece() + " pseudo :" + a.getPseudo() + " ville id : " + a.getVille_id());
		}
	}

	private void AjouterVille() {
		System.out.println("---------------------");
		
		System.out.println("Voulez vous ajouer une ville ? ");
		String saisie = Saisie.next("veillez selection o/n ");
		try {
		   if (saisie.toUpperCase().equals("O")) {
				Ville ville = new Ville();
				String nom = Saisie.next("Veuillez saisir le nom de la ville");
				String ambiance = Saisie.next("Veillez saisir l'ambiance de la ville");
				ville.setAmbiance(ambiance);
				ville.setNom(nom);
				repoVille.save(ville);
				System.out.println("la ville à été creé avec succé ! " );
			} else if (saisie.toUpperCase().equals("N")) {
				System.out.println(" vous allez être redirigé vers le menu principale ");
			}
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
	}

	private void AjouterAbonnes() {
		System.out.println("---------------------");
		System.out.println("Voulez vous ajouer un abonné ? ");
		String saisie = Saisie.next("veillez selection o/n ");
		try {
			if (saisie.toUpperCase().equals("O")) {
				Abonnes abonne = new Abonnes();
				int age = Saisie.nextInt(" Veuillez saisir l'age ");
				int animal_id = Saisie.nextInt(" l'id de l'anima ");
				String pseudo = Saisie.next(" veuillez saisir le pseudo ");
				int ville_id = Saisie.nextInt("veuillez saisir l'id de la ville ");
				String espece = Saisie.next("veuillez indiquez l'espece  ");

				abonne.setAge(age);
				abonne.setAnimal_id(animal_id);
				abonne.setPseudo(pseudo);
				abonne.setVille_id(ville_id);
				abonne.setEspece(espece);
				repoAbonne.save(abonne);
				System.out.println("l'abonne à été creé avec succé ! ");

			} else if (saisie.toUpperCase().equals("N")) {
				System.out.println(" vous allez être redirigé vers le menu principale ");
			}
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
	}

	private void AjouterAbonnement() {
		System.out.println("---------------------");
		System.out.println("Voulez vous ajouer un abonnement ? ");
		String saisie = Saisie.next("veillez selection o/n ");
		try {
			if (saisie.toUpperCase().equals("O")) {
				Abonnement abonnement = new Abonnement();
				int age = Saisie.nextInt(" Veuillez saisir l'age ");
				int animal_id = Saisie.nextInt(" l'id de l'anima ");
				String pseudo = Saisie.next(" veuillez saisir le pseudo ");
				int ville_id = Saisie.nextInt("veuillez saisir l'id de la ville ");
				String espece = Saisie.next("veuillez indiquez l'espece  ");

				abonnement.setAge(age);
				abonnement.setAnimal_id(animal_id);
				abonnement.setPseudo(pseudo);
				abonnement.setVille_id(ville_id);
				abonnement.setEspece(espece);
				repoAbonnement.save(abonnement);
				System.out.println("l'abonnement à été creé avec succé ! ");
			} else if (saisie.toUpperCase().equals("N")) {
				System.out.println(" vous allez être redirigé vers le menu principale ");
			}
		} catch (InputMismatchException e) {
			e.printStackTrace();
		}
	}

	private void SupprimerVille() {
		System.out.println("---------------------");
		System.out.println("Voulez vous supprimer une ville ? ");
		String saisie = Saisie.next("veillez selection o/n ");
		try {
			if (saisie.toUpperCase().equals("O")) {
				int id = Saisie.nextInt(" veuillz saisir l'id que vous voulez supprimer ! ");
				repoVille.deleteById(id);
				System.out.println(" l'id à été supprimé avec succé");
			} else if (saisie.toUpperCase().equals("N")) {
				System.out.println(" vous allez être redirigé vers le menu principale ");
			}

		} catch (NumberFormatException n) {
			n.printStackTrace();
		}
	}

	private void SupprimerAbonnes() {
		System.out.println("---------------------");
		System.out.println("Voulez vous supprimer une ville ? ");
		String saisie = Saisie.next("veillez selection o/n ");
		try {
			if (saisie.toUpperCase().equals("O")) {
				int id = Saisie.nextInt(" veuillz saisir l'id que vous voulez supprimer ! ");
				repoAbonne.deleteById(id);
				System.out.println(" l'id à été supprimé avec succé ");
			} else if (saisie.toUpperCase().equals("N")) {
				System.out.println(" vous allez être redirigé vers le menu principale ");
			}

		} catch (NumberFormatException n) {
			n.printStackTrace();
		}
	}

	private void SupprimerAbonnement() {
		String saisie = Saisie.next("veillez selection o/n ");
		try {
		
			if (saisie.toUpperCase().equals("O")) {
				int id = Saisie.nextInt(" veuillz saisir l'id que vous voulez supprimer ! ");
				repoAbonnement.deleteById(id);
				System.out.println(" l'id à été supprimé avec succé ");
			} else if (saisie.toUpperCase().equals("N")) {
				System.out.println(" vous allez être redirigé vers le menu principale ");
			}

		} catch (NumberFormatException n) {
			n.printStackTrace();
		}
	}

}
