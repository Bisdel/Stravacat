package fr.formation.model;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import fr.Stravacat.sql.RepositoryAbonnesSql;
import fr.Stravact.repo.IAbonnesRepository;

public class ApplicationAbonnes {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<Abonnes> abonne = new ArrayList<>();
		IAbonnesRepository repoAbonnes = new RepositoryAbonnesSql();

		System.out.println(" --- Test findAll() --- ");
		for (Abonnes a : repoAbonnes.findAll()) {

			System.out.println(" Son age est de " + a.getAge() + " id animal est le " + a.getAnimal_id()
					+ " , l'id de l'abonne est le " + a.getId() + " nombre de patounes " + a.getNb_patounes()
					+ " son pseudo " + a.getPseudo() + " le quartier " + a.getQuartier_id());

		}

		System.out.println("-------------");
		System.out.println("---- Test optonel ----");
		
   
    				System.out.println("veillez saisir l'id que vous chercher");
    				int id = sc.nextInt();

    				Optional<Abonnes> optioAbonnes = repoAbonnes.findByid(id);
    				if (optioAbonnes.isPresent()) {
    					System.out.println(optioAbonnes.get().getPseudo());
    				} else {
    					System.out.println("l'abonnes  n'existe pas ");
    				
    				
    		   }
	
		System.out.println("-------------------------");
		System.out.println("---- Test save ----");
		System.out.println(" Creer un nouveau Abonne ");

		System.out.println("veuillez saisr le age de l'abonne ");
		int age = sc.nextInt();
		System.out.println("veuillez saisr le nb_patte de l'abonne ");
		int abon_nb_patounes = sc.nextInt();
		System.out.println("veuillez saisr le l'id du quartier de l'abonne ");
		int id_quartier_id = sc.nextInt();
		System.out.println("veuillez saisr le pseudo de l'abonne ");
		String pseudo = sc.next();
		System.out.println("veuillez saisr l'id de l'anim qui est de l'abonne ");
		int id_animal_id = sc.nextInt();

		Abonnes abonnes = new Abonnes();
		abonnes.setPseudo(pseudo);
		abonnes.setAge(age);
		abonnes.setNb_patounes(abon_nb_patounes);
		abonnes.setQuartier_id(id_quartier_id);
		abonnes.setAnimal_id(id_animal_id);
		repoAbonnes.save(abonnes);
		for (Abonnes a : repoAbonnes.findAll()) {
			System.out.println(a.getAge());
		}

			
			System.out.println("-------------------");
                 System.out.println("---- Test delet ----");			
			
                 try { System.out.println("veuillez saisir l'id a supprimer ");
                 int id1 = sc.nextInt();
                 repoAbonnes.deletedById(id1);
                 System.out.println("l'abonne #" + id1 +" a ete supprimer avec succé " );
                 for(Abonnes q : repoAbonnes.findAll()) {
         			System.out.println("l'id " + q.getId()+ " age " + q.getAge()+ " pseudo " + q.getPseudo());
                 
                 } } catch(InputMismatchException ex) {
                 	System.out.println("Mauvaise saisie !");
			
                 }
			
			
			
		
    sc.close();
    }
	

	private static boolean optioAbonnes() {
		return false;
	}

}
