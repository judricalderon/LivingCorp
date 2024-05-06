package co.edu.unbosque.livingcorp.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserDto {
    private String nameUser;
    private String emailUser;
    private String password;
    private LocalDateTime lastLogin;
    private boolean blocked;
    private boolean propertyAdmin;
    private boolean resident;
    private int attempt;

    public UserDto() {}

    public UserDto(String nameUser, String emailUser, String password, LocalDateTime lastLogin, boolean blocked, boolean propertyAdmin, boolean resident, int attempt) {
        this.nameUser = nameUser;
        this.emailUser = emailUser;
        this.password = password;
        this.lastLogin = lastLogin;
        this.blocked = blocked;
        this.propertyAdmin = propertyAdmin;
        this.resident = resident;
        this.attempt = attempt;
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

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isPropertyAdmin() {
        return propertyAdmin;
    }

    public void setPropertyAdmin(boolean propertyAdmin) {
        this.propertyAdmin = propertyAdmin;
    }

    public boolean isResident() {
        return resident;
    }

    public void setResident(boolean resident) {
        this.resident = resident;
    }

    public int getAttempt() {
        return attempt;
    }

    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "nameUser='" + nameUser + '\'' +
                ", emailUser='" + emailUser + '\'' +
                ", password='" + password + '\'' +
                ", lastLogin=" + lastLogin +
                ", blocked=" + blocked +
                ", propertyAdmin=" + propertyAdmin +
                ", resident=" + resident +
                ", attempt=" + attempt +
                '}';
    }
}
