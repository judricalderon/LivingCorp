package co.edu.unbosque.livingcorp.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "SVC_PROVIDERS")
public class ServiceProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROVIDER_ID")
    private int providerId;

    @Column(name = "PROVIDER_EMAIL")
    private String providerEmail;

    @Column(name = "SERVICE_DESCRIPTION")
    private String serviceDescription;

    @Column(name = "SERVICE_TYPE")
    private String serviceType;

    @Column(name = "SERVICE_PRICE")
    private double servicePrice;

    public ServiceProvider() {
    }

    public ServiceProvider(int providerId, String providerEmail, String serviceDescription, String serviceType, double servicePrice) {
        this.providerId = providerId;
        this.providerEmail = providerEmail;
        this.serviceDescription = serviceDescription;
        this.serviceType = serviceType;
        this.servicePrice = servicePrice;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public String getProviderEmail() {
        return providerEmail;
    }

    public void setProviderEmail(String providerEmail) {
        this.providerEmail = providerEmail;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }
}
