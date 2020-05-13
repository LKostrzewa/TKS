package pl.lodz.p.it.tks.data;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "client")
public class ClientEnt {

    private transient ClientTypeEnt type;
    @NotBlank(message = "Id cannot be blank")
    @Id
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "is_active")
    private boolean isActive;
    @Column(name = "client_type")
    private String clientType;

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
        switch (clientType) {
            case "Normal ":
                return new NormalClientEnt();
            case "Regular ":
                return new RegularClientEnt();
            case "Premium ":
                return new PremiumClientEnt();
        }
        return type;
    }

    public void setType(ClientTypeEnt type) {
        this.type = type;
        this.clientType = type.toString();
    }

    public double getDiscount(double base){
        return type.countDiscount(base);
    }
}
