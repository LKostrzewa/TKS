package pl.lodz.p.it.tks.model;


public class Resource {

    private int id;
    private double price;

    public Resource(){

    }

    public Resource(int id, double price) {
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
