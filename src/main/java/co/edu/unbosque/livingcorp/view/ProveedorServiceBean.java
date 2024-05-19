package co.edu.unbosque.livingcorp.view;

import co.edu.unbosque.livingcorp.exception.DontExistException;
import co.edu.unbosque.livingcorp.exception.FailConectionException;
import co.edu.unbosque.livingcorp.exception.RepetedObjectException;
import co.edu.unbosque.livingcorp.model.dto.*;
import co.edu.unbosque.livingcorp.service.ProveedorAPIService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Named (value = "proveedorServiceBean")
@SessionScoped
public class ProveedorServiceBean implements Serializable {
    private static final long serialVersionUID = 1L;
    @Inject
    private ProveedorAPIService proveedorAPIService;
    private UserDto userDto;
    private PropertyResourceDto propertyResourceDto;
    private ServiceProviderDto providerDto;
    private ServiceRFQDto serviceRFQDto;
    private List<String> serviceProviderDtoList;
    private ServiceRequestDto serviceRequestDto;

    @PostConstruct
    public void init() {
        update();
    }

    public void createServiceRfq(){
        try{
            if(proveedorAPIService.createServiceRFQ(serviceRFQDto)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Service request  created"));
            }else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Service request already exists"));
            }
        }catch (RepetedObjectException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }

    }
    public void createServiceRequest(){
        try{
            serviceRequestDto.setRqstDateTime(LocalDateTime.now());
            if(proveedorAPIService.createServiceRequest(serviceRequestDto)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Service request  created"));
            }else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", "Service request already exists"));
            }
        }catch (RepetedObjectException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
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

    public List<String> getServiceProviderDtoList() {
        return serviceProviderDtoList;
    }

    public void setServiceProviderDtoList(List<String> serviceProviderDtoList) {
        this.serviceProviderDtoList = serviceProviderDtoList;
    }

    public ServiceRequestDto getServiceRequestDto() {
        return serviceRequestDto;
    }

    public void setServiceRequestDto(ServiceRequestDto serviceRequestDto) {
        this.serviceRequestDto = serviceRequestDto;
    }

    public void update() {
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.getAttribute("userLogIn");
            userDto = (UserDto) session.getAttribute("userLogIn");
            propertyResourceDto = (PropertyResourceDto) session.getAttribute("propertyResource");
            providerDto = new ServiceProviderDto();
            providerDto = proveedorAPIService.getServiceTransport();
            serviceRFQDto = new ServiceRFQDto();
            serviceRFQDto.setPropertyId(new PropertyDto());
            serviceRFQDto.setSvcProviderId(new ServiceProviderDto());
            serviceRFQDto.setPropertyId(propertyResourceDto.getProId());
            serviceRFQDto.setUserName(userDto);
            serviceRFQDto.setSvcProviderId(providerDto);
            serviceRequestDto= new ServiceRequestDto();
            serviceRequestDto.setPropertyId(new PropertyDto());
            serviceRequestDto.setPropertyId(propertyResourceDto.getProId());
            serviceRequestDto.setUserName(new UserDto());
            serviceRequestDto.setUserName(userDto);
            serviceRequestDto.setSvcProviderId(new ServiceProviderDto());
            serviceRequestDto.setSvcProviderId(providerDto);
            serviceProviderDtoList = proveedorAPIService.getServiceMaintenance();

        } catch (FailConectionException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            System.out.println(e);
        } catch (DontExistException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            System.out.println(e);
        }
    }


}
