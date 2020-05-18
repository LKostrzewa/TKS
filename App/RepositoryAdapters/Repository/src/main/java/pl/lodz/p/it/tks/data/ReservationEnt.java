package pl.lodz.p.it.tks.data;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
public class ReservationEnt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotBlank(message = "id cannot be blank")
    private int id;
    //@Column(name = "resource")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resource", referencedColumnName = "id")
    private ResourceEnt resource;
    //@Column(name = "client")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client", referencedColumnName = "id")
    private ClientEnt client;
    //@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "beginning")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime beginning;
    @Column(name = "ending")
    private LocalDateTime ending;

    public ReservationEnt(){
        beginning = LocalDateTime.now();
    }

    public ReservationEnt(int id, ResourceEnt resource, ClientEnt client, LocalDateTime beginning) {
        this.id = id;
        this.resource = resource;
        this.client = client;
        this.beginning = beginning;
    }

    public int getId() {
        return id;
    }

    public ResourceEnt getResource() {
        return resource;
    }

    public ClientEnt getClient() {
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

    public void setId(int id) {
        this.id = id;
    }

    public void setResource(ResourceEnt resource) {
        this.resource = resource;
    }

    public void setClient(ClientEnt client) {
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
