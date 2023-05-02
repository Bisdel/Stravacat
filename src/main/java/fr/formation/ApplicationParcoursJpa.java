package fr.formation;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import fr.formation.config.AppConfig;
import fr.formation.model.MenuParcours;
import fr.formation.model.Parcours;
import fr.formation.model.Saisie;
import fr.formation.repo.IParcoursRepository;
import fr.formation.repo.jpa.RepositoryParcoursJpa;

@Component
public class ApplicationParcoursJpa {
    public static Scanner sc = new Scanner(System.in);

    @Autowired
    private IParcoursRepository repoParcours;


    public static void main(String[] args){

            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);   
            ApplicationParcoursJpa app = context.getBean(ApplicationParcoursJpa.class);
            app.run();
            
            context.close();
    }

    public void run(){
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
        
    private void MajParcours() {
        System.out.println("-- Quel modification souhaitez vous faire ? --");
        System.out.println("ville - temps - trace");
        try{
            String modif = sc.nextLine();
            System.out.println("-- Veuillez entrer l'ID du parcours :");
            int idModif = sc.nextInt();
            sc.nextLine();
            Parcours parcours = repoParcours.findById(idModif).get();

            if (modif.equals("ville")){
                
                System.out.println("--Entrez le nouveau nom de la ville : ");
                String villeModif = sc.nextLine();
                String villeAncienne = parcours.getVilleParcours();
                parcours.setVilleParcours(villeModif);
                repoParcours.save(parcours);
                System.out.println("--> La ville : "+ villeAncienne +" a été mise à jour, votre parcours se situe désormarais à : " + villeModif);
                
            }

            if(modif.equals("temps")){
                System.out.println("-- Veuillez entrer le temps de votre parcours sous le format 'HH:mm:ss' : ");
                String tempsModif = sc.nextLine();
                Time tempsAncien = parcours.getTempsParcours();
                Time temps = Time.valueOf(tempsModif);
                parcours.setTempsParcours(temps);
                repoParcours.save(parcours);
                System.out.println("--> Vous avez changé votre temps de parcours initiale de : " + tempsAncien + ". Le nouveau temps affiché est désormais de : " + temps);

            }

            if(modif.equals("trace")){
                System.out.println("Rien à modifier pour l'instant ;) ");
            }

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void SuppressionParcours() {
        ListerParcours();
        System.out.println("------------------------------------------------");
        System.out.println("-- Quel ID de parcours souhaitez vous supprimer ? ");
        int suppIdParcours = sc.nextInt();
        repoParcours.deleteById(suppIdParcours);
        System.out.println("** L'ID " + suppIdParcours + " ** du Parcours a bien été supprimé " );
        
    }

    private void PublicationParcours() {
        Parcours newParcours = new Parcours();
        System.out.println("-- Veuillez entrez le nom de la ville dans laquelle vous souhaitez ajouter votre parcours : ");
        String newVille = sc.nextLine();
        System.out.println("-- Veuillez entrer votre temps de parcours sous le format suivant 'HH:mm:ss' : ");
        String newTemps = sc.nextLine();
        Time temps = Time.valueOf(newTemps);
        LocalDateTime newDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        newParcours.setVilleParcours(newVille);
        newParcours.setTempsParcours(temps);
        newParcours.setDatePublicationParcours(newDate);
       // ajouter le save sinon ça va dans le vent
        System.out.println("-- Votre parcours à bien été ajouté le " + newDate.toLocalDate().format(formatter) + " à " + newVille + " avec un temps de parcours de : " + newTemps);
    }

    private void ListerParcours() {
        System.out.println("-- Voici la liste des parcours effectué : ");
        for(Parcours p : repoParcours.findAll()){
            System.out.println("--" + p.getId()+ "-- " + p.getAnimal() + " a publié le " + p.getDatePublicationParcours() + " à " + p.getVilleParcours() + " avec un temps de parcours de " + p.getTempsParcours());
            //System.out.println(p.getAnimal() + " a publié le " + p.getDatePublicationParcours() + " à " + p.getVilleParcours() + " avec un temps de parcours de " + p.getTempsParcours() + " | TRACE GPS | " + p.getTraceGpsParcours());
        }
    }

    private void DateParcours() {


        System.out.println("-- Rechercher un Parcours par sa date de publication : ");
        System.out.println("-- Entrez une date de publication sous la forme 'dd/MM/yyyy' : ");
        try{
            String date = sc.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(date, formatter);
            LocalDateTime start = localDate.atStartOfDay();
            LocalDateTime end = start.plusDays(1);

            List<Parcours> parcours = repoParcours.findByDateParcours(start, end);

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
