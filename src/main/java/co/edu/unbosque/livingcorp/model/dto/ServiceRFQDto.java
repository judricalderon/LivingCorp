package co.edu.unbosque.livingcorp.model.dto;

import java.time.LocalDateTime;

public class ServiceRFQDto {
    private int rfqId;
    private LocalDateTime rfqDateTime;
    private UserDto userName;
    private PropertyDto propertyId;
    private ServiceProviderDto svcProviderId;
    private String requestDescription;

    public ServiceRFQDto() {
    }

    public ServiceRFQDto(int rfqId, LocalDateTime rfqDateTime, UserDto userName, PropertyDto propertyId, ServiceProviderDto svcProviderId, String requestDescription) {
        this.rfqId = rfqId;
        this.rfqDateTime = rfqDateTime;
        this.userName = userName;
        this.propertyId = propertyId;
        this.svcProviderId = svcProviderId;
        this.requestDescription = requestDescription;
    }

    public int getRfqId() {
        return rfqId;
    }

    public void setRfqId(int rfqId) {
        this.rfqId = rfqId;
    }

    public LocalDateTime getRfqDateTime() {
        return rfqDateTime;
    }

    public void setRfqDateTime(LocalDateTime rfqDateTime) {
        this.rfqDateTime = rfqDateTime;
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

    @Override
    public String toString() {
        return "ServiceRFQDto{" +
                "rfqId=" + rfqId +
                ", rfqDateTime=" + rfqDateTime +
                ", userName=" + userName +
                ", propertyId=" + propertyId +
                ", svcProviderId=" + svcProviderId +
                ", requestDescription='" + requestDescription + '\'' +
                '}';
    }
}
