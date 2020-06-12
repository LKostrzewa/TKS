package pl.lodz.p.it.tks.payloads;

import javax.sql.rowset.serial.SerialArray;
import java.io.Serializable;
import java.util.UUID;

public class UserPayload implements Serializable {
    private String login;
    private String password;
    private String name;
    private String surname;
    private boolean isActive;
    private UUID key;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public UUID getKey() {
        return key;
    }

    public void setKey(UUID key) {
        this.key = key;
    }
}
