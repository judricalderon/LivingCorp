package co.edu.unbosque.livingcorp.view;
import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.dto.ResourceDto;
import co.edu.unbosque.livingcorp.model.dto.UserDto;
import co.edu.unbosque.livingcorp.service.AdmiResourceService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;

@Named(value = "admiResourceBean")
@RequestScoped
public class AdmiResourceBean  {
    private static final Logger logger = Logger.getLogger(AdmiResourceBean.class);

    @Inject
    private AdmiResourceService admiResourceService;
    private ResourceDto resourceDto;
    private UserDto userDto;
    @PostConstruct
    public void init() {
        update();
    }
    public void createResource(){

        try{
            boolean validacion = admiResourceService.create(resourceDto,userDto);
            if(validacion){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Resource created"));
                logger.info("Resource created");
            }else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Resource already exists"));
                logger.warn("Resource already exists");
            }
        } catch (RepetedObjectException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            logger.error(e.getMessage());
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.getAttribute("userLogIn");
            userDto= (UserDto) session.getAttribute("userLogIn");
        }

    }

    public ResourceDto getResourceDto() {
        return resourceDto;
    }

    public void setResourceDto(ResourceDto resourceDto) {
        this.resourceDto = resourceDto;
    }
    public void update(){
        resourceDto = new ResourceDto();
    }
}
