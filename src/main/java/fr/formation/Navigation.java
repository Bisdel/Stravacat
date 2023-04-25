package fr.formation;
import java.util.InputMismatchException;

public class Navigation {
    
    public static boolean choixOuiNon() {
        while(true){
            try {
                System.out.println("Veuillez entrer votre choix (O/N) :");
                String choix = ApplicationAnimalSql.sc.nextLine();
                if (choix.toUpperCase().equals("O")) {
                    return true;
                }
                else if (choix.toUpperCase().equals("N")) {
                    return false;
                }
                else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Vous n'avez pas entré un choix valide.");
            }
        }
    }
}
