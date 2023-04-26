package fr.formation.model;

public class MenuParcours {
    public static int print(){
        System.out.println("------MENU PARCOURS-----");
        System.out.println("--1. Montrer les parcours à une date de publication");
        System.out.println("--2. Lister toutes les publications avec un parcours");
        System.out.println("--3. Publier un parcours");
        System.out.println("--4. Supprimer un parcours");
        System.out.println("--5. Mettre à jour un parcours");
        System.out.println("-------------------------");
        return Saisie.nextInt("choisir le menu parcours");
    }
    
}
