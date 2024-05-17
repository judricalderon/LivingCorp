package co.edu.unbosque.livingcorp.view;

import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.dto.PropertyDto;
import co.edu.unbosque.livingcorp.model.dto.ResidentDto;
import co.edu.unbosque.livingcorp.model.dto.UserDto;
import co.edu.unbosque.livingcorp.service.AdmiUserService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named(value = "admiUserBean")
@ViewScoped
public class AdmiUserBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Inject
    private AdmiUserService admiUserService;
    private UserDto userDto;
    private ResidentDto residentDto;


    @PostConstruct
    public void inti() {
        update();
    }

    public String createUser(UserDto userDto) {
        try {
            if (admiUserService.createUser(userDto)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "UserAdmi created"));
                System.out.println("UserAdmi created");
                return "is admi";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "User created"));
                System.out.println("User created");
                return "isnt admi";
            }
        } catch (RepetedObjectException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            System.out.println(e.getMessage());
        } finally {
            return "index.xhtml";
        }
    }

    public void createResident(ResidentDto residentDto) {
        try {
            if (admiUserService.createPropertyResident(residentDto)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Resident created"));
                System.out.println("Resident created");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Resident already exists"));
                System.out.println("Resident already exists");
            }
        } catch (RepetedObjectException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            System.out.println(e.getMessage());
        }
    }

    public void update() {
        userDto = new UserDto();
        residentDto = new ResidentDto();
        residentDto.setIdProperty(new PropertyDto());
        residentDto.setUserName(new UserDto());
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
}
