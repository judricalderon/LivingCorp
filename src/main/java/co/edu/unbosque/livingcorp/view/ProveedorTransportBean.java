package co.edu.unbosque.livingcorp.view;

import co.edu.unbosque.livingcorp.exception.FailConectionException;
import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.dto.*;
import co.edu.unbosque.livingcorp.service.ProveedorAPIService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import java.io.Serializable;

@Named (value = "proveedorTransportBean")
@RequestScoped
public class ProveedorTransportBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Inject
    private ProveedorAPIService proveedorAPIService;
    private UserDto userDto;
    private PropertyResourceDto propertyResourceDto;
    private ServiceProviderDto providerDto;
    private ServiceRFQDto serviceRFQDto;

    @PostConstruct
    public void init() {
        update();
    }
    public void createServiceRfq(){

    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public PropertyResourceDto getPropertyResourceDto() {
        return propertyResourceDto;
    }

    public void setPropertyResourceDto(PropertyResourceDto propertyResourceDto) {
        this.propertyResourceDto = propertyResourceDto;
    }

    public ServiceProviderDto getProviderDto() {
        return providerDto;
    }

    public void setProviderDto(ServiceProviderDto providerDto) {
        this.providerDto = providerDto;
    }

    public ServiceRFQDto getServiceRFQDto() {
        return serviceRFQDto;
    }

    public void setServiceRFQDto(ServiceRFQDto serviceRFQDto) {
        this.serviceRFQDto = serviceRFQDto;
    }

    public void update() {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.getAttribute("userLogIn");
            userDto = (UserDto) session.getAttribute("userLogIn");
            propertyResourceDto = (PropertyResourceDto) session.getAttribute("propertyResource");
            providerDto = proveedorAPIService.getServiceTrasport();
            serviceRFQDto = new ServiceRFQDto();
            serviceRFQDto.setPropertyId(propertyResourceDto.getProId());
            serviceRFQDto.setUserName(userDto);
            serviceRFQDto.setSvcProviderId(providerDto);
        } catch (FailConectionException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }


}
