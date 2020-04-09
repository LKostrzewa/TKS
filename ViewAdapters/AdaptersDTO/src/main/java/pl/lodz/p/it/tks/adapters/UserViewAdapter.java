package pl.lodz.p.it.tks.adapters;

import org.springframework.stereotype.Component;
import pl.lodz.p.it.tks.converters.UserViewConverter;
import pl.lodz.p.it.tks.dto.ClientDTO;
import pl.lodz.p.it.tks.dto.UserDTO;
import pl.lodz.p.it.tks.model.Client;
import pl.lodz.p.it.tks.model.User;
import pl.lodz.p.it.tks.service.UserService;
import pl.lodz.p.it.tks.useCases.userUseCase.AddUserUseCase;
import pl.lodz.p.it.tks.useCases.userUseCase.DeleteUserUseCase;
import pl.lodz.p.it.tks.useCases.userUseCase.UpdateUserUseCase;
import pl.lodz.p.it.tks.useCases.userUseCase.UtilsUserUseCase;

import java.util.List;

@Component
public class UserViewAdapter implements AddUserUseCase, DeleteUserUseCase, UpdateUserUseCase, UtilsUserUseCase {

    private UserService userService;
    private UserViewConverter userViewConverter;

    public UserViewAdapter(UserService userService) {
        this.userService = userService;
        userViewConverter = new UserViewConverter();
    }

    @Override
    public void addUser(UserDTO user) {
        userService.addUser(userViewConverter.convertUserDTO(user));
    }

    @Override
    public void deleteUser(String id) {
        userService.deleteUser(id);
    }

    @Override
    public void updateUser(String id, UserDTO user) {
        userService.updateUser(id, userViewConverter.convertUserDTO(user));
    }

    @Override
    public UserDTO getUser(String login) {
        return null;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return null;
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return null;
    }

    @Override
    public List<ClientDTO> getAllActiveClients() {
        return null;
    }
}
