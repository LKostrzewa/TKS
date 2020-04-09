package pl.lodz.p.it.tks.dto;

public class ManagerDTO extends UserDTO {

    public ManagerDTO(String login, String password, String name, String surname) {
        super(login, password, name, surname);
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
