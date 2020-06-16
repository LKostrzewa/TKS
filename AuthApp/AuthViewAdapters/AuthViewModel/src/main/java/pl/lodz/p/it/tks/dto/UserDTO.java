package pl.lodz.p.it.tks.dto;

import java.util.UUID;

public class UserDTO {

    private int id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private boolean active;
    private String accessLevel;
    private UUID key;

    public UserDTO(){
        this.active = true;
    }

    public UserDTO(int id, String login, String password, String name, String surname, String accessLevel) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.active = true;
        this.accessLevel = accessLevel;
        this.key = UUID.randomUUID();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
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

    public void setLogin(String login) {
        this.login = login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    public UUID getKey() {
        return key;
    }

    public void setKey(UUID key) {
        this.key = key;
    }
}
