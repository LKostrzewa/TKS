package pl.lodz.p.it.tks.useCases.UserUseCase;

import pl.lodz.p.it.tks.model.User;

public interface UpdateUserUseCase {
    void updateUser(String id, User user);
}
