package pl.lodz.p.it.tks.useCases.userUseCase;

import pl.lodz.p.it.tks.dto.UserDTO;

public interface UpdateUserUseCase {
    void updateUser(String id, UserDTO user);
}
