package pl.lodz.p.it.tks.ports.userPort;

import pl.lodz.p.it.tks.model.Client;
import pl.lodz.p.it.tks.model.User;

import java.util.List;

public interface GetUserPort {
    User getUser(String id);
    List<User> getAllUsers();
    List<Client> getAllClients();
    List<Client> getAllActiveClients();
}
