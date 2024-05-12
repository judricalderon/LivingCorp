package co.edu.unbosque.livingcorp.model.presistence;

import co.edu.unbosque.livingcorp.exception.ExceptionDontExist;
import co.edu.unbosque.livingcorp.exception.ExceptionRepetedObject;
import co.edu.unbosque.livingcorp.model.entity.Resource;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class ResourceDao implements InterfaceDao<Resource,Integer> {
    @PersistenceContext (unitName = "livingCorpPU")
    private EntityManager em;


    @Override
    public void create(Resource resource) throws ExceptionRepetedObject {
       try {
           em.persist(resource);
       }catch(EntityExistsException e) {
           throw new ExceptionRepetedObject("Recurso ya existe");
       }

    }

    @Override
    public void update(Resource resource) throws ExceptionRepetedObject {

    }

    @Override
    public void delete(Resource resource) throws ExceptionDontExist {

    }

    @Override
    public List<Resource> getAll() {
        return List.of();
    }

    @Override
    public Resource find(Integer id) throws ExceptionRepetedObject, ExceptionDontExist {
        return null;
    }
}
