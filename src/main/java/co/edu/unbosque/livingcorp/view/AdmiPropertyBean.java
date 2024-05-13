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
import java.util.List;

@Named (value = "admiPropertyBean")
@RequestScoped
public class AdmiPropertyBean  {

    @Inject
    private AdmiPropertiesService admiPropertiesService;
    private PropertyDto propertyDto;
    private List<String> administradores;
    private String Administrador;

    @PostConstruct
    public void init() {
        update();
    }
    public void createProperty() throws RepetedObjectException {
        try{

            for(String aux : administradores){
                System.out.println(aux);
            }

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
        } catch (DontExistException e) {
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

    public List<String> getAdministradores() {
        return administradores;
    }

    public void setAdministradores(List<String> administradores) {
        this.administradores = administradores;
    }

    public String getAdministrador() {
        return Administrador;
    }

    public void setAdministrador(String administrador) {
        Administrador = administrador;
    }
    public void update(){
        propertyDto = new PropertyDto();
        propertyDto.setPropertyAdmin(new UserDto());
        administradores = admiPropertiesService.listarAdmi();
        for(String aux : administradores){
            System.out.println(aux);
        }
        System.out.println("se actualiza los datos");
    }
}
