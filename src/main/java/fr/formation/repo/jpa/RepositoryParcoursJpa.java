package fr.formation.repo.jpa;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import fr.formation.ApplicationParcoursJpa;
import fr.formation.model.Parcours;
import fr.formation.repo.IParcoursRepository;
import jakarta.persistence.EntityManager;

public class RepositoryParcoursJpa extends AbstractRepositoryJpa implements IParcoursRepository{
    @Override
    public List <Parcours> findAll(){
        try (EntityManager em = emf.createEntityManager()){
            return em.createQuery ("select p from Parcours p", Parcours.class).getResultList();
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
            return;
        }
    }

    @Override
    public void deleteEntry (){
        System.out.println("Entrez l'id du parcours à supprimer : ");
        String id = ApplicationParcoursJpa.sc.nextLine();
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

    @Override
    public void createEntry() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createEntry'");
    }

    @Override
    public List<Parcours> findByDateParcours(LocalDateTime start) {
        
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            LocalDateTime end = start.plusDays(1);
            return em.createQuery("select p from Parcours p where p.datePublicationParcours between ?1 and ?2", Parcours.class)
                    .setParameter(1, start)
                    .setParameter(2, end)
                    .getResultList();
        }
        catch (Exception ex){
            ex.printStackTrace();
            return new ArrayList<>();
        }
        
    }

}