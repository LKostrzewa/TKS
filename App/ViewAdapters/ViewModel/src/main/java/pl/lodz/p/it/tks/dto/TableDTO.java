package pl.lodz.p.it.tks.dto;

public class TableDTO extends ResourceDTO {

    private int number;
    private int numOfPeople;

    public TableDTO(){

    }

    public TableDTO(int id, double price, int number, int numOfPeople) {
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
