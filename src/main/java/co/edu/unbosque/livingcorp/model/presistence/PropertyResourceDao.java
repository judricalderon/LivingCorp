package co.edu.unbosque.livingcorp.model.presistence;

import co.edu.unbosque.livingcorp.exception.DontExistException;
import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.entity.Property;
import co.edu.unbosque.livingcorp.model.entity.PropertyResource;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;
@Stateless
public class PropertyResourceDao implements InterfaceDao<PropertyResource,Integer> {
    @PersistenceContext(unitName = "livingCorpPU")
    private EntityManager em;
    /**
     * @param propertyResource
     * @throws RepetedObjectException
     */
    @Override
    public void create(PropertyResource propertyResource) throws RepetedObjectException {
        try {
            em.persist(propertyResource);
        }catch(EntityExistsException e){
            throw new RepetedObjectException("Recurso de la propiedad ya existe");
        }
    }

    /**
     * @param propertyResource
     * @throws RepetedObjectException
     */
    @Override
    public void update(PropertyResource propertyResource) throws RepetedObjectException {
        try {
            em.merge(propertyResource);
        }catch(EntityNotFoundException e){
            throw new RepetedObjectException( "Recurso de la propiedad a modificar no existe");
        }
    }

    /**
     * @param propertyResource
     * @throws DontExistException
     */
    @Override
    public void delete(PropertyResource propertyResource) throws DontExistException {
        try {
            em.remove(propertyResource);
        }catch(EntityNotFoundException e){
            throw new DontExistException("recurso de la propiedad no existe");
        }
    }

    /**
     * @return
     */
    @Override
    public List<PropertyResource> getAll() {
        try {
            return em.createQuery("SELECT r FROM PropertyResource r", PropertyResource.class).getResultList();
        }catch(NoResultException ignored){
            return List.of();
        }
    }

    /**
     * @param id
     * @return
     * @throws RepetedObjectException
     * @throws DontExistException
     */
    @Override
    public PropertyResource find(Integer id) throws RepetedObjectException, DontExistException {
        try {
            return em.find(PropertyResource.class, id);
        } catch (EntityNotFoundException e) {
            throw new DontExistException("Recurso de la Propiedad no existe");
        }
    }
}
