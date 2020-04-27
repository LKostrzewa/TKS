package pl.lodz.p.it.tks.data;

public class AdministratorEnt extends UserEnt {

    public AdministratorEnt(String login, String password, String name, String surname) {
        super(login, password, name, surname);
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
