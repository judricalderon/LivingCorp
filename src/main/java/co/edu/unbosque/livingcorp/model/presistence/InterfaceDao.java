package co.edu.unbosque.livingcorp.model.presistence;

import co.edu.unbosque.livingcorp.exception.ExceptionDontExist;
import co.edu.unbosque.livingcorp.exception.ExceptionRepetedObject;

import java.util.List;

public interface InterfaceDao <T,K>{
    void create(T t) throws ExceptionRepetedObject;
    void update(T t) throws ExceptionRepetedObject;
    void delete(T t) throws ExceptionDontExist;
    List<T> getAll();
    T find(K id) throws ExceptionRepetedObject, ExceptionDontExist;
}
