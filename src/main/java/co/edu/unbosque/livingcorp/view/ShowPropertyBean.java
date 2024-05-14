package co.edu.unbosque.livingcorp.view;

import co.edu.unbosque.livingcorp.model.dto.PropertyDto;
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
    private List<PropertyDto> propertiesDto;

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

    public void update(){
        propertiesDto = showPropertyService.listProperty();
        for(PropertyDto propertyDto : propertiesDto){
            System.out.println(propertyDto);
        }
    }
}
