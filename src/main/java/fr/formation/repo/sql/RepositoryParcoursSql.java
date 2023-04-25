package fr.formation.repo.sql;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import fr.formation.model.Animal;
import fr.formation.model.Parcours;
import fr.formation.repo.IParcoursRepository;

public class RepositoryParcoursSql extends AbstractRepositorySql implements IParcoursRepository{
	public List<Parcours> findAll(){

		String query = "SELECT * FROM parcours";
		PreparedStatement statement = connection.prepareStatement(query);
		
		statement.executeQuery();
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			int id = result.getInt("par_id");
			String ville = result.getString("par_ville");
			String quartier = result.getString("par_quartier");
			int gps = result.getInt("par_tracegps");
			int idcompte = result.getInt("par_compte_id");
			LocalDateTime date = result.getTimestamp("par_time").toLocalDateTime();
			System.out.println("id : " + id + " - ville : " + ville + " dans le quartier " + quartier + " - trace GPS : " + gps + " publié par : " + idcompte + " le " + date);
			
			}
	}

		
		
		
	}
	public Parcours save (Parcours entity) {
		try {
			String query = "INSERT INTO parcours (par_id, par_ville, par_quartier, par_time, par_tracegps) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement statement = this.connection.prepareStatement(query);
			
			statement.setInt(1, entity.getId());
			statement.setString(2, entity.getVilleParcours());
			statement.setString(3, entity.getQuartierParcours());
			statement.setTimestamp(4, Timestamp.valueOf(entity.getDatePublicationParcours()));
			statement.setInt(5, entity.getTraceGpsParcours());
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		return entity;
		
	}
	
	public Optional<Parcours> findByIdParcours (int id){
		return Optional.empty();
	}

    @Override
    public void deleteEntry() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteEntry'");
    }

    @Override
    public void createEntry() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createEntry'");
    }

    @Override
    public void updateEntry(Parcours entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateEntry'");
    }

    @Override
    public List<Parcours> findByDateParcours(LocalDateTime date) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByDateParcours'");
    }
}
