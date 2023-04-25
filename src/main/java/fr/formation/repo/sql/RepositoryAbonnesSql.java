package fr.formation.repo.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fr.Stravact.repo.IAbonnesRepository;
import fr.formation.model.Abonnes;

public class RepositoryAbonnesSql extends AbstractRepository implements IAbonnesRepository {

	@Override
	public List<Abonnes> findAll() {
		List<Abonnes> abonnes = new ArrayList<>();

		try {
			String qyery = " select * from abonnes";
			PreparedStatement statement = connection.prepareStatement(qyery);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				Abonnes abonne = new Abonnes();

				abonne.setAge(result.getInt("abon_age"));
				abonne.setId(result.getInt("abon_id"));
				abonne.setPseudo(result.getString("abon_pseudo"));
				abonne.setAnimal_id(result.getInt("abon_animal_id"));
				abonne.setNb_patounes(result.getInt("abon_nb_patounes"));
				abonne.setQuartier_id(result.getInt("abon_quartier_id"));

				abonnes.add(abonne);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return abonnes;
	}

	@Override
	public Optional<Abonnes> findByid(int id) {
		try {
			String query = "select * from abonnes where abon_id = ? ";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();

		if(result.next()) {
				Abonnes abonne = new Abonnes();
				
				abonne.setAge(result.getInt("abon_age"));
				abonne.setId(result.getInt("abon_id"));
				abonne.setPseudo(result.getString("abon_pseudo"));
				abonne.setAnimal_id(result.getInt("abon_animal_id"));
				abonne.setNb_patounes(result.getInt("abon_nb_patounes"));
				abonne.setQuartier_id(result.getInt("abon_quartier_id"));

				return Optional.of(abonne);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return Optional.empty();
	}

	@Override
	public Abonnes save(Abonnes entity) {
		try {
			String query = "INSERT INTO ABONNES( abon_age , abon_pseudo, abon_animal_id , abon_nb_patounes ,abon_quartier_id) values (?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setFloat(1, entity.getAge());
			statement.setString(2, entity.getPseudo());
			statement.setFloat(3, entity.getAnimal_id());
			statement.setFloat(4, entity.getNb_patounes());
			statement.setFloat(5, entity.getQuartier_id());
			
			ResultSet result = statement.getGeneratedKeys();
			statement.executeUpdate();
			
			if (result.next()) {
				entity.setId(result.getInt("abon_id"));
			}
		} catch (SQLException m) {
			m.printStackTrace();
		}
		return entity;
	}

	@Override
	public void deletedById(int id) {
		try {
			String query = "delete from abonnes where abon_id = ? ";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();

		} catch (SQLException d) {
			d.printStackTrace();
		}

	}
	@Override
	public List<Abonnes> findByAbonnes(String pseudo) {
		try {
			String qyery = " selectr * from abonnes where abon_pseudo = ?";
			PreparedStatement statement = connection.prepareStatement(qyery);
			statement.setString(1, pseudo);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				Abonnes abonne = new Abonnes();

				abonne.setAge(result.getInt("abon_age"));
				abonne.setId(result.getInt("abon_id"));
				abonne.setPseudo(result.getString("abon_pseudo"));
				abonne.setAnimal_id(result.getInt("abon_animal_id"));
				abonne.setNb_patounes(result.getInt("abon_nb_patounes"));
				abonne.setQuartier_id(result.getInt("abon_quartier_id"));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
