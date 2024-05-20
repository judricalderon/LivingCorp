package co.edu.unbosque.livingcorp.model.persistence;

import co.edu.unbosque.livingcorp.exception.DontExistException;
import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.entity.ServiceRequest;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

@Stateless
public class ServiceRequiestDao implements InterfaceDao<ServiceRequest,Integer> {
    @PersistenceContext(unitName = "livingCorpPU")
    private EntityManager em;

    /**
     * @param serviceRequest
     * @throws RepetedObjectException
     */
    @Override
    public void create(ServiceRequest serviceRequest) throws RepetedObjectException {
        try {
            em.persist(serviceRequest);
        }catch(EntityExistsException e) {
            throw new RepetedObjectException("Solicitud ya existe");
        }
    }

    /**
     * @param serviceRequest
     * @throws RepetedObjectException
     */
    @Override
    public void update(ServiceRequest serviceRequest) throws RepetedObjectException {
        try {
            em.merge(serviceRequest);
        }catch(EntityNotFoundException e){
            throw new RepetedObjectException( "Solicitud a modificar no existe");
        }
    }

    /**
     * @param serviceRequest
     * @throws DontExistException
     */
    @Override
    public void delete(ServiceRequest serviceRequest) throws DontExistException {
        try {
            em.remove(serviceRequest);
        }catch(EntityNotFoundException e){
            throw new DontExistException("Solicitud no existe");
        }
    }

    /**
     * @return
     */
    @Override
    public List<ServiceRequest> getAll() {
        try {
            return em.createQuery("SELECT s FROM ServiceRequest s", ServiceRequest.class).getResultList();
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
    public ServiceRequest find(Integer id) throws RepetedObjectException, DontExistException {
        try {
            return em.find(ServiceRequest.class, id);
        }catch(EntityNotFoundException e){
            throw new DontExistException("Rescurso no existe");
        }
    }
}
