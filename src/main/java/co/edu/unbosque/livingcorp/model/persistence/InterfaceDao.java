package co.edu.unbosque.livingcorp.model.persistence;

import co.edu.unbosque.livingcorp.exception.DontExistException;
import co.edu.unbosque.livingcorp.exception.RepetedObjectException;

import java.util.List;

public interface InterfaceDao <T,K>{
    void create(T t) throws RepetedObjectException;
    void update(T t) throws RepetedObjectException;
    void delete(T t) throws DontExistException;
    List<T> getAll();
    T find(K id) throws RepetedObjectException, DontExistException;
}
