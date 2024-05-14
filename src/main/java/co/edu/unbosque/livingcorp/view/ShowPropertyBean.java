package co.edu.unbosque.livingcorp.view;

import co.edu.unbosque.livingcorp.model.dto.PropertyDto;
import co.edu.unbosque.livingcorp.model.dto.VisitorDto;
import co.edu.unbosque.livingcorp.service.AdmiPropertyResourceService;
import co.edu.unbosque.livingcorp.service.ShowPropertyService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named(value = "showPropertyBean")
@RequestScoped
public class ShowPropertyBean {
    @Inject
    private ShowPropertyService showPropertyService;
    @Inject
    private AdmiPropertyResourceService admiPropertyResourceService;
    private List<String> nameUsers;

    private List<PropertyDto> propertiesDto;

    private VisitorDto visitorDto;

    @PostConstruct
    public void init() {
        update();
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



    public void update(){
        propertiesDto = showPropertyService.listPropertyObject();
        nameUsers = admiPropertyResourceService.listNameUser();

        }

}
