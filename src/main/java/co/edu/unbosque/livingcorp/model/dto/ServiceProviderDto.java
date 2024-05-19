package co.edu.unbosque.livingcorp.model.dto;

import com.google.gson.annotations.SerializedName;

public class ServiceProviderDto {
    @SerializedName("id")
    private int providerId;

    @SerializedName("email")
    private String providerEmail;

    @SerializedName("description")
    private String serviceDescription;

    @SerializedName("type")
    private String serviceType;

    @SerializedName("price")
    private double servicePrice;

    public ServiceProviderDto() {
    }

    public ServiceProviderDto(int providerId, String providerEmail, String serviceDescription, String serviceType, double servicePrice) {
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

    @Override
    public String toString() {
        return "ServiceProviderDto{" +
                "providerId=" + providerId +
                ", providerEmail='" + providerEmail + '\'' +
                ", serviceDescription='" + serviceDescription + '\'' +
                ", serviceType='" + serviceType + '\'' +
                ", servicePrice=" + servicePrice +
                '}';
    }
}
