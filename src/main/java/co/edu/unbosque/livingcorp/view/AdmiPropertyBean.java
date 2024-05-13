package co.edu.unbosque.livingcorp.view;

import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.dto.PropertyDto;
import co.edu.unbosque.livingcorp.service.AdmiPropertiesService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named (value = "admiPropertyBean")
@SessionScoped
public class AdmiPropertyBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Inject
    private AdmiPropertiesService admiPropertiesService;
    private PropertyDto propertyDto;

    @PostConstruct
    public void init() {
        propertyDto = new PropertyDto();
    }
    public void createProperty() throws RepetedObjectException {
        try{
            boolean validaPropiedad = admiPropertiesService.createProperties(propertyDto);
            if (validaPropiedad) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Property created"));
                System.out.println("Property created");
            }else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Property already exists"));
                System.out.println("Property already exists");
            }
        }catch (RepetedObjectException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            System.out.println(e.getMessage());
        }
    }

    public PropertyDto getPropertyDto() {
        return propertyDto;
    }

    public void setPropertyDto(PropertyDto propertyDto) {
        this.propertyDto = propertyDto;
    }
}
