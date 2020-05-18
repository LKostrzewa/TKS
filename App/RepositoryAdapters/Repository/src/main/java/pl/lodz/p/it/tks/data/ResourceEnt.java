package pl.lodz.p.it.tks.data;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "resource")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ResourceEnt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotBlank(message = "Empty ID given")
    private int id;

    @Column(name = "price")
    @Min(value = 0, message = "Price has to be positive decimal")
    private double price;

    public ResourceEnt(){

    }

    public ResourceEnt(int id, double price) {
        this.id = id;
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("id ").append(id);
        sb.append(", costs ").append(price);
        return sb.toString();
    }
}
