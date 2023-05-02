package fr.formation;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import fr.formation.config.AppConfig;
import fr.formation.model.Animal;
import fr.formation.repo.IAnimalRepository;

@Component
public class ApplicationAnimalAdminJpa {

    public static Scanner scadmin = new Scanner(System.in);

    @Autowired
    private IAnimalRepository repoAnimal;

    public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		ApplicationAnimalAdminJpa app = context.getBean(ApplicationAnimalAdminJpa.class);
		app.run();
		
		context.close();
	}
	
	public void run() {

            System.out.println("\nVous avez lancé le programme avec les droits d'administrateur.");

            System.out.println("\n-- Affichage des animaux présents sur le site :");
            List<Animal> animaux = repoAnimal.findAll();
            for (Animal animal : animaux) {
                System.out.println(
                        "Animal #" + animal.getId() + " : " + animal.getPseudo() + ", " + animal.getAge() + " ans.");
            }

            System.out.println("\nVoulez-vous supprimer un animal ?");

            if (Navigation.choixOuiNon()) {
                boolean saisieValide = false;
                while (saisieValide == false) {
                    System.out.println("Entrez le pseudo à supprimer :");
                    String pseudo = ApplicationAnimalAdminJpa.scadmin.nextLine();
                    repoAnimal.delete(repoAnimal.findByPseudo(pseudo).get());
                    System.out.println("L'animal a bien été supprimé de la base de données.");
                    saisieValide = true;
                }
            }

        System.out.println("Merci d'être passé Monsieur l'Administrateur, ça fait toujours plaisir !\nA bientôt !");
    }
}
