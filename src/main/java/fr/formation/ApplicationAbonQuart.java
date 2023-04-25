package fr.formation;

import fr.formation.model.Abonnes;
import fr.formation.model.Menu;
import fr.formation.model.Quartier;
import fr.formation.model.Saisie;
import fr.formation.repo.IAbonnesRepository;
import fr.formation.repo.IQuartierRepository;
import fr.formation.repo.sql.RepositoryAbonnesSql;
import fr.formation.repo.sql.RepositoryQuartierSql;

public class ApplicationAbonQuart {

	public static void main(String[] args) {
		int choixMenu = 0;
		do {
			choixMenu = Menu.print();
			switch (choixMenu) {
			case 1:
				ListerQuartier();
				break;
			case 2:
				ListerAbonnes();
				break;
			case 3:
				AjouterQuartier();
				break;
			case 4:
				AjouterAbonnes();
				break;
			case 5:
				SupprimerQuartier();
				break;
			case 6:
				SupprimerAbonnes();
				break;
			}
		} while (choixMenu != 0);
		Saisie.sc.close();
	}

	private static void ListerQuartier() {
		IQuartierRepository repoQuartier = new RepositoryQuartierSql();
		for (Quartier q : repoQuartier.findAll()) {
			System.out.println("L'id du quartier : " + q.getId() + " la ville : " + q.getVille()
					+ " son Ambinace est : " + q.getAmbiance());
		}
	}

	private static void ListerAbonnes() {
		IAbonnesRepository repoAbonnes = new RepositoryAbonnesSql();
		for (Abonnes a : repoAbonnes.findAll()) {
			System.out.println("L'id de l'abonne" + a.getId() + " l'id de l'animal abonne est " + a.getAnimal_id()
					+ " son pseudo " + a.getPseudo() + " son age " + a.getAge() + " nb_patounes " + a.getNb_patounes()
					+ " le quartier de l'abonnes " + a.getQuartier_id());
		}
	}

	private static void AjouterQuartier() {
		IQuartierRepository repoQuartier = new RepositoryQuartierSql();
		System.out.println(" Creer un nouveau quartier ");
		Quartier quartier = new Quartier();
		String ville = Saisie.next("le nom de la ville :");
		String ambiance = Saisie.next(" l'ambiance : ");
		quartier.setVille(ville);
		quartier.setAmbiance(ambiance);
		repoQuartier.updateEntry(quartier);
		System.out.println(quartier.getId() + " " + quartier.getAmbiance() + " " + quartier.getVille());
	}

	private static void AjouterAbonnes() {
		IAbonnesRepository repoAbonnes = new RepositoryAbonnesSql();
		System.out.println(" Creer un nouveau Abonne ");
		Abonnes abonnes = new Abonnes();
		System.out.println("--------------------------------");
		int id_animal = Saisie.nextInt("L'Id de l'animal: ");
		int age = Saisie.nextInt("Son age : ");
		String pseudo = Saisie.next("Son pseudo :");
		int nb_patounes = Saisie.nextInt("nb_patounes : ");
		int id_quartier = Saisie.nextInt(" l'Id du quartier : ");
		abonnes.setAnimal_id(id_animal);
		abonnes.setAge(age);
		abonnes.setPseudo(pseudo);
		abonnes.setNb_patounes(nb_patounes);
		abonnes.setQuartier_id(id_quartier);
		repoAbonnes.updateEntry(abonnes);
		System.out.println("L'id de l'abonne" + abonnes.getId() + " l'id de l'animal abonne est "
				+ abonnes.getAnimal_id() + " son pseudo " + abonnes.getPseudo() + " son age " + abonnes.getAge()
				+ " nb_patounes " + abonnes.getNb_patounes() + " le quartier de l'abonnes " + abonnes.getQuartier_id());
	}

	private static void SupprimerQuartier() {
		IQuartierRepository repoQuartier = new RepositoryQuartierSql();
		System.out.println("Veuillez sasir l'Id que vous voulez supprimer ");
		int id = Saisie.nextInt("son Id");
		repoQuartier.deleteEntry();
		System.out.println("l'Id # " + (id) + " à été supprimer avec succé ! ");
		for (Quartier q : repoQuartier.findAll()) {
			System.out.println(
					" l'id est " + q.getId() + " l'ambiance " + q.getAmbiance() + " la ville  " + q.getVille());

		}

	}

	private static void SupprimerAbonnes() {
		IAbonnesRepository repoAbonnes = new RepositoryAbonnesSql();
		System.out.println(" qu'elle abonne vous voulez supprimer !");
		repoAbonnes.deleteEntry();
		for (Abonnes a : repoAbonnes.findAll()) {
			System.out.println("l'Id " + a.getId() + " l'Id de l'animal abonne " + a.getAnimal_id() + " son pseudo "
					+ a.getPseudo() + " son age " + a.getAge() + " c'est nombre de patte " + a.getNb_patounes()
					+ " l'Id du cartier " + a.getQuartier_id());
		}

	}

}
