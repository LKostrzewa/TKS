package pl.lodz.p.it.tks.ports.userPort;

import pl.lodz.p.it.tks.model.User;

import java.util.List;

public interface GetUserPort {
    User getUser(int id);
    List<User> getAllUsers();
    User getUserByName(String name);
}
