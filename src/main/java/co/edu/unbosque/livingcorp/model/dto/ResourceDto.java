package co.edu.unbosque.livingcorp.model.dto;

public class ResourceDto {

    private int idResource;
    private String descripcion;
    private String type;
    public ResourceDto() {}

    public ResourceDto(int idResource, String descripcion, String type) {
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
