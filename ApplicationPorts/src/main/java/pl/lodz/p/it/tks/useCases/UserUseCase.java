package pl.lodz.p.it.tks.useCases;

import org.springframework.security.crypto.bcrypt.BCrypt;
import pl.lodz.p.it.tks.model.Client;
import pl.lodz.p.it.tks.model.User;

import java.util.List;

public interface UserUseCase {
    void addUser(User user);
    User getUser(String login);
    void deleteUser(String id);
    void updateUser(String id, User user);
    List<User> getAllUsers();
    List<Client> getAllClients();
    List<Client> getAllActiveClients();
}
