package fr.formation.repo.jpa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractRepositoryJpa {
    
    protected Connection connection;

    public AbstractRepositoryJpa() {
        
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/stravacat_db", "postgres", "0000");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    

}
