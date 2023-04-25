package fr.formation.repo.sql;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fr.formation.repo.IVilleRepository;
import fr.formation.model.Ville;
import fr.formation.model.Saisie;

public class RepositoryVilleSql extends AbstractRepositorySql implements IVilleRepository {

	@Override
	public List<Ville> findAll() {

		List<Ville> villes = new ArrayList<>();

		try {
			String query = "Select *from Ville";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				Ville ville = new Ville();

				ville.setAmbiance(result.getString("vill_ambiance"));
				ville.setId(result.getInt("vill_id"));
				ville.setVille(result.getString("vill_ville"));

				villes.add(ville);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return villes;
	}

	public Optional<Ville> findByid(int id) {
		try {
			String query = "Select * from ville where vill_id = ?";
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();

			if (result.next()) {
				Ville ville = new Ville();

				ville.setAmbiance(result.getString("vill_ambiance"));
				ville.setId(result.getInt("vill_id"));
				ville.setVille(result.getString("vill_ville"));

				return Optional.of(ville);
			}

		}

		catch (SQLException ex) {
			ex.printStackTrace();
		}

		return Optional.empty();
	}

	public List<Ville> findByVille(String ville2) {
		try {
			String query = "Select * from ville where vill_ville = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, ville2);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				Ville ville = new Ville();

				ville.setAmbiance(result.getString("vill_ambiance"));
				ville.setId(result.getInt("vill_id"));
				ville.setVille(result.getString("vill_ville"));

			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return null;
	}

	@Override
	public void createEntry() {
		IVilleRepository repoVille = new RepositoryVilleSql();
		System.out.println(" Creer un nouveau ville ");
		Ville ville = new Ville();
		String vil = Saisie.next("le nom de la ville :");
		String ambiance = Saisie.next(" l'ambiance : ");
		ville.setVille(vil);
		ville.setAmbiance(ambiance);
		repoVille.updateEntry(ville);
		System.out.println("le ville  " + ville.getId() + "ajouté");

	}

	@Override
	public void updateEntry(Ville entity) {
		try {
			String query = "Insert into ville (vill_ambiance , vill_ville) values(?,?)";
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, entity.getAmbiance());
			statement.setString(2, entity.getVille());
			statement.executeUpdate();
			ResultSet result = statement.getGeneratedKeys();
			if (result.next()) {
				entity.setId(result.getInt("vill_id"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return;
	}

	@Override
	public void deleteEntry() {
		try {
	
			String query = "delete from ville where vill_id= ? ";
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setInt(1, Saisie.nextInt("son Id"));
			statement.executeUpdate();
		} catch (SQLException d) {
			d.printStackTrace();
		}

	}

}
