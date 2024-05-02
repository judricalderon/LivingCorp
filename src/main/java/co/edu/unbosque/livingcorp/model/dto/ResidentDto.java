package co.edu.unbosque.livingcorp.model.dto;

public class ResidentDto {

    private int idResident;
    private PropertyDto idProperty;
    private UserDto userName;
    private boolean owner;

    public ResidentDto(int idResident, PropertyDto idProperty, UserDto userName, boolean owner) {
        this.idResident = idResident;
        this.idProperty = idProperty;
        this.userName = userName;
        this.owner = owner;
    }

    public ResidentDto() {
    }

    public int getIdResident() {
        return idResident;
    }

    public void setIdResident(int idResident) {
        this.idResident = idResident;
    }

    public PropertyDto getIdProperty() {
        return idProperty;
    }

    public void setIdProperty(PropertyDto idProperty) {
        this.idProperty = idProperty;
    }

    public UserDto getUserName() {
        return userName;
    }

    public void setUserName(UserDto userName) {
        this.userName = userName;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }
}
