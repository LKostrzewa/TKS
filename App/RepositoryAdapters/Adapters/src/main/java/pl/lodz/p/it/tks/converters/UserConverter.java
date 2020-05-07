package pl.lodz.p.it.tks.converters;

import pl.lodz.p.it.tks.data.*;
import pl.lodz.p.it.tks.model.*;

public class UserConverter {

    public UserEnt convertUser(User user){
        UserEnt userEnt;
        if(user instanceof Administrator){
            userEnt = new AdministratorEnt(user.getLogin(), user.getPassword(), user.getName(), user.getSurname());
        }
        else if (user instanceof Manager) {
            userEnt = new ManagerEnt(user.getLogin(), user.getPassword(), user.getName(), user.getSurname());
        }
        else {
            userEnt = new UserEnt(user.getLogin(), user.getPassword(), user.getName(), user.getSurname());
        }
        userEnt.setActive(user.isActive());
        userEnt.setMatchingPassword(user.getMatchingPassword());
        return userEnt;
    }

    public Administrator convertAdministratorEnt(AdministratorEnt administratorEnt){
        Administrator administrator = new Administrator(administratorEnt.getLogin(), administratorEnt.getPassword(), administratorEnt.getName(), administratorEnt.getSurname());
        administrator.setActive(administratorEnt.isActive());
        administrator.setMatchingPassword(administratorEnt.getMatchingPassword());
        return administrator;
    }

    public Manager convertManagerEnt(ManagerEnt managerEnt){
        Manager manager = new Manager(managerEnt.getLogin(), managerEnt.getPassword(), managerEnt.getName(), managerEnt.getSurname());
        manager.setActive(managerEnt.isActive());
        manager.setMatchingPassword(managerEnt.getMatchingPassword());
        return manager;
    }

    public User convertUserEnt(UserEnt userEnt) {
        User user = new User(userEnt.getLogin(), userEnt.getPassword(), userEnt.getName(), userEnt.getSurname());
        user.setActive(userEnt.isActive());
        user.setMatchingPassword(userEnt.getMatchingPassword());
        return user;
    }
}
