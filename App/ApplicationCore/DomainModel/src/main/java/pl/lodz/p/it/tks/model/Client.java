package pl.lodz.p.it.tks.model;

import java.util.UUID;

public class Client {

    private ClientType type;
    //mozesz zmienic na longa jeszcze jak
    private int id;
    private UUID key;
    private String name;
    private String surname;
    private boolean isActive;

    public Client(){
        type = new NormalClient();
    }

    public Client(int id, String name, String surname, ClientType type, UUID key) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.isActive = true;
        this.type = type;
        this.key = key;
    }

    public int getId() {
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

    public void setId(int id) {
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

    public UUID getKey() {
        return key;
    }

    public void setKey(UUID key) {
        this.key = key;
    }

    public double getDiscount(double base){
        return type.countDiscount(base);
    }
}
