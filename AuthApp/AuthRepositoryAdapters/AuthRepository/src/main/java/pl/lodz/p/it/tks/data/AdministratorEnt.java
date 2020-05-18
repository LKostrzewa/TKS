package pl.lodz.p.it.tks.data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "administrator")
public class AdministratorEnt extends UserEnt {

    public AdministratorEnt(String login, String password, String name, String surname) {
        super(login, password, name, surname);
    }

    public AdministratorEnt() {

    }

    @Override
    public String toString() {
        return "Administrator{" +
                "login" + getLogin() + '\'' +
                ", name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                '}';
    }

}
