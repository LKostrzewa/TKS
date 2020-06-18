package pl.lodz.p.it.tks.publisherrabbitmq;

import java.io.Serializable;
import java.util.UUID;

public class UserPayload implements Serializable {
    private String login;
    private String password;
    private String name;
    private String surname;
    private boolean active;
    private UUID key;
    private String clientType;

    public UserPayload(String login, String password, String name, String surname, boolean active, UUID key, String clientType) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.active = active;
        this.key = key;
        this.clientType = clientType;
    }

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
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public UUID getKey() {
        return key;
    }

    public void setKey(UUID key) {
        this.key = key;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }
}
