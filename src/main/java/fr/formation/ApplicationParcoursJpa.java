package fr.formation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import fr.formation.model.MenuParcours;
import fr.formation.model.Parcours;
import fr.formation.model.Saisie;
import fr.formation.repo.IParcoursRepository;
import fr.formation.repo.jpa.RepositoryParcoursJpa;

public class ApplicationParcoursJpa {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
        int choixMenu = 0;
        do{
            choixMenu = MenuParcours.print();
            switch(choixMenu){
                case 1 : DateParcours();break;
                case 2 : ListerParcours();break;
                case 3 : PublicationParcours();break;
                case 4 : SuppressionParcours();break;
                case 5 : MajParcours();break;
            } 
        }while (choixMenu != 0);
            Saisie.sc.close();

        }
        
    private static void MajParcours() {
        IParcoursRepository repoParcoursJpa = new RepositoryParcoursJpa();
        System.out.println("-- Quel modification souhaitez vous faire ? --");
        System.out.println("ville - temps - trace");
        try{
            String modif = sc.nextLine();
            System.out.println("-- Veuillez entrer votre ID :");
            String idModif = sc.nextLine();
            if (modif == "ville" ){
                String chgtVille = getVilleParcours().getId(idModif);
                System.out.println("Entrez le nouveau nom de la ville : ");
                
            }

        }
    }

    private static void SuppressionParcours() {
    }

    private static void PublicationParcours() {
    }

    private static void ListerParcours() {
        IParcoursRepository repoParcoursJpa = new RepositoryParcoursJpa();
        System.out.println("-- Voici la liste des parcours effectué : ");
        for(Parcours p : repoParcoursJpa.findAll()){
            System.out.println(p.getAnimal() + " a publié le " + p.getDatePublicationParcours() + " à " + p.getVilleParcours() + " avec un temps de parcours de " + p.getTempsParcours());
            //System.out.println(p.getAnimal() + " a publié le " + p.getDatePublicationParcours() + " à " + p.getVilleParcours() + " avec un temps de parcours de " + p.getTempsParcours() + " | TRACE GPS | " + p.getTraceGpsParcours());
        }
    }

    private static void DateParcours() {
        IParcoursRepository repoParcoursJpa = new RepositoryParcoursJpa();

        System.out.println("-- Rechercher un Parcours par sa date de publication : ");
        System.out.println("-- Entrez une date de publication sous la forme 'dd/MM/yyyy' : ");
        try{
            String date = sc.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(date, formatter);
            LocalDateTime start = localDate.atStartOfDay();

            List<Parcours> parcours = repoParcoursJpa.findByDateParcours(start);

            if (parcours.isEmpty()){
                System.out.println("Aucun parcours trouvé à la date du " + date + " !");
                
                }
            else{
                System.out.println("Voici la liste des parcours effectués le " + date + " :");
                for (Parcours p : parcours) {
                    System.out.println(p.getAnimal() + " a publié un parcours le : "+ p.getDatePublicationParcours() + " à " + p.getVilleParcours());
                    }
                    
                }
            }
            
        catch (Exception ex){
            ex.printStackTrace();
        }

    }

        
}
