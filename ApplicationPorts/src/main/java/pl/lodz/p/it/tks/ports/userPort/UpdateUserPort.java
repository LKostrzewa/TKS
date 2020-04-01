package pl.lodz.p.it.tks.ports.userPort;

import pl.lodz.p.it.tks.model.User;

public interface UpdateUserPort {
    void updateUser(String id, User user);
}
