package pl.lodz.p.it.tks.converters;

import pl.lodz.p.it.tks.dto.*;
import pl.lodz.p.it.tks.model.*;

public class UserViewConverter {

    public User convertUserDTO(UserDTO userDTO){
        User user = new User(userDTO.getId(), userDTO.getLogin(), userDTO.getPassword(), userDTO.getName(), userDTO.getSurname(), userDTO.getAccessLevel(), userDTO.getKey());
        user.setActive(userDTO.isActive());
        return user;
    }

    public UserDTO convertUser(User user) {
        UserDTO userDTO = new UserDTO(user.getId(), user.getLogin(), user.getPassword(), user.getName(), user.getSurname(), user.getAccessLevel());
        userDTO.setActive(user.isActive());
        userDTO.setKey(user.getKey());
        return userDTO;
    }
}
