package fr.formation.repo.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fr.formation.model.Animal;
import fr.formation.model.Parcours;
import fr.formation.repo.IParcoursRepository;
import jakarta.persistence.EntityManager;

public class RepositoryParcoursJpa extends AbstractRepositoryJpa implements IParcoursRepository{
    @Override
    public List <Parcours> findAll(){
        try (EntityManager em = emf.createEntityManager()){
            return em.createQuery ("select p from parcours p", Parcours.class).getResultList();
        }
        catch (Exception ex){
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void updateEntry (Parcours entity){
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            try{
                if (entity.getId() == 0){
                    em.persist(entity);
                }
                else {
                    entity = em.merge(entity);
                }
                em.getTransaction().commit();
            }
            catch (Exception ex){
                ex.printStackTrace();
                em.getTransaction().rollback();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }

            return;
        }
    }

    @Override
    public void deleteEntry (){
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            try{
                em  .createQuery("delete from Parcours p where p.id = ?1")
                    .setParameter(1, id)
                    .executeUpdate();
                em.getTransaction().commit();
            }
            catch (Exception ex){
                ex.printStackTrace();
                em.getTransaction().rollback();
            }

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        
    }

}