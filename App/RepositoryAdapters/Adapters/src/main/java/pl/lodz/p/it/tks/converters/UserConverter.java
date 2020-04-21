package pl.lodz.p.it.tks.converters;

import pl.lodz.p.it.tks.data.*;
import pl.lodz.p.it.tks.model.*;

public class UserConverter {
    public ClientTypeEnt convertClientType(ClientType clientType){
        if(clientType instanceof NormalClient) return new NormalClientEnt();
        else if(clientType instanceof RegularClient) return new RegularClientEnt();
        else return new PremiumClientEnt();
    }

    public UserEnt convertUser(User user){
        UserEnt userEnt;
        if(user instanceof Client){
            userEnt = new ClientEnt(user.getLogin(), user.getPassword(), user.getName(), user.getSurname(), convertClientType(((Client) user).getType()));
        }
        else if(user instanceof Administrator){
            userEnt = new AdministratorEnt(user.getLogin(), user.getPassword(), user.getName(), user.getSurname());
        }
        else {
            userEnt = new ManagerEnt(user.getLogin(), user.getPassword(), user.getName(), user.getSurname());
        }
        userEnt.setActive(user.isActive());
        userEnt.setMatchingPassword(user.getMatchingPassword());
        return userEnt;
    }

    public Client convertClientEnt(ClientEnt clientEnt){
        ClientType clientType;
        if(clientEnt.getType() instanceof NormalClientEnt) clientType = new NormalClient();
        else if(clientEnt.getType() instanceof RegularClientEnt) clientType = new RegularClient();
        else clientType = new PremiumClient();
        Client client = new Client(clientEnt.getLogin(), clientEnt.getPassword(), clientEnt.getName(), clientEnt.getSurname(), clientType);
        client.setActive(clientEnt.isActive());
        client.setMatchingPassword(clientEnt.getMatchingPassword());
        return client;
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
}
