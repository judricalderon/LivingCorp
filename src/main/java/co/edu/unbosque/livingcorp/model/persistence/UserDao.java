package co.edu.unbosque.livingcorp.model.persistence;

import co.edu.unbosque.livingcorp.exception.DontExistException;
import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.entity.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

@Stateless
public class UserDao implements InterfaceDao <User,String> {
    @PersistenceContext(unitName = "livingCorpPU")
    private EntityManager em;


    @Override
    public void create(User user) throws RepetedObjectException {
        try {
            em.persist(user);
        }catch(EntityExistsException e){
            throw new RepetedObjectException("Usuario ya existe");
        }
    }

    @Override
    public void update(User user) throws RepetedObjectException {
        try {
            em.merge(user);
        }catch(EntityNotFoundException e){
            throw new RepetedObjectException( "Usuario a modificar no existe");
        }
    }

    @Override
    public void delete(User user) throws DontExistException {
        try {
            em.remove(user);
        }catch(EntityNotFoundException e){
            throw new DontExistException("Usuario no existe");
        }
    }

    @Override
    public List<User> getAll() {
        try {
            return em.createQuery("SELECT u FROM User u", User.class).getResultList();
        }catch(NoResultException ignored){
            return List.of();
        }
    }

    @Override
    public User find(String id) throws DontExistException {
        try {
            return em.find(User.class, id);
        }catch(EntityNotFoundException e){
            throw new DontExistException("Usuario no existe");
    }
    }
}
