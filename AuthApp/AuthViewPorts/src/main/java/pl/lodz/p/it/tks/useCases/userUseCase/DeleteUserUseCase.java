package pl.lodz.p.it.tks.useCases.userUseCase;

import java.util.UUID;

public interface DeleteUserUseCase {
    void deleteUser(int id);
    void deleteUserByKey(UUID key);
}
