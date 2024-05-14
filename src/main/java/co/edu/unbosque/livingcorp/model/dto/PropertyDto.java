package co.edu.unbosque.livingcorp.model.dto;



public class PropertyDto {


    private int idProperty;
    private String nameProperty;
    private String city;
    private String address;
    private int area;
    private double priceProperty;
    private int room;
    private String bathroom;
    private String description;
    private UserDto propertyAdmin;
    private boolean rent;
    private boolean sale;

    public PropertyDto() {
    }

    public PropertyDto(int idProperty, String nameProperty, String city, String address, int area, double priceProperty, int room, String bathroom, String description, UserDto propertyAdmin, boolean rent, boolean sale) {
        this.idProperty = idProperty;
        this.nameProperty = nameProperty;
        this.city = city;
        this.address = address;
        this.area = area;
        this.priceProperty = priceProperty;
        this.room = room;
        this.bathroom = bathroom;
        this.description = description;
        this.propertyAdmin = propertyAdmin;
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

    public String getBathroom() {
        return bathroom;
    }

    public void setBathroom(String bathroom) {
        this.bathroom = bathroom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserDto getPropertyAdmin() {
        return propertyAdmin;
    }

    public void setPropertyAdmin(UserDto propertyAdmin) {
        this.propertyAdmin = propertyAdmin;
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

    @Override
    public String toString() {
        return "PropertyDto{" +
                "idProperty=" + idProperty +
                ", nameProperty='" + nameProperty + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", area=" + area +
                ", priceProperty=" + priceProperty +
                ", room=" + room +
                ", bathroom='" + bathroom + '\'' +
                ", description='" + description + '\'' +
                ", propertyAdmin=" + propertyAdmin +
                ", rent=" + rent +
                ", sale=" + sale +
                '}';
    }
}
