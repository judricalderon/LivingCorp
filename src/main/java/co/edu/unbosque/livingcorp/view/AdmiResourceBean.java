package co.edu.unbosque.livingcorp.view;

import co.edu.unbosque.livingcorp.exception.ExceptionRepetedObject;
import co.edu.unbosque.livingcorp.model.dto.ResourceDto;
import co.edu.unbosque.livingcorp.service.AdmiResourceService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named(value = "admiResourceBean")
@SessionScoped
public class AdmiResourceBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private AdmiResourceService admiResourceService;
    private ResourceDto resourceDto;

    @PostConstruct
    public void init() {
        resourceDto = new ResourceDto();
    }
    public void createResource(){
        try{
            boolean validacion = admiResourceService.create(resourceDto);
            if(validacion){
                System.out.println("Resource created");
            }else {
                System.out.println("Resource already exists");
            }
        } catch (ExceptionRepetedObject e) {
            System.out.println(e.getMessage());
        }
    }

}
