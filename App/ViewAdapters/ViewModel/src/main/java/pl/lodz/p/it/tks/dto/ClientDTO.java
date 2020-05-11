package pl.lodz.p.it.tks.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ClientDTO {

    @JsonIgnore
    private ClientTypeDTO type;
    private String id;
    private String name;
    private String surname;
    private boolean isActive;

    public ClientDTO(){
        type = new NormalClientDTO();
    }

    public ClientDTO(String id, String name, String surname, ClientTypeDTO type) {
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

    public ClientTypeDTO getType() {
        return type;
    }

    public void setType(ClientTypeDTO type) {
        this.type = type;
    }

    public double getDiscount(double base){
        return type.countDiscount(base);
    }
}
