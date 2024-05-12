package co.edu.unbosque.livingcorp.model.entity;

import jakarta.persistence.*;

@Entity
@Table
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESOURCE_ID")
    private int idResource;
    @Column(name = "RESOURCE_DESCRIPCION")
    private String description;
    @Column(name = "RESOURCE_TYPE")
    private String type;

    public Resource() {
    }

    public Resource(int idResource, String description, String type) {
        this.idResource = idResource;
        this.description = description;
        this.type = type;
    }

    public int getIdResource() {
        return idResource;
    }

    public void setIdResource(int idResource) {
        this.idResource = idResource;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String descripcion) {
        this.description = descripcion;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
