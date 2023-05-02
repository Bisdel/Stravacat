package fr.formation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import fr.formation.config.AppConfig;
import fr.formation.factory.RepositoryFactory;
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
	public static void main(String[] args) {
		int choixMenu = 0;
		do {
			choixMenu = Menu.print();
			switch (choixMenu) {
			case 1: ListerVille();break;
			case 2: ListerAbonnes();break;			
			case 3: ListerAbonnement();break;
			case 4: AjouterVille();break;
			case 5: AjouterAbonnes();break;		
			case 6: AjouterAbonnement();break;	
			case 7: SupprimerVille();break;
			case 8: SupprimerAbonnes();break;
		    case 9: SupprimerAbonnement();break;
			}
		} while (choixMenu != 0);
		Saisie.sc.close();
	}
	
	@Autowired
	private IVilleRepository repoVille;
	
	static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);



	private static  void ListerVille() {
		IVilleRepository repoVille = context.getBean(IVilleRepository.class);
		for (Ville v : repoVille.findAll()) {
			System.out.println("le nom de la ville" + v.getNom() + " son ambiance " + v.getAmbiance());
		}
	}

	private static void ListerAbonnes() {
		IAbonnesRepository repoAbonne = context.getBean(IAbonnesRepository.class);
		for (Abonnes a : repoAbonne.findAll()) {
			System.out.println(" age : " + a.getAge() + " animal id : " + a.getAnimal_id() + " son id : " + a.getId()
					+ " espece : " + a.getEspece() + " pseudo :" + a.getPseudo() + " ville id : "
					+ a.getVille_id());
		}
	}
	private static void ListerAbonnement() {
		IAbonnementRepository repoAbonnement = context.getBean(IAbonnementRepository.class);
		for(Abonnement a : repoAbonnement.findAll()) {
			System.out.println(" age : " + a.getAge()+" animal id : "+ a.getAnimal_id() +" son id : " + a.getId()
			+" espece : "+a.getEspece()+" pseudo :" +a.getPseudo()
			+" ville id : "+a.getVille_id());
			
		}
		
	}
//	private static void AjouterVille() {
//		IVilleRepository repoVille = RepositoryFactory.creatVilleRepository();
//		repoVille.createEntry();
//	}
//
//	private static void AjouterAbonnes() {
//		IAbonnesRepository repoAbonne = RepositoryFactory.creatAbonnesRepository();
//		repoAbonne.createEntry();
//	}
//	
//
//	private static void AjouterAbonnement() {
//		IAbonnementRepository repoAbonnement = RepositoryFactory.creatAbonnementRepository();
//		repoAbonnement.createEntry();
//	}
//
//	private static void SupprimerVille() {
//		IVilleRepository repoVille = RepositoryFactory.creatVilleRepository();
//		repoVille.deleteAll();
//
//	}
//
//	private static void SupprimerAbonnes() {
//		IAbonnesRepository repoAbonne = RepositoryFactory.creatAbonnesRepository();
//		repoAbonne.deleteAll();
//
//	}
//	private static void SupprimerAbonnement() {
//		IAbonnementRepository repoAbonnement = RepositoryFactory.creatAbonnementRepository();
//		repoAbonnement.deleteAll();
		
	}


//}
