package co.edu.unbosque.livingcorp.view;

import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.dto.PropertyDto;
import co.edu.unbosque.livingcorp.model.dto.VisitorDto;
import co.edu.unbosque.livingcorp.service.ShowPropertyService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named(value = "showPropertyBean")
@ViewScoped
public class ShowPropertyBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Inject
    private ShowPropertyService showPropertyService;
    private List<String> nameUsers;

    private List<PropertyDto> propertiesDto;

    private VisitorDto visitorDto;
    private Date minDateTime;


    @PostConstruct
    public void init() {
        update();
    }

    public void createAppointmentVisitor() {
        try {

            boolean aux = showPropertyService.createAppointmentVisitor(visitorDto);
            if (aux) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Visitor appointment created"));
                System.out.println("Visitor appointment created");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Visitor appointment already exists"));
                System.out.println("Visitor appointment already exists");
            }
        } catch (RepetedObjectException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            System.out.println(e.getMessage());
        }
    }




    public List<PropertyDto> getPropertiesDto() {
        return propertiesDto;
    }

    public void setPropertiesDto(List<PropertyDto> propertiesDto) {
        this.propertiesDto = propertiesDto;
    }

    public List<String> getNameUsers() {
        return nameUsers;
    }

    public void setNameUsers(List<String> nameUsers) {
        this.nameUsers = nameUsers;
    }

    public VisitorDto getVisitorDto() {
        return visitorDto;
    }

    public void setVisitorDto(VisitorDto visitorDto) {
        this.visitorDto = visitorDto;
    }

    public Date getMinDateTime() {
        return minDateTime;
    }

    public void setMinDateTime(Date minDateTime) {
        this.minDateTime = minDateTime;
    }



    public void update() {
        propertiesDto = showPropertyService.listPropertyObject();
        nameUsers = showPropertyService.listNameUser();
        visitorDto = new VisitorDto();
        visitorDto.setIdProperty(new PropertyDto());
        Date today = new Date();
        minDateTime = new Date(today.getTime());

    }

}
