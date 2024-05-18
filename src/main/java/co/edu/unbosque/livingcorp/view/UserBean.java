package co.edu.unbosque.livingcorp.view;


import co.edu.unbosque.livingcorp.exception.DontExistException;
import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.dto.*;
import co.edu.unbosque.livingcorp.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Inject
    private UserService userService;
    private int horas;
    private List<PropertyResourceDto> propertyResources;
    private ResourceBookingDto resourceBookingDto;
    private PropertyResourceDto propertyResource;
    private UserDto userDto;
    private Date minDateTime;
    private Date today;

    @PostConstruct
    public void init() {
        try {
            update();
        }catch (DontExistException e){
            System.out.println(e.getMessage());
        }
    }
    public String ValorFinal(){



        System.out.println("calcula valor de final");
        System.out.println(resourceBookingDto);
        if(userService.calcularTiempoMin(resourceBookingDto)) {
            resourceBookingDto.setBookingCost(userService.calcularPrecio(resourceBookingDto));
            return "panelPago.xhtml";
        }else {
            return "panelUser.xhtml";
        }
    }
    public String prepareResourceBooking(PropertyResourceDto propertyResourceDto) {
        System.out.println("preparaBook");
        System.out.println(resourceBookingDto);
        resourceBookingDto.setPropertyResourceId(propertyResourceDto);
        resourceBookingDto.setUserName(userDto.getNameUser());


        System.out.println(resourceBookingDto);

        return "panelBookingResource.xhtml";
    }
    public String createResourceBooking(){
        try{

            if(userService.crearReserva(resourceBookingDto)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Booking Resource created"));
                System.out.println("Visitor appointment created");
                return "panelUser.xhtml";
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Booking Resource appointment already exists"));
                System.out.println("Booking Resource already exists");
                return "panelUser.xhtml";
            }
        }catch (RepetedObjectException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            System.out.println(e.getMessage());
            return "panelUser.xhtml";
        }
    }
    public void update() throws DontExistException {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        System.out.println("raro");
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
        today = new Date();
        long oneDay = 24 * 60 * 60 * 1000;
        minDateTime = new Date(today.getTime() - (3 * oneDay));;
    }

    public Date getMinDateTime() {
        return minDateTime;
    }

    public void setMinDateTime(Date minDateTime) {
        this.minDateTime = minDateTime;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
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

    public PropertyResourceDto getPropertyResource() {
        return propertyResource;
    }

    public void setPropertyResource(PropertyResourceDto propertyResource) {
        this.propertyResource = propertyResource;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

}
