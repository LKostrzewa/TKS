package pl.lodz.p.it.tks.useCases.userUseCase;


import pl.lodz.p.it.tks.dto.UserDTO;

import java.util.List;

public interface UtilsUserUseCase {
    UserDTO getUser(int login);
    List<UserDTO> getAllUsers();
    UserDTO getUserByName(String login);
}
