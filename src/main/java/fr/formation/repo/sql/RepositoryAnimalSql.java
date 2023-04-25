package fr.formation.repo.sql;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Optional;
import fr.formation.ApplicationAnimalSql;
import fr.formation.ApplicationAdminSql;
import fr.formation.model.Animal;
import fr.formation.repo.IRepositoryAnimal;

import java.lang.String;

public class RepositoryAnimalSql extends AbstractRepositorySql implements IRepositoryAnimal {

	@Override
	public List<Animal> findAll() { 
		List<Animal> animaux = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			ResultSet results = statement.executeQuery("SELECT * from animal ORDER BY anim_id ASC");

			while (results.next()) {
				Animal animal = new Animal();
				animal.setId(results.getInt("anim_id"));
				animal.setPseudo(results.getString("anim_pseudo"));
				animal.setPassword(results.getString("anim_password"));
				animal.setAge(results.getInt("anim_age"));
				animal.setNbPatounes(results.getInt("anim_nb_patounes"));
				animal.setVilleId(results.getInt("anim_ville_id"));
				animaux.add(animal);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return animaux;
	}

	@Override
	public Optional<Animal> findByPseudo(String pseudo) {
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM animal WHERE anim_pseudo = ?");
			statement.setString(1, pseudo);
			ResultSet result = statement.executeQuery();
			result.next();
			Animal animal = new Animal();
			animal.setId(result.getInt("anim_id"));
			animal.setPseudo(result.getString("anim_pseudo"));
			animal.setPassword(result.getString("anim_password"));
			animal.setAge(result.getInt("anim_age"));
			animal.setNbPatounes(result.getInt("anim_nb_patounes"));
			animal.setVilleId(result.getInt("anim_ville_id"));
			return Optional.of(animal);

		} catch (SQLException e) {
			return Optional.empty();
		}
	}

	@Override
	public void createEntry() {
		System.out.println(
				"Veuillez entrer les informations de votre nouvel animal, au format pseudo - motdepasse - age - nombredepatounes");
		Animal animal = new Animal();
		try {
			String infos = ApplicationAnimalSql.sc.nextLine();
			animal.setPseudo(String.format(infos.split(" - ")[0]));
			animal.setPassword(String.format(infos.split(" - ")[1]));
			animal.setAge(Integer.parseInt(infos.split(" - ")[2]));
			animal.setNbPatounes(Integer.parseInt(infos.split(" - ")[3]));
		} catch (NumberFormatException e) {
			System.out.println("Le format de l'age et/ou du nombre de patounes est incorrect.");
		} catch (IllegalFormatException e) {
			System.out.println("Le format du pseudo et/ou du mot de passe est incorrect.");
		}
		try {
			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO animal(anim_pseudo, anim_password, anim_age, anim_nb_patounes) VALUES(?,?,?,?)");
			statement.setString(1, animal.getPseudo());
			statement.setString(2, animal.getPassword());
			statement.setInt(3, animal.getAge());
			statement.setInt(4, animal.getNbPatounes());
			statement.executeUpdate();
			System.out.println("Votre nouvel animal a bien été créé !");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateEntry(Animal animal) {
		boolean saisieValide = false;
		while (saisieValide == false) {
			System.out.println(
					"\nVeuillez entrer les informations à modifier, au format champ(pseudo/motdepasse/age/patounes) - donnée, exemple : pseudo - Franklinlatortue)");
			String infos = ApplicationAnimalSql.sc.nextLine();

			try {
				try {
					animal.setPseudo(String.format(infos.split("pseudo - ")[1]));
					saisieValide = true;
				} catch (ArrayIndexOutOfBoundsException e) {
					// ignores error thrown for the fields not filled by user to update
				}
				try {
					animal.setPassword(String.format(infos.split("motdepasse - ")[1]));
					saisieValide = true;
				} catch (ArrayIndexOutOfBoundsException e) {
					// ignores error thrown for the fields not filled by user to update
				}
				try {
					animal.setAge(Integer.parseInt(infos.split("age - ")[1]));
					saisieValide = true;
				} catch (ArrayIndexOutOfBoundsException e) {
					// ignores error thrown for the fields not filled by user to update
				}
				try {
					animal.setNbPatounes(Integer.parseInt(infos.split("patounes - ")[1]));
					saisieValide = true;
				} catch (ArrayIndexOutOfBoundsException e) {
					// ignores error thrown for the fields not filled by user to update
				}
			} catch (Exception e) {
				System.out.println(
						"\nUne erreur s'est glissée dans votre saisie. Veuillez bien respecter le format de saisie des données à modifier :)");
			}
		}
		try {
			PreparedStatement statement = connection.prepareStatement(
					"UPDATE animal SET anim_pseudo = ?, anim_password = ?, anim_age = ?, anim_nb_patounes = ? WHERE anim_id = ?");
			statement.setString(1, animal.getPseudo());
			statement.setString(2, animal.getPassword());
			statement.setInt(3, animal.getAge());
			statement.setInt(4, animal.getNbPatounes());
			statement.setInt(5, animal.getId());
			statement.executeUpdate();
			System.out.println("Les informations de votre animal ont bien été modifiées !");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteEntry() {
		boolean saisieValide = false;
		while (saisieValide == false) {
			System.out.println("Entrez le pseudo à supprimer :");
			String pseudo = ApplicationAdminSql.scadmin.nextLine();
			try {
				PreparedStatement verifStatement = connection
						.prepareStatement("SELECT * FROM animal WHERE anim_pseudo = ?");
				verifStatement.setString(1, pseudo);
				if (verifStatement.executeQuery().next() == false) {
					System.out.println("Le pseudo entré n'existe pas dans la base de données.");
				} else {
					PreparedStatement statement = connection
							.prepareStatement("DELETE FROM animal WHERE anim_pseudo = ?");
					statement.setString(1, pseudo);
					statement.executeUpdate();
					System.out.println("L'animal a bien été supprimé de la base de données.");
					saisieValide = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}