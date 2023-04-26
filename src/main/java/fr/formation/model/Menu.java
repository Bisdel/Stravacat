package fr.formation.model;

public class Menu {
	public static int print() {
		System.out.println("--------------------------");
		System.out.println("-- 1. Lister les villes");

		System.out.println("-- 2. Lister abonnes");

		System.out.println("-- 3. Lister abonnements");
		
		System.out.println("-- 4. ajout d'un ville ");

		System.out.println("-- 5. ajout des abonnes");
		
		System.out.println("-- 6. ajout un abonnement ");
		
		System.out.println("-- 7. supprimer une ville ");
		
		System.out.println("-- 8. supprimer un abonne ");
		
		System.out.println("-- 9. supprimer un abonnement ");

		System.out.println("-- 0. Quitter");

		System.out.println("--------------------------");

		return Saisie.nextInt("choisir le menu");

	}
}
