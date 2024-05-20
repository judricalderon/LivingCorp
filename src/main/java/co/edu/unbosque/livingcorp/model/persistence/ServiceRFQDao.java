package co.edu.unbosque.livingcorp.model.persistence;

import co.edu.unbosque.livingcorp.exception.DontExistException;
import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.entity.ServiceRFQ;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

@Stateless
public class ServiceRFQDao implements InterfaceDao<ServiceRFQ,Integer> {
    @PersistenceContext(unitName = "livingCorpPU")
    private EntityManager em;


    /**
     * @param serviceRFQ
     * @throws RepetedObjectException
     */
    @Override
    public void create(ServiceRFQ serviceRFQ) throws RepetedObjectException {
        try {
            em.persist(serviceRFQ);
        }catch(EntityExistsException e) {
            throw new RepetedObjectException("Cotizacion ya existe");
        }
    }

    /**
     * @param serviceRFQ
     * @throws RepetedObjectException
     */
    @Override
    public void update(ServiceRFQ serviceRFQ) throws RepetedObjectException {
        try {
            em.merge(serviceRFQ);
        }catch(EntityNotFoundException e){
            throw new RepetedObjectException( "Cotizacion a modificar no existe");
        }
    }

    /**
     * @param serviceRFQ
     * @throws DontExistException
     */
    @Override
    public void delete(ServiceRFQ serviceRFQ) throws DontExistException {
        try {
            em.remove(serviceRFQ);
        }catch(EntityNotFoundException e){
            throw new DontExistException("Cotizacion no existe");
        }
    }

    /**
     * @return
     */
    @Override
    public List<ServiceRFQ> getAll() {
        try {
            return em.createQuery("SELECT s FROM ServiceRFQ s", ServiceRFQ.class).getResultList();
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
    public ServiceRFQ find(Integer id) throws RepetedObjectException, DontExistException {
        try {
            return em.find(ServiceRFQ.class, id);
        }catch(EntityNotFoundException e){
            throw new DontExistException("Cotizacion no existe");
        }
    }
}
