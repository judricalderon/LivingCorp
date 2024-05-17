package co.edu.unbosque.livingcorp.model.presistence;

import co.edu.unbosque.livingcorp.exception.DontExistException;
import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.entity.Resident;
import co.edu.unbosque.livingcorp.model.entity.Resource;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

@Stateless
public class ResidentDao implements InterfaceDao<Resident,Integer>{
    @PersistenceContext(unitName = "livingCorpPU")
    private EntityManager em;


    /**
     * @param resident
     * @throws RepetedObjectException
     */
    @Override
    public void create(Resident resident) throws RepetedObjectException {
        try {
            em.persist(resident);
        }catch(EntityExistsException e) {
            throw new RepetedObjectException("Residente ya existe");
        }
    }

    /**
     * @param resident
     * @throws RepetedObjectException
     */
    @Override
    public void update(Resident resident) throws RepetedObjectException {
        try {
            em.merge(resident);
        }catch(EntityNotFoundException e){
            throw new RepetedObjectException( "Residente a modificar no existe");
        }
    }

    /**
     * @param resident
     * @throws DontExistException
     */
    @Override
    public void delete(Resident resident) throws DontExistException {
        try {
            em.remove(resident);
        }catch(EntityNotFoundException e){
            throw new DontExistException("Residente no existe");
        }
    }

    /**
     * @return
     */
    @Override
    public List<Resident> getAll() {
        try {
            return em.createQuery("SELECT r FROM Resident r", Resident.class).getResultList();
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
    public Resident find(Integer id) throws RepetedObjectException, DontExistException {
        try {
            return em.find(Resident.class, id);
        }catch(EntityNotFoundException e){
            throw new DontExistException("Residente no existe");
        }
    }
}
