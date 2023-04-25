package fr.formation;

import fr.formation.model.Abonnes;
import fr.formation.model.Menu;
import fr.formation.model.Ville;
import fr.formation.model.Saisie;
import fr.formation.repo.IAbonnesRepository;
import fr.formation.repo.IVilleRepository;
import fr.formation.repo.sql.RepositoryAbonnesSql;
import fr.formation.repo.sql.RepositoryVilleSql;

public class ApplicationAbonVille {

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
		IVilleRepository repoVille = new RepositoryVilleSql();
		for (Ville q : repoVille.findAll()) {
			System.out.println("L'id du ville : " + q.getId() + " la ville : " + q.getVille()
					+ " son Ambinace est : " + q.getAmbiance());
		}
	}

	private static void ListerAbonnes() {
		IAbonnesRepository repoAbonnes = new RepositoryAbonnesSql();
		for (Abonnes a : repoAbonnes.findAll()) {
			System.out.println("L'id de l'abonne" + a.getId() + " l'id de l'animal abonne est " + a.getAnimal_id()
					+ " son pseudo " + a.getPseudo() + " son age " + a.getAge() + " nb_patounes " + a.getNb_patounes()
					+ " le ville de l'abonnes " + a.getVille_id());
		}
	}

	private static void AjouterVille() {
		IVilleRepository repoVille = new RepositoryVilleSql();
	    repoVille.createEntry();
	   
	}

	private static void AjouterAbonnes() {
		IAbonnesRepository repoAbonnes = new RepositoryAbonnesSql();
		repoAbonnes.createEntry();
	
	}

	private static void SupprimerVille() {
		IVilleRepository repoVille = new RepositoryVilleSql();
		repoVille.deleteEntry();
		

		}
	
	private static void SupprimerAbonnes() {
		IAbonnesRepository repoAbonnes = new RepositoryAbonnesSql();
		repoAbonnes.deleteEntry();
	
		}

	}


