package pl.lodz.p.it.tks.dto;

import java.util.UUID;

//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ClientDTO {

    //@JsonIgnore
    //private ClientTypeDTO type;
    private int id;
    private String name;
    private String surname;
    private boolean active;
    private String clientType;
    private UUID key;

    public ClientDTO(){
        clientType = "Normal";
    }

    public ClientDTO(int id, String name, String surname, String type) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.active = true;
        this.clientType = type;
        this.key = UUID.randomUUID();
    }

    public ClientDTO(UUID key, String name, String surname, boolean active) {
        this.name = name;
        this.surname = surname;
        this.active = active;
        this.clientType = "Normal";
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
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
                + " " + getSurname() + " " + getClientType() + " type " + tmp;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public UUID getKey() {
        return key;
    }

    public void setKey(UUID key) {
        this.key = key;
    }
}
