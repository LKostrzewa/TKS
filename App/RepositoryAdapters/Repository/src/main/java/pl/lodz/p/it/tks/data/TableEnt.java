package pl.lodz.p.it.tks.data;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "table_")
public class TableEnt extends ResourceEnt {

    @Column(name = "number")
    @Min(value = 0, message = "Number has to be positive integer")
    private int number;
    @Column(name = "num_of_people")
    @Min(value = 0, message = "Number of people has to be positive integer")
    private int numOfPeople;

    public TableEnt(){

    }

    public TableEnt(String id, double price, int number, int numOfPeople) {
        super(id, price);
        this.number = number;
        this.numOfPeople = numOfPeople;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setNumOfPeople(int numOfPeople) {
        this.numOfPeople = numOfPeople;
    }

    public int getNumber() {
        return number;
    }

    public int getNumOfPeople() {
        return numOfPeople;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Table ");
        sb.append(super.toString());
        sb.append(" number ").append(number);
        sb.append(", can hold ").append(numOfPeople);
        sb.append(" people");
        return sb.toString();
    }
}
