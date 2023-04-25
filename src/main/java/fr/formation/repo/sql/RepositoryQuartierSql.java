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
import fr.formation.model.Saisie;

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
	public void createEntry() {
		IQuartierRepository repoQuartier = new RepositoryQuartierSql();
		System.out.println(" Creer un nouveau quartier ");
		Quartier quartier = new Quartier();
		String ville = Saisie.next("le nom de la ville :");
		String ambiance = Saisie.next(" l'ambiance : ");
		quartier.setVille(ville);
		quartier.setAmbiance(ambiance);
		repoQuartier.updateEntry(quartier);
		System.out.println("le quartier  " + quartier.getId() + "ajouté");

	}

	@Override
	public void updateEntry(Quartier entity) {
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
		return;
	}

	@Override
	public void deleteEntry() {
		try {
	
			String query = "delete from quartier where quar_id= ? ";
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setInt(1, Saisie.nextInt("son Id"));
			statement.executeUpdate();
		} catch (SQLException d) {
			d.printStackTrace();
		}

	}

}
