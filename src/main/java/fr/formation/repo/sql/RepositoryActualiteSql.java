package fr.formation.repo.sql;

import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.formation.ApplicationActu;
import fr.formation.model.Actualite;
import fr.formation.repo.IActualiteRepository;

public class RepositoryActualiteSql extends AbstractRepositorySql implements IActualiteRepository {

	@Override
	public List<Actualite> findAll() {
		List<Actualite> actualites = new ArrayList<>();
		try {
			String query = "SELECT * FROM actualite";
			PreparedStatement statement = this.connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				actualites.add(this.map(result));
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		return actualites;
	}

	// @Override
	// public Optional<Actualite> findById(int id) {
	// 	
	// return Optional.empty();
	// }

	@Override
	public void createEntry() {
        Actualite actualite = new Actualite();
		try {
			if (actualite.getActu_id() == 0) {
				String query = "INSERT INTO actualite (actu_animal_id, actu_quartier_id, actu_description, actu_isprivate, actu_timestamp) VALUES (?, ?, ?, ?, ?)";
				PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				statement.setInt(1, actualite.getActu_animal_id());
				statement.setInt(2, actualite.getActu_quartier_id());
				statement.setString(3, actualite.getActu_description());
				statement.setBoolean(4, actualite.getActu_isPrivate());
				statement.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
				statement.executeUpdate();
				
				ResultSet result = statement.getGeneratedKeys();
				
				if (result.next()) {
					actualite.setActu_id(result.getInt(1));
				}
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public List<Actualite> findByAnimalId(int animalId) {
		List<Actualite> actualites = new ArrayList<>();
		try  {
			String query = "SELECT * FROM actualite WHERE actu_animal_id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, animalId);
			
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				actualites.add(this.map(result));
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		return actualites;
	}
	
    @Override
	public void updateEntry(Actualite actualite) {
        try {
            System.out.println("Saisis l'id de l'actualité à modifier l'ancien");
            int id = ApplicationActu.sc.nextInt();

            int actuanimalId = ApplicationActu.sc.nextInt();
            int actuquartierId = ApplicationActu.sc.nextInt();
            String actudescription = ApplicationActu.sc.nextLine();
            boolean actuconfid = ApplicationActu.sc.nextBoolean();
		
			String query = "UPDATE actualite SET actu_animal_id = ?, actu_quartier_id = ?, actu_description = ?, actu_isprivate = ? , actu_date = ? WHERE actu_id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, actuanimalId);
			statement.setInt(2, actuquartierId);
			statement.setString(3, actudescription);
			statement.setBoolean(4, actuconfid);
            statement.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
			statement.setInt(6, id);
			statement.executeUpdate();
		}

		catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

    @Override
	public void deleteEntry() {
        System.out.println("Saisis l'id de l'actualité à supprimer l'ancien");
        int id = ApplicationActu.sc.nextInt();
		try {
			String query = "DELETE FROM actualite WHERE actu_id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	private Actualite map(ResultSet result) throws SQLException {
		Actualite actualite = new Actualite();

		actualite.setActu_id(result.getInt("actu_id"));   
		actualite.setActu_animal_id(result.getInt("actu_animal_id"));
		actualite.setActu_quartier_id(result.getInt("actu_quartier_id"));
		actualite.setActu_timestamp(result.getTimestamp("actu_timestamp").toLocalDateTime());
		actualite.setActu_coordonneesgps(result.getString("actu_coordonnees_gps"));
		actualite.setActu_description(result.getString("actu_description"));
		actualite.setActu_isPrivate(result.getBoolean("actu_isprivate"));
//		actualite.setActu_contactIdentifies(result.getBoolean("actu_contacts_identifies"));
	
		return actualite;
	}
}
