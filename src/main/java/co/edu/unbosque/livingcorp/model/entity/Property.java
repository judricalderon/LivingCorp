package co.edu.unbosque.livingcorp.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "PROPERTIES")
public class Property {
    @Id
    @Column(name = "PROPERTY_ID")
    private int idProperty;
    @Column(name = "PROPERTY_NAME")
    private String nameProperty;
    @Column(name = "PROPERTY_CITY")
    private String city;
    @Column(name = "PROPERTY_ADDRESS")
    private String address;
    @Column(name = "PROPERTY_AREA")
    private int area;
    @Column(name = "PROPERTY_PRICE")
    private double priceProperty;
    @Column(name = "PROPERTY_ROOMS")
    private int room;
    @Column(name = "PROPERTY_BATHROOMS")
    private String bathroom;
    @Column (name="PROPERTY_DESCRIPCION")
    private String description;
    @ManyToOne
    @JoinColumn(name = "PROPERTY_ADMIN")
    private User admi;
    @Column(name = "IS_AVAILABLE_FOR_RENT")
    private boolean rent;
    @Column(name = "IS_AVAILABLE_FOR_SALE")
    private boolean sale;

    public Property() {
    }

    public Property(int idProperty, String nameProperty, String city, String address, int area, double priceProperty, int room, String description, List<User> admi, boolean rent, boolean sale) {
        this.idProperty = idProperty;
        this.nameProperty = nameProperty;
        this.city = city;
        this.address = address;
        this.area = area;
        this.priceProperty = priceProperty;
        this.room = room;
        this.description = description;
        this.admi = admi;
        this.rent = rent;
        this.sale = sale;
    }

    public int getIdProperty() {
        return idProperty;
    }

    public void setIdProperty(int idProperty) {
        this.idProperty = idProperty;
    }

    public String getNameProperty() {
        return nameProperty;
    }

    public void setNameProperty(String nameProperty) {
        this.nameProperty = nameProperty;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public double getPriceProperty() {
        return priceProperty;
    }

    public void setPriceProperty(double priceProperty) {
        this.priceProperty = priceProperty;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getAdmi() {
        return admi;
    }

    public void setAdmi(List<User> admi) {
        this.admi = admi;
    }

    public boolean isRent() {
        return rent;
    }

    public void setRent(boolean rent) {
        this.rent = rent;
    }

    public boolean isSale() {
        return sale;
    }

    public void setSale(boolean sale) {
        this.sale = sale;
    }
}
