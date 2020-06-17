package pl.lodz.p.it.tks.useCases.userUseCase;


import pl.lodz.p.it.tks.dto.UserDTO;

public interface AddUserUseCase {
    boolean addUser(UserDTO user);
}
