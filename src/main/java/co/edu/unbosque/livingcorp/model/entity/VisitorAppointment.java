package co.edu.unbosque.livingcorp.model.entity;

import co.edu.unbosque.livingcorp.model.dto.PropertyDto;
import jakarta.persistence.*;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table (name = "PROPERTY_VISITOR_APPOINTMENT")
public class VisitorAppointment {

    @Id
    @Column(name ="APPOINTMENT_ID")
    private int idVisitor;
    @Column(name ="VISITOR_NAME")
    private String nameVisitor;
    @Column(name ="ADVISOR_NAME")
    private String advisor;
    @Column(name ="APPOINTMENT_DATETIME")
    private LocalDateTime dateTimeVisitor;
    @OneToMany
    @JoinColumn(name = "PROPERTY_ID")
    private List<Property> idProperty;

    public VisitorAppointment() {
    }

    public VisitorAppointment(int idVisitor, String nameVisitor, String advisor, LocalDateTime dateTimeVisitor, List<Property> idProperty) {
        this.idVisitor = idVisitor;
        this.nameVisitor = nameVisitor;
        this.advisor = advisor;
        this.dateTimeVisitor = dateTimeVisitor;
        this.idProperty = idProperty;
    }

    public int getIdVisitor() {
        return idVisitor;
    }

    public void setIdVisitor(int idVisitor) {
        this.idVisitor = idVisitor;
    }

    public String getNameVisitor() {
        return nameVisitor;
    }

    public void setNameVisitor(String nameVisitor) {
        this.nameVisitor = nameVisitor;
    }

    public String getAdvisor() {
        return advisor;
    }

    public void setAdvisor(String advisor) {
        this.advisor = advisor;
    }

    public LocalDateTime getDateTimeVisitor() {
        return dateTimeVisitor;
    }

    public void setDateTimeVisitor(LocalDateTime dateTimeVisitor) {
        this.dateTimeVisitor = dateTimeVisitor;
    }

    public List<Property> getIdProperty() {
        return idProperty;
    }

    public void setIdProperty(List<Property> idProperty) {
        this.idProperty = idProperty;
    }
}
