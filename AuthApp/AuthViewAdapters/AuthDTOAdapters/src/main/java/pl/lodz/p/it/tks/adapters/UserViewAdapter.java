package pl.lodz.p.it.tks.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.lodz.p.it.tks.converters.UserViewConverter;
import pl.lodz.p.it.tks.dto.UserDTO;
import pl.lodz.p.it.tks.model.Administrator;
import pl.lodz.p.it.tks.model.Manager;
import pl.lodz.p.it.tks.service.UserService;
import pl.lodz.p.it.tks.useCases.userUseCase.AddUserUseCase;
import pl.lodz.p.it.tks.useCases.userUseCase.DeleteUserUseCase;
import pl.lodz.p.it.tks.useCases.userUseCase.UpdateUserUseCase;
import pl.lodz.p.it.tks.useCases.userUseCase.UtilsUserUseCase;

import java.util.ArrayList;
import java.util.List;

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
        if (userService.getUser(login) instanceof Administrator) return userViewConverter.convertAdministrator((Administrator)userService.getUser(login));
        else if(userService.getUser(login) instanceof Manager) return userViewConverter.convertManager((Manager)userService.getUser(login));
        else return userViewConverter.convertUser(userService.getUser(login));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOS = new ArrayList<>();
        userService.getAllUsers().forEach(user -> userDTOS.add(getUser(user.getLogin())));
        return userDTOS;
    }
}
