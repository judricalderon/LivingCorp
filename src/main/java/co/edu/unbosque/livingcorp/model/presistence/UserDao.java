package co.edu.unbosque.livingcorp.model.presistence;

import co.edu.unbosque.livingcorp.exception.ExceptionDontExist;
import co.edu.unbosque.livingcorp.exception.ExceptionRepetedObject;
import co.edu.unbosque.livingcorp.model.entity.Property;
import co.edu.unbosque.livingcorp.model.entity.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class UserDao implements InterfaceDao <User,String> {
    @PersistenceContext(unitName = "livingCorpPU")
    private EntityManager em;


    @Override
    public void create(User user) throws ExceptionRepetedObject {
        try {
            em.persist(user);
        }catch(EntityExistsException e){
            throw new ExceptionRepetedObject("Usuario ya existe");
        }
    }

    @Override
    public void update(User user) throws ExceptionRepetedObject {
        try {
            em.merge(user);
        }catch(EntityNotFoundException e){
            throw new ExceptionRepetedObject( "Usuario a modificar no existe");
        }
    }

    @Override
    public void delete(User user) throws ExceptionDontExist {
        try {
            em.remove(user);
        }catch(EntityNotFoundException e){
            throw new ExceptionDontExist("Usuario no existe");
        }
    }

    @Override
    public List<User> getAll() {
        return List.of();
    }

    @Override
    public User find(String id) throws ExceptionDontExist {
        try {
            return em.find(User.class, id);
        }catch(EntityNotFoundException e){
            throw new ExceptionDontExist("Usuario no existe");
    }
    }
}
