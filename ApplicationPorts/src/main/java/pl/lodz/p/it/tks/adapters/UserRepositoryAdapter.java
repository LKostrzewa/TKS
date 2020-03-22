package pl.lodz.p.it.tks.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.lodz.p.it.tks.data.*;
import pl.lodz.p.it.tks.model.*;
import pl.lodz.p.it.tks.ports.AddUserPort;
import pl.lodz.p.it.tks.ports.DeleteUserPort;
import pl.lodz.p.it.tks.ports.GetUserPort;
import pl.lodz.p.it.tks.ports.UpdateUserPort;
import pl.lodz.p.it.tks.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepositoryAdapter implements AddUserPort, DeleteUserPort, GetUserPort, UpdateUserPort {
    private UserRepository repository;

    @Autowired
    public UserRepositoryAdapter(UserRepository repository) {
        this.repository = repository;
    }

    private ClientTypeEnt convertClientType(ClientType clientType){
        if(clientType instanceof NormalClient) return new NormalClientEnt();
        else if(clientType instanceof RegularClient) return new RegularClientEnt();
        else return new PremiumClientEnt();
    }

    private UserEnt convertUser(User user){
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

    private Client convertClientEnt(ClientEnt clientEnt){
        ClientType clientType;
        if(clientEnt.getType() instanceof NormalClientEnt) clientType = new NormalClient();
        else if(clientEnt.getType() instanceof RegularClientEnt) clientType = new RegularClient();
        else clientType = new PremiumClient();
        Client client = new Client(clientEnt.getLogin(), clientEnt.getPassword(), clientEnt.getName(), clientEnt.getSurname(), clientType);
        client.setActive(clientEnt.isActive());
        client.setMatchingPassword(clientEnt.getMatchingPassword());
        return client;
    }

    private Administrator convertAdministratorEnt(AdministratorEnt administratorEnt){
        Administrator administrator = new Administrator(administratorEnt.getLogin(), administratorEnt.getPassword(), administratorEnt.getName(), administratorEnt.getSurname());
        administrator.setActive(administratorEnt.isActive());
        administrator.setMatchingPassword(administratorEnt.getMatchingPassword());
        return administrator;
    }

    private Manager convertManagerEnt(ManagerEnt managerEnt){
        Manager manager = new Manager(managerEnt.getLogin(), managerEnt.getPassword(), managerEnt.getName(), managerEnt.getSurname());
        manager.setActive(managerEnt.isActive());
        manager.setMatchingPassword(managerEnt.getMatchingPassword());
        return manager;
    }

    @Override
    public void addUser(User user) {
        repository.add(user.getLogin(), convertUser(user));
    }

    @Override
    public void deleteUser(String id) {
        repository.delete(id);
    }


    @Override
    public User getUser(String id) {
        if(repository.get(id) instanceof ClientEnt) return convertClientEnt((ClientEnt)repository.get(id));
        else if(repository.get(id) instanceof AdministratorEnt) return convertAdministratorEnt((AdministratorEnt)repository.get(id));
        else return convertManagerEnt((ManagerEnt)repository.get(id));
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        for (UserEnt userEnt : repository.getAll()){
            users.add(getUser(userEnt.getLogin()));
        }
        return users;
    }

    @Override
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        for (ClientEnt clientEnt : repository.getAllClients()){
            clients.add(convertClientEnt(clientEnt));
        }
        return clients;
    }

    @Override
    public List<Client> getAllActiveClients() {
        List<Client> clients = new ArrayList<>();
        for (ClientEnt clientEnt : repository.getAllActiveClients()){
            clients.add(convertClientEnt(clientEnt));
        }
        return clients;
    }

    @Override
    public void updateUser(String id, User user) {
        repository.update(id, convertUser(user));
    }
}
