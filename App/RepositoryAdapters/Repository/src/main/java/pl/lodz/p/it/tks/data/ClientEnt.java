package pl.lodz.p.it.tks.data;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "client")
public class ClientEnt {

   // private transient ClientTypeEnt type;
    //@NotBlank(message = "Id cannot be blank")
    @Id
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "is_active")
    private boolean active;
    @Column(name = "client_type")
    private String clientType;

    public ClientEnt(){
        clientType = "Normal";
    }

    public ClientEnt(int id, String name, String surname, String type) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.active = true;
        this.clientType = type;
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

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
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
}
