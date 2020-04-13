package pl.lodz.p.it.tks.dto;

public class AdministratorDTO extends UserDTO {

    public AdministratorDTO(String login, String password, String name, String surname) {
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
