package pl.lodz.p.it.tks.converters;

import pl.lodz.p.it.tks.data.*;
import pl.lodz.p.it.tks.model.*;

public class UserConverter {

    public User convertUserEnt(UserEnt userEnt) {
        User user = new User(userEnt.getId(), userEnt.getLogin(), userEnt.getPassword(), userEnt.getName(), userEnt.getSurname(), userEnt.getAccessLevel(), userEnt.getKey());
        user.setActive(userEnt.isActive());
        return user;
    }

    public UserEnt convertUser(User user) {
        UserEnt userEnt = new UserEnt(user.getId(), user.getLogin(), user.getPassword(), user.getName(), user.getSurname(), user.getAccessLevel(), user.getKey());
        userEnt.setActive(userEnt.isActive());
        return userEnt;
    }
}
