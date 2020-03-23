package pl.lodz.p.it.tks.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.lodz.p.it.tks.converters.UserConverter;
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
    private UserConverter converter;

    @Autowired
    public UserRepositoryAdapter(UserRepository repository) {
        this.repository = repository;
        converter = new UserConverter();
    }

    @Override
    public void addUser(User user) {
        repository.add(user.getLogin(), converter.convertUser(user));
    }

    @Override
    public void deleteUser(String id) {
        repository.delete(id);
    }


    @Override
    public User getUser(String id) {
        if(repository.get(id) instanceof ClientEnt) return converter.convertClientEnt((ClientEnt)repository.get(id));
        else if(repository.get(id) instanceof AdministratorEnt) return converter.convertAdministratorEnt((AdministratorEnt)repository.get(id));
        else return converter.convertManagerEnt((ManagerEnt)repository.get(id));
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
            clients.add(converter.convertClientEnt(clientEnt));
        }
        return clients;
    }

    @Override
    public List<Client> getAllActiveClients() {
        List<Client> clients = new ArrayList<>();
        for (ClientEnt clientEnt : repository.getAllActiveClients()){
            clients.add(converter.convertClientEnt(clientEnt));
        }
        return clients;
    }

    @Override
    public void updateUser(String id, User user) {
        repository.update(id, converter.convertUser(user));
    }
}
