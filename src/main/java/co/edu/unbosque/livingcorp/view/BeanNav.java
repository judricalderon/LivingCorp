package co.edu.unbosque.livingcorp.view;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named(value = "beanNav")
@RequestScoped
public class BeanNav implements Serializable {
    private static final long serialVersionUID = 1L;
    public String inicio(){
        return "index.xhtml";
    }

}
