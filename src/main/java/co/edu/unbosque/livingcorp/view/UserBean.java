package co.edu.unbosque.livingcorp.view;


import co.edu.unbosque.livingcorp.exception.DontExistException;
import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.dto.PropertyResourceDto;
import co.edu.unbosque.livingcorp.model.dto.ResourceBookingDto;
import co.edu.unbosque.livingcorp.model.dto.UserDto;
import co.edu.unbosque.livingcorp.model.entity.PropertyResource;
import co.edu.unbosque.livingcorp.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import java.io.Serializable;
import java.util.List;

@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Inject
    private UserService userService;
    private int horas;
    private List<PropertyResource> propertyResources;
    private ResourceBookingDto resourceBookingDto;
    private PropertyResourceDto propertyResource;

    @PostConstruct
    public void init() {
        try {
            update();
        }catch (DontExistException e){
            System.out.println(e.getMessage());
        }
    }
    public void calcularPrecio(){
        resourceBookingDto.setBookingCost(userService.calcularPrecio(propertyResource,horas));
    }
    public void createResourceBooking(){
        try{
            if(userService.crearReserva(resourceBookingDto)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Booking Resource created"));
                System.out.println("Visitor appointment created");
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Booking Resource appointment already exists"));
                System.out.println("Booking Resource already exists");
            }
        }catch (RepetedObjectException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            System.out.println(e.getMessage());
        }
    }
    public void update() throws DontExistException {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.getAttribute("userLogIn");
        UserDto userDto= (UserDto) session.getAttribute("userLogIn");
        resourceBookingDto = new ResourceBookingDto();
        propertyResource = new PropertyResourceDto();
        resourceBookingDto.setPropertyResourceId(propertyResource);
        resourceBookingDto.setUserName(userDto);
        propertyResources = userService.obtenerReservas(userDto);


    }


}
