package pl.lodz.p.it.tks.dto;

import java.time.LocalDateTime;

public class ReservationDTO {

    private String id;
    private ResourceDTO resource;
    private ClientDTO client;
    private LocalDateTime beginning;
    private LocalDateTime ending;

    public ReservationDTO(){
        beginning = LocalDateTime.now();
    }

    public ReservationDTO(String id, ResourceDTO resource, ClientDTO client, LocalDateTime beginning) {
        this.id = id;
        this.resource = resource;
        this.client = client;
        this.beginning = beginning;
    }

    public String getId() {
        return id;
    }

    public ResourceDTO getResource() {
        return resource;
    }

    public ClientDTO getClient() {
        return client;
    }

    public LocalDateTime getBeginning() {
        return beginning;
    }

    public void setEnding(LocalDateTime ending) {
        this.ending = ending;
    }

    public LocalDateTime getEnding() {
        return ending;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setResource(ResourceDTO resource) {
        this.resource = resource;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public void setBeginning(LocalDateTime beginning) {
        this.beginning = beginning;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Reservation ");
        sb.append("id ").append(id).append(" of");
        sb.append(" resource ").append(resource.getId()).append(" by");
        sb.append(" client ").append(client.getId());
        sb.append(", begins ").append(beginning);
        sb.append(", ends ").append(ending);
        return sb.toString();
    }
}
