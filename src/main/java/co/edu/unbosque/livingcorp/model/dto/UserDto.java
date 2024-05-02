package co.edu.unbosque.livingcorp.model.dto;

import java.time.LocalDate;

public class UserDto {
    private String nameUser;
    private String emailUser;
    private String password;
    private LocalDate lastLogin;
    private boolean blocked;
    private boolean propertyAdmi;
    private boolean resident;

    public UserDto() {}

    public UserDto(String nameUser, String emailUser, String password, LocalDate lastLogin, boolean blocked, boolean propertyAdmi, boolean resident) {
        this.nameUser = nameUser;
        this.emailUser = emailUser;
        this.password = password;
        this.lastLogin = lastLogin;
        this.blocked = blocked;
        this.propertyAdmi = propertyAdmi;
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

    public void setPropertyAdmi(boolean propertyAdmi) {
        this.propertyAdmi = propertyAdmi;
    }

    public boolean isResident() {
        return resident;
    }

    public void setResident(boolean resident) {
        this.resident = resident;
    }
}
