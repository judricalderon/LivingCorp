package co.edu.unbosque.livingcorp.view;

import co.edu.unbosque.livingcorp.model.dto.PropertyDto;
import co.edu.unbosque.livingcorp.service.ShowPropertyService;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named(value = "showPrpertyBean")
public class ShowPropertyBean {
    @Inject
    private ShowPropertyService showPropertyService;
    private List<PropertyDto> propertiesDto;

    public ShowPropertyBean() {
        update();
    }
    

    public void update(){
        propertiesDto = showPropertyService.listProperty();
    }
}
