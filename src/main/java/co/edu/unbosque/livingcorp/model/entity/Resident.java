package co.edu.unbosque.livingcorp.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "PROPERTY_RESIDENTS")
public class Resident {
    @Id
    @Column(name = "PROPERTY_RESIDENT_ID")
    private int idResident;
    @ManyToOne
    @JoinColumn(name = "PROPERTY_ID")
    private Property idProperty;
    @ManyToOne
    @Column(name = "USER_NAME")
    private User userName;
    @Column(name = "IS_OWNER")
    private boolean owner;

    public Resident() {
    }

    public Resident(int idResident, Property idProperty, User userName, boolean owner) {
        this.idResident = idResident;
        this.idProperty = idProperty;
        this.userName = userName;
        this.owner = owner;
    }

    public int getIdResident() {
        return idResident;
    }

    public void setIdResident(int idResident) {
        this.idResident = idResident;
    }

    public Property getIdProperty() {
        return idProperty;
    }

    public void setIdProperty(Property idProperty) {
        this.idProperty = idProperty;
    }

    public User getUserName() {
        return userName;
    }

    public void setUserName(User userName) {
        this.userName = userName;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }
}
