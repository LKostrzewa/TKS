package pl.lodz.p.it.tks.model;

import java.util.UUID;

public class Client {

    private ClientType type;
    //mozesz zmienic na longa jeszcze jak
    private String id;
    //zalezy od łączenia dziada tutaj wstawiam prototypa
    private UUID key;
    private String name;
    private String surname;
    private boolean isActive;

    public Client(){
        type = new NormalClient();
    }

    public Client(String id, String name, String surname, ClientType type) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.isActive = true;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        String tmp;
        if(isActive()){
            tmp = "is active";
        }
        else tmp = "is inactive";
        return "Client login " + getId() + " full name " + getName()
                + " " + getSurname() + " " + getType() + " type " + tmp;
    }

    public ClientType getType() {
        return type;
    }

    public void setType(ClientType type) {
        this.type = type;
    }

    public double getDiscount(double base){
        return type.countDiscount(base);
    }
}
