package co.edu.unbosque.livingcorp.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table (name = "PROPERTY_VISITOR_APPOINTMENT")
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="APPOINTMENT_ID")
    private int idVisitor;
    @Column(name ="VISITOR_NAME")
    private String nameVisitor;
    @Column(name ="ADVISOR_NAME")
    private String advisor;
    @Column(name ="APPOINTMENT_DATETIME")
    private LocalDateTime dateTimeVisitor;
    @ManyToOne
    @JoinColumn(name = "PROPERTY_ID")
    private Property idProperty;

    public Visitor() {
    }

    public Visitor(int idVisitor, String nameVisitor, String advisor, LocalDateTime dateTimeVisitor, Property idProperty) {
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

    public Property getIdProperty() {
        return idProperty;
    }

    public void setIdProperty(Property idProperty) {
        this.idProperty = idProperty;
    }
}
