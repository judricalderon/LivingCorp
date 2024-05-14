package co.edu.unbosque.livingcorp.model.presistence;

import co.edu.unbosque.livingcorp.exception.DontExistException;
import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.entity.Resource;
import co.edu.unbosque.livingcorp.model.entity.Visitor;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

@Stateless

public class VisitorDao implements InterfaceDao<Visitor,Integer> {
    @PersistenceContext(unitName = "")
    private EntityManager em;

    /**
     * @param visitor
     * @throws RepetedObjectException
     */
    @Override
    public void create(Visitor visitor) throws RepetedObjectException {
        try {
            em.persist(visitor);
        }catch(EntityExistsException e) {
            throw new RepetedObjectException("Cita ya existe");
        }
    }

    /**
     * @param visitor
     * @throws RepetedObjectException
     */
    @Override
    public void update(Visitor visitor) throws RepetedObjectException {

        try {
            em.merge(visitor);
        }catch(EntityNotFoundException e){
            throw new RepetedObjectException( "Cita a modificar no existe");
        }
    }

    /**
     * @param visitor
     * @throws DontExistException
     */
    @Override
    public void delete(Visitor visitor) throws DontExistException {

        try {
            em.remove(visitor);
        }catch(EntityNotFoundException e){
            throw new DontExistException("Cita no existe");
        }
    }

    /**
     * @return
     */
    @Override
    public List<Visitor> getAll() {
        try {
            return em.createQuery("SELECT a FROM Visitor a", Visitor.class).getResultList();
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
    public Visitor find(Integer id) throws RepetedObjectException, DontExistException {
        try {
            return em.find(Visitor.class, id);
        }catch(EntityNotFoundException e){
            throw new DontExistException("Cita no existe");
        }
    }
}
