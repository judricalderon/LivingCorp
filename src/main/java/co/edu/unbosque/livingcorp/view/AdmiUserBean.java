package co.edu.unbosque.livingcorp.view;

import co.edu.unbosque.livingcorp.exception.PasswordNotEncryptedException;
import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.dto.PropertyDto;
import co.edu.unbosque.livingcorp.model.dto.ResidentDto;
import co.edu.unbosque.livingcorp.model.dto.UserDto;
import co.edu.unbosque.livingcorp.service.AdmiPropertyResourceService;
import co.edu.unbosque.livingcorp.service.AdmiUserService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named(value = "admiUserBean")
@SessionScoped
public class AdmiUserBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Inject
    private AdmiUserService admiUserService;
    @Inject
    private AdmiPropertyResourceService admiPropertyResourceService;;
    private UserDto userDto;
    private ResidentDto residentDto;

    private List<String> listNameProperty;


    @PostConstruct
    public void inti() {
        update();
    }

    public String createUser() {
        try {
            if (admiUserService.createUser(userDto)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "UserAdmi created"));
                System.out.println("UserAdmi created");
                return "panelAdmiUser.xhtml";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "User created"));
                System.out.println("User created");
                return "panelAdmiResident.xhtml";
            }
        } catch (RepetedObjectException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            System.out.println(e.getMessage());
            return "panelAdmiResource.xhtml";
        } catch (PasswordNotEncryptedException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            System.out.println(e.getMessage());
            return "panelAdmiResource.xhtml";
        }

    }

    public String createResident() {
        try {
            residentDto.setUserName(userDto);
            residentDto.setIdProperty(admiPropertyResourceService.findPropertyByName(residentDto.getIdProperty()));
            residentDto.setOwner(userDto.isResident());
            if (admiUserService.createPropertyResident(residentDto)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Resident created"));
                System.out.println("Resident created");
                return "panelAdmiUser.xhtml";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Resident already exists"));
                System.out.println("Resident already exists");
                return "panelAdmiResident.xhtml";
            }
        } catch (RepetedObjectException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            System.out.println(e.getMessage());
            return "panelAdmiResource.xhtml";
        }
    }

    public void update() {
        userDto = new UserDto();
        residentDto = new ResidentDto();
        residentDto.setIdProperty(new PropertyDto());
        residentDto.setUserName(new UserDto());
        listNameProperty = admiPropertyResourceService.listNameProperty();
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public ResidentDto getResidentDto() {
        return residentDto;
    }

    public void setResidentDto(ResidentDto residentDto) {
        this.residentDto = residentDto;
    }

    public List<String> getListNameProperty() {
        return listNameProperty;
    }

    public void setListNameProperty(List<String> listNameProperty) {
        this.listNameProperty = listNameProperty;
    }
}
