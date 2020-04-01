package pl.lodz.p.it.tks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import pl.lodz.p.it.tks.model.*;
import pl.lodz.p.it.tks.ports.AddUserPort;
import pl.lodz.p.it.tks.ports.DeleteUserPort;
import pl.lodz.p.it.tks.ports.GetUserPort;
import pl.lodz.p.it.tks.ports.UpdateUserPort;
import pl.lodz.p.it.tks.repository.UserRepository;
import pl.lodz.p.it.tks.useCases.UserUseCase;

import java.util.List;

@Service
public class UserService implements UserUseCase {

    private AddUserPort addUserPort;
    private GetUserPort getUserPort;
    private UpdateUserPort updateUserPort;
    private DeleteUserPort deleteUserPort;

    @Autowired
    public UserService(AddUserPort addUserPort, GetUserPort getUserPort, UpdateUserPort updateUserPort, DeleteUserPort deleteUserPort) {
        this.addUserPort = addUserPort;
        this.getUserPort = getUserPort;
        this.updateUserPort = updateUserPort;
        this.deleteUserPort = deleteUserPort;
        addUser(new Administrator("admin", "password", "Jan", "Kowalski"));
        addUser(new Manager("manager", "password", "Piotr", "Nowak"));
        addUser(new Client("romek", "password", "Roman", "Bialek", new NormalClient()));
    }

    @Override
    public void addUser(User user){
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        addUserPort.addUser(user);
    }

    @Override
    public User getUser(String login){
        return getUserPort.getUser(login);
    }

    @Override
    public void deleteUser(String id){
        deleteUserPort.deleteUser(id);
    }

    @Override
    public void updateUser(String id, User user){
        updateUserPort.updateUser(id, user);
    }

    @Override
    public List<User> getAllUsers(){
        return getUserPort.getAllUsers();
    }

    @Override
    public List<Client> getAllClients(){
        return getUserPort.getAllClients();
    }

    @Override
    public List<Client> getAllActiveClients(){
        return getUserPort.getAllActiveClients();
    }
}
