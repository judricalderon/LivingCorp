package co.edu.unbosque.livingcorp.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name="LIVINGCORPDB")
public class User {

    @Id
    @Column(name="USER_NAME")
    private String nameUser;
    @Column(name="USER_EMAIL")
    private String emailUser;
    @Column (name = "USER_PASSWORD")
    private String password;
    @Column (name = "LAST_LOGIN")
    private LocalDate lastLogin;
    @Column (name = "IS_BLOCKED")
    private boolean blocked;
    @Column (name = "IS_PROPERTY_ADMIN")
    private boolean propert@yAdmi;
    @Column (name = "IS_RESIDENT_PPRTY_OWNER")
    private boolean resident;

    public User() {
    }

    public User(String nameUser, String emailUser, String password, LocalDate lastLogin, boolean blocked, boolean propiedadAdmi, boolean resident) {
        this.nameUser = nameUser;
        this.emailUser = emailUser;
        this.password = password;
        this.lastLogin = lastLogin;
        this.blocked = blocked;
        this.propertyAdmi = propiedadAdmi;
        this.resident = resident;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDate lastLogin) {
        this.lastLogin = lastLogin;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isPropertyAdmi() {
        return propertyAdmi;
    }

    public void setPropertyAdmi(boolean propiedadAdmi) {
        this.propertyAdmi = propiedadAdmi;
    }

    public boolean isResident() {
        return resident;
    }

    public void setResident(boolean resident) {
        this.resident = resident;
    }
}
