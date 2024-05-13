package co.edu.unbosque.livingcorp.view;
import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.dto.ResourceDto;
import co.edu.unbosque.livingcorp.service.AdmiResourceService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named(value = "admiResourceBean")
@RequestScoped
public class AdmiResourceBean  {


    @Inject
    private AdmiResourceService admiResourceService;
    private ResourceDto resourceDto;

    @PostConstruct
    public void init() {
        update();
    }


    public void createResource(){
        try{
            System.out.println(resourceDto.getDescription()+" "+resourceDto.getType());
            boolean validacion = admiResourceService.create(resourceDto);
            if(validacion){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Resource created"));
                System.out.println("Resource created");
            }else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Resource already exists"));
                System.out.println("Resource already exists");
            }
        } catch (RepetedObjectException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            System.out.println(e.getMessage());
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
        System.out.println("se actualiza los datos");
    }
}
