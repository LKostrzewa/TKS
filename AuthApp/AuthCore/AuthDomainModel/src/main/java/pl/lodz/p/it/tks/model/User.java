package pl.lodz.p.it.tks.model;

import java.util.UUID;

public class User {

    protected int id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private boolean isActive;
    private String accessLevel;
    private UUID key;

    public User(){
        this.isActive = true;
    }

    public User(String login, String password, String name, String surname, String accessLevel, UUID key) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.isActive = true;
        this.accessLevel = accessLevel;
        this.key = key;
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
