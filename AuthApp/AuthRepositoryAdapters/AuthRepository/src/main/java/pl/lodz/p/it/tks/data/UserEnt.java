package pl.lodz.p.it.tks.data;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "user_table")
public class UserEnt {

    @Id
    @SequenceGenerator(name = "UserSeqGen", sequenceName = "user_table_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserSeqGen")
    private int id;
    @NotBlank(message = "Login cannot be blank")
    @Column(name = "login")
    private String login;
    @NotBlank(message = "Password cannot be blank")
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "is_active")
    private boolean active;
    @Column(name = "access_level")
    private String accessLevel;
    @Column
    private UUID key;

    public UserEnt(){
        this.active = true;
    }

    public UserEnt(int id, String login, String password, String name, String surname, String accessLevel, UUID key) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.active = true;
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
