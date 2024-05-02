package co.edu.unbosque.livingcorp.model.dto;

import java.time.LocalDateTime;

public class VisitorDto {
    private int idVisitor;
    private String nameVisitor;
    private String advisor;
    private LocalDateTime dateTimeVisitor;
    private PropertyDto idProperty;

    public VisitorDto() {
    }

    public VisitorDto(int idVisitor, String nameVisitor, String advisor, LocalDateTime dateTimeVisitor, PropertyDto idProperty) {
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

    public PropertyDto getIdProperty() {
        return idProperty;
    }

    public void setIdProperty(PropertyDto idProperty) {
        this.idProperty = idProperty;
    }
}
