package pl.lodz.p.it.tks.useCases.UserUseCase;

import pl.lodz.p.it.tks.model.Client;
import pl.lodz.p.it.tks.model.User;

import java.util.List;

public interface UtilsUserUseCase {
    User getUser(String login);
    List<User> getAllUsers();
    List<Client> getAllClients();
    List<Client> getAllActiveClients();
}
