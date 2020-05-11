package pl.lodz.p.it.tks.data;

import org.hibernate.validator.constraints.NotBlank;

public class ClientEnt {

    private ClientTypeEnt type;
    @NotBlank(message = "Id cannot be blank")
    private String id;
    private String name;
    private String surname;
    private boolean isActive;

    public ClientEnt(){
        type = new NormalClientEnt();
    }

    public ClientEnt(String id, String name, String surname, ClientTypeEnt type) {
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

    public ClientTypeEnt getType() {
        return type;
    }

    public void setType(ClientTypeEnt type) {
        this.type = type;
    }

    public double getDiscount(double base){
        return type.countDiscount(base);
    }
}
