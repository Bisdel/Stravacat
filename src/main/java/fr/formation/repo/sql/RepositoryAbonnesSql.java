package fr.formation.repo.sql;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fr.formation.model.Abonnes;
import fr.formation.model.Quartier;
import fr.formation.model.Saisie;
import fr.formation.repo.IAbonnesRepository;
import fr.formation.repo.IQuartierRepository;

public class RepositoryAbonnesSql extends AbstractRepositorySql implements IAbonnesRepository {

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

	public Optional<Abonnes> findByid(int id) {
		try {
			String query = "select * from abonnes where abon_id = ? ";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();

			if (result.next()) {
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

	@Override
	public void createEntry() {
		IAbonnesRepository repoAbonnes = new RepositoryAbonnesSql();
		Abonnes abonnes = new Abonnes();

		int id_animal = Saisie.nextInt("L'Id de l'animal: ");
		int age = Saisie.nextInt("Son age : ");
		String pseudo = Saisie.next("Son pseudo :");
		int nb_patounes = Saisie.nextInt("nb_patounes : ");
		int id_quartier = Saisie.nextInt(" l'Id du quartier : ");
		abonnes.setAnimal_id(id_animal);
		abonnes.setAge(age);
		abonnes.setPseudo(pseudo);
		abonnes.setNb_patounes(nb_patounes);
		abonnes.setQuartier_id(id_quartier);

		repoAbonnes.updateEntry(abonnes);
		System.out.println("l'abonne à été ajouter !");
	}

	@Override
	public void updateEntry(Abonnes entity) {
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
		return;

	}

	@Override
	public void deleteEntry() {
		try {
			String query = "delete from abonnes where abon_pseudo= ? ";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, Saisie.next("veuillez saisr le pseudo que vous voulez supprimer : "));
			statement.executeUpdate();

		} catch (SQLException d) {
			d.printStackTrace();
		}
	}

	@Override
	public List<Abonnes> findByPseudo(String pseudo) {

		return null;
	}

}
