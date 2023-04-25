package fr.formation.repo.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractRepositorySql {
    
    protected Connection connection;

    public AbstractRepositorySql() {
        
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/stravacat_db", "postgres", "0000");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    

}
