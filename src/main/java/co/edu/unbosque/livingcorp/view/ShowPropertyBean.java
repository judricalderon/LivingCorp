package co.edu.unbosque.livingcorp.view;

import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.dto.PropertyDto;
import co.edu.unbosque.livingcorp.model.dto.VisitorDto;
import co.edu.unbosque.livingcorp.service.ShowPropertyService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named(value = "showPropertyBean")
@RequestScoped
public class ShowPropertyBean implements Serializable {
    private static final Logger logger = Logger.getLogger(ShowPropertyBean.class);
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
                logger.info("Visitor created");
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Visitor appointment already exists"));
                logger.warn("Visitor already exists");
            }
        } catch (RepetedObjectException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            logger.error(e.getMessage());
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
