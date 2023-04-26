package fr.formation;

import fr.formation.factory.RepositoryFactory;
import fr.formation.model.Abonnes;
import fr.formation.model.Menu;

import fr.formation.model.Saisie;
import fr.formation.model.Ville;
import fr.formation.repo.IAbonnesRepository;
import fr.formation.repo.IVilleRepository;

public class ApplicationAbonVilleJpa {

	public static void main(String[] args) {
		int choixMenu = 0;
		do {
			choixMenu = Menu.print();
			switch (choixMenu) {
			case 1:
				ListerVille();
				break;
			case 2:
				ListerAbonnes();
				break;
			case 3:
				AjouterVille();
				break;
			case 4:
				AjouterAbonnes();
				break;
			case 5:
				SupprimerVille();
				break;
			case 6:
				SupprimerAbonnes();
				break;
			}
		} while (choixMenu != 0);
		Saisie.sc.close();
	}

	private static void ListerVille() {
		IVilleRepository repoVille = RepositoryFactory.creatVilleRepository();
		for (Ville v : repoVille.findAll()) {
			System.out.println("le nom de la ville" + v.getNom() + " son ambiance " + v.getAmbiance());
		}
	}

	private static void ListerAbonnes() {
		IAbonnesRepository repoAbonne = RepositoryFactory.creatAbonnesRepository();
		for (Abonnes a : repoAbonne.findAll()) {
			System.out.println(" age : " + a.getAge() + " animal id : " + a.getAnimal_id() + " son id " + a.getId()
					+ " nb_patounes " + a.getNb_patounes() + " pseudo :" + a.getPseudo() + " ville id : "
					+ a.getVille_id());
		}
	}

	private static void AjouterVille() {
		IVilleRepository repoVille = RepositoryFactory.creatVilleRepository();
		repoVille.createEntry();
	}

	private static void AjouterAbonnes() {
		IAbonnesRepository repoAbonne = RepositoryFactory.creatAbonnesRepository();
		repoAbonne.createEntry();
	}

	private static void SupprimerVille() {
		IVilleRepository repoVille = RepositoryFactory.creatVilleRepository();
		repoVille.deleteEntry();

	}

	private static void SupprimerAbonnes() {
		IAbonnesRepository repoAbonne = RepositoryFactory.creatAbonnesRepository();
		repoAbonne.deleteEntry();

	}

}
