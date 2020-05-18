package pl.lodz.p.it.tks.dto;


import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

public class ResourceDTO {

    @NotBlank(message = "Empty ID given")
    private int id;
    @Min(value = 0, message = "Price has to be positive decimal")
    private double price;

    public ResourceDTO(){

    }

    public ResourceDTO(int id, double price) {
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
