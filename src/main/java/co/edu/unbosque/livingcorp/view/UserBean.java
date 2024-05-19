package co.edu.unbosque.livingcorp.view;


import co.edu.unbosque.livingcorp.exception.DontExistException;
import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.dto.*;
import co.edu.unbosque.livingcorp.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {
    private static final Logger logger = Logger.getLogger(UserBean.class);
    private static final long serialVersionUID = 1L;
    @Inject
    private UserService userService;
    private List<PropertyResourceDto> propertyResources;
    private ResourceBookingDto resourceBookingDto;
    private PropertyResourceDto propertyResource;
    private UserDto userDto;


    @PostConstruct
    public void init() {
        try {
            update();
        }catch (DontExistException e){
            logger.error(e);
        }
    }
    public String ValorFinal(){
        if(userService.calcularTiempoMin(resourceBookingDto)) {
            resourceBookingDto.setBookingCost(userService.calcularPrecio(resourceBookingDto));
            logger.info("Calculated value");
            return "panelPago.xhtml";
        }else {
            logger.warn("Uncalculated value");
            return "panelUser.xhtml";
        }
    }
    public String prepareResourceBooking(PropertyResourceDto propertyResourceDto) {
        resourceBookingDto.setPropertyResourceId(propertyResourceDto);
        resourceBookingDto.setUserName(userDto.getNameUser());
        logger.info("Preparing resource booking");
        return "panelBookingResource.xhtml";
    }
    public String createResourceBooking(){
        try{
            if(userService.crearReserva(resourceBookingDto,userDto)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Booking Resource created"));
                logger.info("Booking Resource created");
                return "panelUser.xhtml";
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Booking Resource appointment already exists"));
                logger.info("Booking Resource appointment already exists");
                return "panelUser.xhtml";
            }
        }catch (RepetedObjectException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            logger.error("Error creating resource booking");
            return "panelUser.xhtml";
        }
    }
    public void update() throws DontExistException {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.getAttribute("userLogIn");

        userDto= (UserDto) session.getAttribute("userLogIn");
        resourceBookingDto = new ResourceBookingDto();
        propertyResource = new PropertyResourceDto();
        propertyResource.setProId(new PropertyDto());
        propertyResource.setResId(new ResourceDto());
        resourceBookingDto.setPropertyResourceId(propertyResource);
        resourceBookingDto.setUserName(userDto.getNameUser());
        resourceBookingDto.setBookingDateTime(LocalDateTime.now());
        propertyResources = userService.obtenerReservas(userDto);
        LocalDateTime now = LocalDateTime.now();
        resourceBookingDto.setBookingStartDate(now.plusDays(3));
        resourceBookingDto.setBookingEndDate(now.plusDays(3).plusHours(5));
        session.setAttribute("propertyResource",propertyResources.get(0));
    }

    public List<PropertyResourceDto> getPropertyResources() {
        return propertyResources;
    }

    public void setPropertyResources(List<PropertyResourceDto> propertyResources) {
        this.propertyResources = propertyResources;
    }

    public ResourceBookingDto getResourceBookingDto() {
        return resourceBookingDto;
    }

    public void setResourceBookingDto(ResourceBookingDto resourceBookingDto) {
        this.resourceBookingDto = resourceBookingDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
