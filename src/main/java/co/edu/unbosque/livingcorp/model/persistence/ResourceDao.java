package co.edu.unbosque.livingcorp.model.persistence;

import co.edu.unbosque.livingcorp.exception.DontExistException;
import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.entity.Resource;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

@Stateless
public class ResourceDao implements InterfaceDao<Resource,Integer> {
    @PersistenceContext (unitName = "livingCorpPU")
    private EntityManager em;


    @Override
    public void create(Resource resource) throws RepetedObjectException {
       try {
           em.persist(resource);
       }catch(EntityExistsException e) {
           throw new RepetedObjectException("Recurso ya existe");
       }

    }

    @Override
    public void update(Resource resource) throws RepetedObjectException {
        try {
            em.merge(resource);
        }catch(EntityNotFoundException e){
            throw new RepetedObjectException( "Recurso a modificar no existe");
        }
    }

    @Override
    public void delete(Resource resource) throws DontExistException {
        try {
            em.remove(resource);
        }catch(EntityNotFoundException e){
            throw new DontExistException("Recurso no existe");
        }
    }

    @Override
    public List<Resource> getAll() {
        try {
            return em.createQuery("SELECT r FROM Resource r", Resource.class).getResultList();
        }catch(NoResultException ignored){
            return List.of();
        }
    }

    @Override
    public Resource find(Integer id) throws RepetedObjectException, DontExistException {
        try {
            return em.find(Resource.class, id);
        }catch(EntityNotFoundException e){
            throw new DontExistException("Rescurso no existe");
        }
    }
}
