package co.edu.unbosque.livingcorp.view;

import co.edu.unbosque.livingcorp.exception.DontExistException;
import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.dto.PropertyDto;
import co.edu.unbosque.livingcorp.model.dto.UserDto;
import co.edu.unbosque.livingcorp.service.AdmiPropertiesService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.log4j.Logger;
import java.util.List;

@Named (value = "admiPropertyBean")
@RequestScoped
public class AdmiPropertyBean  {
    private static final Logger logger = Logger.getLogger(AdmiPropertyBean.class);

    @Inject
    private AdmiPropertiesService admiPropertiesService;
    private PropertyDto propertyDto;
    private List<String> administradores;

    @PostConstruct
    public void init() {
        update();
    }
    public void createProperty() throws RepetedObjectException {
        try{
            boolean validaPropiedad = admiPropertiesService.createProperties(propertyDto);
            if (validaPropiedad) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Property created"));
                logger.info("Property created");
            }else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Property already exists"));
                logger.warn("Property already exists");
            }
        }catch (RepetedObjectException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            logger.error(e.getMessage());
        } catch (DontExistException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            logger.error(e.getMessage());
        }
    }

    public PropertyDto getPropertyDto() {
        return propertyDto;
    }

    public void setPropertyDto(PropertyDto propertyDto) {
        this.propertyDto = propertyDto;
    }

    public List<String> getAdministradores() {
        return administradores;
    }

    public void setAdministradores(List<String> administradores) {
        this.administradores = administradores;
    }

    public void update(){
        propertyDto = new PropertyDto();
        propertyDto.setPropertyAdmin(new UserDto());
        administradores = admiPropertiesService.listarAdmi();
        logger.info(administradores);

    }
}
