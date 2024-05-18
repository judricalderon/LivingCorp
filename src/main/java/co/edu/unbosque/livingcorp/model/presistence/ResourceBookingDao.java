package co.edu.unbosque.livingcorp.model.presistence;

import co.edu.unbosque.livingcorp.exception.DontExistException;
import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.entity.Resource;
import co.edu.unbosque.livingcorp.model.entity.ResourceBooking;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

@Stateless
public class ResourceBookingDao implements InterfaceDao<ResourceBooking, Integer> {

    @PersistenceContext(unitName = "livingCorpPU")
    private EntityManager em;
    /**
     * @param resourceBooking
     * @throws RepetedObjectException
     */
    @Override
    public void create(ResourceBooking resourceBooking) throws RepetedObjectException {
        try {
            em.persist(resourceBooking);
        }catch(EntityExistsException e) {
            throw new RepetedObjectException("Reserva del recurso ya existe");
        }
    }

    /**
     * @param resourceBooking
     * @throws RepetedObjectException
     */
    @Override
    public void update(ResourceBooking resourceBooking) throws RepetedObjectException {
        try {
            em.merge(resourceBooking);
        }catch(EntityNotFoundException e){
            throw new RepetedObjectException( "Reserva del recurso a modificar no existe");
        }
    }

    /**
     * @param resourceBooking
     * @throws DontExistException
     */
    @Override
    public void delete(ResourceBooking resourceBooking) throws DontExistException {
        try {
            em.remove(resourceBooking);
        }catch(EntityNotFoundException e){
            throw new DontExistException("Reserva del recurso no existe");
        }
    }

    /**
     * @return
     */
    @Override
    public List<ResourceBooking> getAll() {
        try {
            return em.createQuery("SELECT b FROM ResourceBooking b", ResourceBooking.class).getResultList();
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
    public ResourceBooking find(Integer id) throws RepetedObjectException, DontExistException {
        try {
            return em.find(ResourceBooking.class, id);
        }catch(EntityNotFoundException e){
            throw new DontExistException("Reserva del recurso no existe");
        }
    }


}
