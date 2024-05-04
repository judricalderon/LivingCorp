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
    public ServiceRequestDto{}

    public ServiceRequestDto(int rqstId, LocalDateTime rqstDateTime, UserDto userName, PropertyDto propertyId, ServiceProviderDto svcProviderId, String requestDescription, LocalDateTime svcDateTime){
        this.rqstId = rqstId;
        this.rqstDateTime = rqstDateTime;
        this.userName = userName;
        this.propertyId = propertyId;
        this.svcProviderId = svcProviderId;
        this.requestDescription = requestDescription;
        this.svcDateTime = svcDateTime;
    }
}
