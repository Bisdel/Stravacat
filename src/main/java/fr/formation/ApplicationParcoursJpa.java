package fr.formation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import fr.formation.model.Parcours;
import fr.formation.repo.IParcoursRepository;
import fr.formation.repo.jpa.RepositoryParcoursJpa;

public class ApplicationParcoursJpa {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){

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
                for (Parcours parcours2 : parcours) {
                    System.out.println(parcours2);
                    }
                    
                }
            }
            
        catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
