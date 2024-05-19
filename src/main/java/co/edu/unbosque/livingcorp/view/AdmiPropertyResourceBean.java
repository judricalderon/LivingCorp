package co.edu.unbosque.livingcorp.view;

import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.dto.PropertyDto;
import co.edu.unbosque.livingcorp.model.dto.PropertyResourceDto;
import co.edu.unbosque.livingcorp.model.dto.ResourceDto;
import co.edu.unbosque.livingcorp.service.AdmiPropertyResourceService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.log4j.Logger;

import java.util.List;

@Named(value = "admiPropertyResourceBean")
@RequestScoped
public class AdmiPropertyResourceBean  {
    private static final Logger logger = Logger.getLogger(AdmiPropertyResourceBean.class);

    @Inject
    private AdmiPropertyResourceService admiPropertyResourceService;
    private List<String> listNameProperty;
    private List<String> listTypeResource;
    private List<String> listEmailUser;
    private PropertyResourceDto propertyResourceDto;

    @PostConstruct
    public void init() {
        update();
    }

    public void createPropertyResource() {
        try{
            boolean auxPropertyResource = admiPropertyResourceService.createPropertyResource(propertyResourceDto);
            if (auxPropertyResource) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Property Resource created"));
                logger.info("Property Resource created");
            }else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Property already exists"));
                logger.warn("Property already exists");
            }
        } catch (RepetedObjectException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            logger.error(e.getMessage());
        }
    }

    public List<String> getListNameProperty() {
        return listNameProperty;
    }

    public void setListNameProperty(List<String> listNameProperty) {
        this.listNameProperty = listNameProperty;
    }

    public List<String> getListTypeResource() {
        return listTypeResource;
    }

    public void setListTypeResource(List<String> listTypeResource) {
        this.listTypeResource = listTypeResource;
    }

    public List<String> getListEmailUser() {
        return listEmailUser;
    }

    public void setListEmailUser(List<String> listEmailUser) {
        this.listEmailUser = listEmailUser;
    }

    public PropertyResourceDto getPropertyResourceDto() {
        return propertyResourceDto;
    }

    public void setPropertyResourceDto(PropertyResourceDto propertyResourceDto) {
        this.propertyResourceDto = propertyResourceDto;
    }
    public void update(){
        listNameProperty = admiPropertyResourceService.listNameProperty();
        listTypeResource = admiPropertyResourceService.listTypeResource();
        listEmailUser = admiPropertyResourceService.listEmilUser();
        propertyResourceDto = new PropertyResourceDto();
        propertyResourceDto.setResId(new ResourceDto());
        propertyResourceDto.setProId(new PropertyDto());
    }
}
