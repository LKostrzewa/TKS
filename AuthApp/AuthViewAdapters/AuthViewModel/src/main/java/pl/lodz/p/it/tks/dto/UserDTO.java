package pl.lodz.p.it.tks.dto;

import java.util.UUID;

public class UserDTO {

    private String login;
    private String password;
    private String name;
    private String surname;
    private boolean isActive;
    private String accessLevel;
    //TODO w którym miejscu go ustawiamy ?
    private UUID key;

    public UserDTO(){
        this.isActive = true;
    }

    public UserDTO(String login, String password, String name, String surname, String accessLevel) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.isActive = true;
        this.accessLevel = accessLevel;
        this.key = UUID.randomUUID();
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
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
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
