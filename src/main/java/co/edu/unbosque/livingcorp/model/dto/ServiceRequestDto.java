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

}
