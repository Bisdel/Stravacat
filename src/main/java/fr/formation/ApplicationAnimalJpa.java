package fr.formation;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import fr.formation.config.AppConfig;
import fr.formation.model.Animal;
import fr.formation.model.Saisie;
import fr.formation.model.Ville;
import fr.formation.repo.IAnimalRepository;
import fr.formation.repo.IVilleRepository;


@Component
public class ApplicationAnimalJpa {
    public static Scanner sc = new Scanner(System.in);

    @Autowired
    private IAnimalRepository repoAnimal;

    @Autowired
    private IVilleRepository repoVille;

    public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		ApplicationAnimalJpa app = context.getBean(ApplicationAnimalJpa.class);
		app.run();
		
		context.close();
	}
	
	public void run() {

        System.out.println("\nBienvenue sur Stravacat, le réseau social de vos compagnons à poils préférés !");
        System.out.println("\n-- Rechercher un animal par son pseudo :\nEntrez un pseudo :");
        String pseudo = sc.nextLine();

        if (repoAnimal.findByPseudo(pseudo).isPresent()) {
            Animal animal = repoAnimal.findByPseudo(pseudo).get();
            System.out.println("Animal trouvé !\nIl s'appelle " + animal.getPseudo() + ", il a "
                    + animal.getAge() + " ans et c'est un/une " + animal.getEspece() + ".");
            System.out.println("Voulez-vous mettre à jour une de ces informations ?");
            if (Navigation.choixOuiNon()){
                boolean saisieValide = false;
                while (saisieValide == false) {
                    System.out.println(
                            "\nVeuillez entrer les informations à modifier, au format champ(pseudo/motdepasse/age/espece/ville) - donnée, exemple : pseudo - Franklinlatortue)");
                    String infos = ApplicationAnimalJpa.sc.nextLine();
                    try {
                        if (infos.startsWith("pseudo - ")) {
                            animal.setPseudo(String.format(infos.split("pseudo - ")[1]));
                            saisieValide = true;
                        } else if (infos.startsWith("motdepasse - ")) {
                            animal.setPassword(String.format(infos.split("motdepasse - ")[1]));
                            saisieValide = true;
                        } else if (infos.startsWith("age - ")) {
                            animal.setAge(Integer.parseInt(infos.split("age - ")[1]));
                            saisieValide = true;
                        } else if (infos.startsWith("espece - ")) {
                            animal.setEspece(String.format(infos.split("espece - ")[1]));
                            saisieValide = true;
                        } else if (infos.startsWith("ville - ")) {
                            if (repoVille.findByNom(String.format(infos.split("ville - ")[1])).isPresent()) {
                                animal.setVille(repoVille.findByNom(String.format(infos.split("ville - ")[1])).get());
                                saisieValide = true;
                            } else {
                                System.out.println("La ville entrée n'existe pas encore, mais nous allons la créer ensemble !");
                                Ville ville = new Ville();
                                String nom = Saisie.next("Veuillez entrer le nom de votre ville :");
                                String ambiance = Saisie.next("Veuillez entrer l'ambiance de la ville :");
                                ville.setNom(nom);
                                ville.setAmbiance(ambiance);
                                System.out.println("Votre nouvelle ville a bien été créée !");
                                animal.setVille(repoVille.findByNom(String.format(infos.split("ville - ")[1])).get());
                                saisieValide = true;
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(
                                "\nUne erreur s'est glissée dans votre saisie. Veuillez bien respecter le format de saisie des données à modifier :)");
                    }
                repoAnimal.save(animal);    
                System.out.println("Les infos de votre animal ont bien été modifiées.");
                }
            }
        } else {
            System.out.println("Animal non trouvé :(\nVoulez-vous créer un nouvel animal ?");
            if (Navigation.choixOuiNon()) {
                System.out.println(
                    "Veuillez entrer les informations de votre nouvel animal, au format pseudo - motdepasse - age - espece - ville");
                Animal animal = new Animal();
                try {
                    String infos = ApplicationAnimalJpa.sc.nextLine();
                    animal.setPseudo(String.format(infos.split(" - ")[0]));
                    animal.setPassword(String.format(infos.split(" - ")[1]));
                    animal.setAge(Integer.parseInt(infos.split(" - ")[2]));
                    animal.setEspece(String.format(infos.split(" - ")[3]));
                    String nomVille = String.format(infos.split(" - ")[4]);
                    if (repoVille.findByNom(nomVille).isPresent()) {
                        animal.setVille(repoVille.findByNom(nomVille).get());
                    } else {
                        System.out.println("La ville entrée n'existe pas encore, mais nous allons la créer ensemble !");
                        Ville ville = new Ville();
                        String nom = Saisie.next("Veuillez entrer le nom de votre ville :");
                        String ambiance = Saisie.next("Veuillez entrer l'ambiance de la ville :");
                        ville.setNom(nom);
                        ville.setAmbiance(ambiance);
                        repoVille.save(ville);
                        System.out.println("Votre nouvelle ville a bien été créée !");
                        animal.setVille(repoVille.findByNom(nomVille).get());
                    }
        
                } catch (NumberFormatException e) {
                    System.out.println("Le format de l'age et/ou de l'espece est incorrect.");
                }
                repoAnimal.save(animal);
                System.out.println("Votre animal a bien été créé !");
            }
        }
    }
}
