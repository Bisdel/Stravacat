package fr.formation.repo.sql;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import fr.formation.repo.IQuartierRepository;
import fr.formation.model.Quartier;

public class RepositoryQuartierSql extends AbstractRepositorySql implements IQuartierRepository {

	@Override
	public List<Quartier> findAll() {
		List<Quartier> quartiers = new ArrayList<>();

		try {
			String query = "Select *from Quartier";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				Quartier quartier = new Quartier();

				quartier.setAmbiance(result.getString("quar_ambiance"));
				quartier.setId(result.getInt("quar_id"));
				quartier.setVille(result.getString("quar_ville"));

				quartiers.add(quartier);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return quartiers;
	}

	
	public Optional<Quartier> findByid(int id) {
		try {
			String query = "Select * from quartier where quar_id = ?";
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();

			if (result.next()) {
				Quartier quartier = new Quartier();

				quartier.setAmbiance(result.getString("quar_ambiance"));
				quartier.setId(result.getInt("quar_id"));
				quartier.setVille(result.getString("quar_ville"));

				return Optional.of(quartier);
			}

		}

		catch (SQLException ex) {
			ex.printStackTrace();
		}

		return Optional.empty();
	}

	
	public Quartier save(Quartier entity) {
		try {
			String query = "Insert into quartier (quar_ambiance , quar_ville) values(?,?)";
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, entity.getAmbiance());
			statement.setString(2, entity.getVille());

			statement.executeUpdate();
			
			ResultSet result = statement.getGeneratedKeys();
			
			if (result.next()) {
				entity.setId(result.getInt("quar_id"));
			}                                                    

				
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return entity;
	}


	public void deletedById(int id) {
	

	}


	
	public List<Quartier> findByVille(String ville) {
		try {
			String query = "Select * from quartier where quar_ville = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, ville);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				Quartier quartier = new Quartier();

				quartier.setAmbiance(result.getString("quar_ambiance"));
				quartier.setId(result.getInt("quar_id"));
				quartier.setVille(result.getString("quar_ville"));

			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return null;
	}


	@Override
	public void createEntry(String pseudo) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void updateEntry(Quartier entity) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteEntry() {
		// TODO Auto-generated method stub
		
	}



}
