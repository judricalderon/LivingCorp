package co.edu.unbosque.livingcorp.model.dto;

public class ResourceDto {

    private int idResource;
    private String description;
    private String type;
    public ResourceDto() {}

    public ResourceDto(int idResource, String description, String type) {
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

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
