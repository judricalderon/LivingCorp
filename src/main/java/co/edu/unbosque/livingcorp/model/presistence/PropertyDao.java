package co.edu.unbosque.livingcorp.model.presistence;

import co.edu.unbosque.livingcorp.exception.DontExistException;
import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.entity.Property;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

@Stateless
public class PropertyDao implements InterfaceDao<Property,Integer> {
    @PersistenceContext (unitName = "livingCorpPU")
    private EntityManager em;


    @Override
    public void create(Property property) throws RepetedObjectException {
        try {
            em.persist(property);
        }catch(EntityExistsException e){
            throw new RepetedObjectException("Propiedad ya existe");
        }
    }

    @Override
    public void update(Property property)  throws RepetedObjectException {
      try {
          em.merge(property);
      }catch(EntityNotFoundException e){
          throw new RepetedObjectException( "Propiedad a modificar no existe");
      }
      }

    @Override
    public void delete(Property property) throws DontExistException {
        try {
            em.remove(property);
        }catch(EntityNotFoundException e){
            throw new DontExistException("Propiedad no existe");
        }
    }

    @Override
    public List<Property> getAll() {
        try {
            return em.createQuery("SELECT p FROM Property p", Property.class).getResultList();
        }catch(NoResultException ignored){
            return List.of();
        }

    }

    @Override
    public Property find(Integer id) throws DontExistException {
        try {
            return em.find(Property.class, id);
        } catch (EntityNotFoundException e) {
            throw new DontExistException("Propiedad no existe");
        }
    }
}



