package pl.lodz.p.it.tks.converters;

import pl.lodz.p.it.tks.data.*;
import pl.lodz.p.it.tks.model.*;

public class UserConverter {

    public User convertUserEnt(UserEnt userEnt) {
        User user = new User(userEnt.getLogin(), userEnt.getPassword(), userEnt.getName(), userEnt.getSurname(), userEnt.getAccessLevel());
        user.setActive(userEnt.isActive());
        return user;
    }

    public UserEnt convertUser(User user) {
        UserEnt userEnt = new UserEnt(user.getLogin(), user.getPassword(), user.getName(), user.getSurname(), user.getAccessLevel());
        userEnt.setActive(userEnt.isActive());
        return userEnt;
    }
}
