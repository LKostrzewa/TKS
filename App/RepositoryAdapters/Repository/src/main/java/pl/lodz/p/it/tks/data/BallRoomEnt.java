package pl.lodz.p.it.tks.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name = "ball_room")
public class BallRoomEnt extends ResourceEnt {

    @Column(name = "description")
    private String description;
    @Min(value = 0, message = "Number of rooms has to be positive integer")
    @Column(name = "num_of_rooms")
    private int numOfRooms;

    public BallRoomEnt(){

    }

    public BallRoomEnt(String id, double price, String description, int numOfRooms) {
        super(id, price);
        this.description = description;
        this.numOfRooms = numOfRooms;
    }

    public String getDescription() {
        return description;
    }

    public int getNumOfRooms() {
        return numOfRooms;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNumOfRooms(int numOfRooms) {
        this.numOfRooms = numOfRooms;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BallRoom ");
        sb.append(super.toString());
        sb.append(", ").append(description);
        sb.append(", has ").append(numOfRooms);
        sb.append(" rooms");
        return sb.toString();
    }
}
