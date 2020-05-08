package pl.lodz.p.it.tks.converters;

import pl.lodz.p.it.tks.dto.*;
import pl.lodz.p.it.tks.model.*;

public class UserViewConverter {

    public User convertUserDTO(UserDTO userDTO){
        User user;
        if(userDTO instanceof AdministratorDTO){
            user = new Administrator(userDTO.getLogin(), userDTO.getPassword(), userDTO.getName(), userDTO.getSurname());
        }
        else if(userDTO instanceof ManagerDTO) {
            user = new Manager(userDTO.getLogin(), userDTO.getPassword(), userDTO.getName(), userDTO.getSurname());
        }
        else {
            user = new User(userDTO.getLogin(), userDTO.getPassword(), userDTO.getName(), userDTO.getSurname());
        }
        user.setActive(userDTO.isActive());
        user.setMatchingPassword(userDTO.getMatchingPassword());
        return user;
    }

    public AdministratorDTO convertAdministrator(Administrator administrator){
        AdministratorDTO administratorDTO = new AdministratorDTO(administrator.getLogin(), administrator.getPassword(), administrator.getName(), administrator.getSurname());
        administratorDTO.setActive(administrator.isActive());
        administratorDTO.setMatchingPassword(administrator.getMatchingPassword());
        return administratorDTO;
    }

    public ManagerDTO convertManager(Manager manager){
        ManagerDTO managerDTO = new ManagerDTO(manager.getLogin(), manager.getPassword(), manager.getName(), manager.getSurname());
        managerDTO.setActive(manager.isActive());
        managerDTO.setMatchingPassword(manager.getMatchingPassword());
        return managerDTO;
    }

    public UserDTO convertUser(User user) {
        UserDTO userDTO = new UserDTO(user.getLogin(), user.getPassword(), user.getName(), user.getSurname());
        userDTO.setActive(user.isActive());
        userDTO.setMatchingPassword(user.getMatchingPassword());
        return userDTO;
    }
}
