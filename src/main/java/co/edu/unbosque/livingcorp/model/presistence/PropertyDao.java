package co.edu.unbosque.livingcorp.model.presistence;

import co.edu.unbosque.livingcorp.exception.ExceptionDontExist;
import co.edu.unbosque.livingcorp.exception.ExceptionRepetedObject;
import co.edu.unbosque.livingcorp.model.entity.Property;
import co.edu.unbosque.livingcorp.model.entity.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

@Stateless
public class PropertyDao implements InterfaceDao<Property,Integer> {
    @PersistenceContext (unitName = "livingCorpPU")
    private EntityManager em;


    @Override
    public void create(Property property) throws ExceptionRepetedObject {
        try {
            em.persist(property);
        }catch(EntityExistsException e){
            throw new ExceptionRepetedObject("Propiedad ya existe");
        }
    }

    @Override
    public void update(Property property)  throws ExceptionRepetedObject {
      try {
          em.merge(property);
      }catch(EntityNotFoundException e){
          throw new ExceptionRepetedObject( "Propiedad a modificar no existe");
      }
      }

    @Override
    public void delete(Property property) {

    }

    @Override
    public List<Property> getAll() {
        return List.of();
    }

    @Override
    public Property find(Integer id) throws ExceptionDontExist {
        try {
            return em.find(Property.class, id);
        } catch (EntityNotFoundException e) {
            throw new ExceptionDontExist("Usuario no existe");
        }
    }
}



