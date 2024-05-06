package co.edu.unbosque.livingcorp.model.dto;

import java.time.LocalDateTime;

public class ServiceRequestDto {

    private int rqstId;
    private LocalDateTime rqstDateTime;
    private UserDto userName;
    private PropertyDto propertyId;
    private ServiceProviderDto svcProviderId;
    private String requestDescription;
    private LocalDateTime svcDateTime;
    public ServiceRequestDto() {}

    public ServiceRequestDto(int rqstId, LocalDateTime rqstDateTime, UserDto userName, PropertyDto propertyId, ServiceProviderDto svcProviderId, String requestDescription, LocalDateTime svcDateTime){
        this.rqstId = rqstId;
        this.rqstDateTime = rqstDateTime;
        this.userName = userName;
        this.propertyId = propertyId;
        this.svcProviderId = svcProviderId;
        this.requestDescription = requestDescription;
        this.svcDateTime = svcDateTime;
    }

    public int getRqstId() {
        return rqstId;
    }

    public void setRqstId(int rqstId) {
        this.rqstId = rqstId;
    }

    public LocalDateTime getRqstDateTime() {
        return rqstDateTime;
    }

    public void setRqstDateTime(LocalDateTime rqstDateTime) {
        this.rqstDateTime = rqstDateTime;
    }

    public UserDto getUserName() {
        return userName;
    }

    public void setUserName(UserDto userName) {
        this.userName = userName;
    }

    public PropertyDto getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(PropertyDto propertyId) {
        this.propertyId = propertyId;
    }

    public ServiceProviderDto getSvcProviderId() {
        return svcProviderId;
    }

    public void setSvcProviderId(ServiceProviderDto svcProviderId) {
        this.svcProviderId = svcProviderId;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    public LocalDateTime getSvcDateTime() {
        return svcDateTime;
    }

    public void setSvcDateTime(LocalDateTime svcDateTime) {
        this.svcDateTime = svcDateTime;
    }
}
