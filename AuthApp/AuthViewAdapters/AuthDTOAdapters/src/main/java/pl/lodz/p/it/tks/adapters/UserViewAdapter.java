package pl.lodz.p.it.tks.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.lodz.p.it.tks.converters.UserViewConverter;
import pl.lodz.p.it.tks.dto.UserDTO;
import pl.lodz.p.it.tks.service.UserService;
import pl.lodz.p.it.tks.useCases.userUseCase.AddUserUseCase;
import pl.lodz.p.it.tks.useCases.userUseCase.DeleteUserUseCase;
import pl.lodz.p.it.tks.useCases.userUseCase.UpdateUserUseCase;
import pl.lodz.p.it.tks.useCases.userUseCase.UtilsUserUseCase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class UserViewAdapter implements AddUserUseCase, DeleteUserUseCase, UpdateUserUseCase, UtilsUserUseCase {

    private UserService userService;
    private UserViewConverter userViewConverter;

    @Autowired
    public UserViewAdapter(UserService userService) {
        this.userService = userService;
        userViewConverter = new UserViewConverter();
    }

    @Override
    public boolean addUser(UserDTO user) {
        return userService.addUser(userViewConverter.convertUserDTO(user));
    }

    @Override
    public void deleteUser(int id) {
        userService.deleteUser(id);
    }

    @Override
    public void deleteUserByKey(UUID key){
        userService.deleteUserByKey(key);
    }

    @Override
    public void updateUser(int id, UserDTO user) {
        userService.updateUser(id, userViewConverter.convertUserDTO(user));
    }

    @Override
    public UserDTO getUser(int id) {
        return userViewConverter.convertUser(userService.getUser(id));
    }

    @Override
    public UserDTO getUserByName(String login){
        return userViewConverter.convertUser(userService.getUserByName(login));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOS = new ArrayList<>();
        userService.getAllUsers().forEach(user -> userDTOS.add(getUserByName(user.getLogin())));
        return userDTOS;
    }
}
