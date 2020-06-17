package pl.lodz.p.it.tks.ports.userPort;

import java.util.UUID;

public interface DeleteUserPort {
    void deleteUser(int id);
    void deleteUserByKey(UUID key);
}
