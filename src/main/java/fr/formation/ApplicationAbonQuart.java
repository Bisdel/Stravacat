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
	    repoQuartier.createEntry();
	   
	}

	private static void AjouterAbonnes() {
		IAbonnesRepository repoAbonnes = new RepositoryAbonnesSql();
		repoAbonnes.createEntry();
	
	}

	private static void SupprimerQuartier() {
		IQuartierRepository repoQuartier = new RepositoryQuartierSql();
		repoQuartier.deleteEntry();
		

		}
	
	private static void SupprimerAbonnes() {
		IAbonnesRepository repoAbonnes = new RepositoryAbonnesSql();
		repoAbonnes.deleteEntry();
	
		}

	}


