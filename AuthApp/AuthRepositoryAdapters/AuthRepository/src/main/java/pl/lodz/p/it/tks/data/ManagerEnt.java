package pl.lodz.p.it.tks.data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "manager")
public class ManagerEnt extends UserEnt {

    public ManagerEnt(String login, String password, String name, String surname) {
        super(login, password, name, surname);
    }

    public ManagerEnt() {

    }

    @Override
    public String toString() {
        return "Manager{" +
                "login" + getLogin() + '\'' +
                ", name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                '}';
    }
}
