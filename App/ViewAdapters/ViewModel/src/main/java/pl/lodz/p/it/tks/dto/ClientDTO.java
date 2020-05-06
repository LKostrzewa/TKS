package pl.lodz.p.it.tks.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ClientDTO extends UserDTO {

    @JsonIgnore
    private ClientTypeDTO type;
    //private String typeName;

    public ClientDTO(){
        type = new NormalClientDTO();
    }

    public ClientDTO(String login, String password, String name, String surname, ClientTypeDTO type) {
        super(login, password, name, surname);
        this.type = type;
    }

    @Override
    public String toString() {
        String tmp;
        if(isActive()){
            tmp = "is active";
        }
        else tmp = "is inactive";
        return "Client login " + getLogin() + " full name " + getName()
                + " " + getSurname() + " " + getType() + " type " + tmp;
        /*return "Client{" +
                "login='" + getLogin() + '\'' +
                ", name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", type=" + type + '\'' +
                ", isActive='" + isActive() + '\'' +
                '}';*/
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
