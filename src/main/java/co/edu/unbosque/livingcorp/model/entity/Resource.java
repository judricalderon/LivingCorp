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
    private String descripcion;
    @Column(name = "RESOURCE_TYPE")
    private String type;

    public Resource() {
    }

    public Resource(int idResource, String descripcion, String type) {
        this.idResource = idResource;
        this.descripcion = descripcion;
        this.type = type;
    }

    public int getIdResource() {
        return idResource;
    }

    public void setIdResource(int idResource) {
        this.idResource = idResource;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
