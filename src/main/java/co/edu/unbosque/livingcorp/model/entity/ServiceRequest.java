package co.edu.unbosque.livingcorp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "SERVICE_REQUESTS")
public class ServiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RQST_ID")
    private int rqstId;

    @Column(name = "RQST_DATETIME")
    private LocalDateTime rqstDateTime;
    @ManyToOne
    @JoinColumn(name = "USER_NAME")
    private User userName;
    @ManyToOne
    @JoinColumn(name = "PROPERTY_ID")
    private Property propertyId;
    @ManyToOne
    @JoinColumn(name = "SVC_PROVIDER_ID")
    private ServiceProvider svcProviderId;

    @Column(name = "REQUEST_DESCRIPTION")
    private String requestDescription;

    @Column(name = "SVC_DATETIME")
    private LocalDateTime svcDateTime;

    public ServiceRequest() {
    }

    public ServiceRequest(int rqstId, LocalDateTime rqstDateTime, User userName, Property propertyId, ServiceProvider svcProviderId, String requestDescription, LocalDateTime svcDateTime) {
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

    public User getUserName() {
        return userName;
    }

    public void setUserName(User userName) {
        this.userName = userName;
    }

    public Property getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Property propertyId) {
        this.propertyId = propertyId;
    }

    public ServiceProvider getSvcProviderId() {
        return svcProviderId;
    }

    public void setSvcProviderId(ServiceProvider svcProviderId) {
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
