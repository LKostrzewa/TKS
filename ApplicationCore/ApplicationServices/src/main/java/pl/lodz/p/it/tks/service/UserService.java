package pl.lodz.p.it.tks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import pl.lodz.p.it.tks.model.*;
import pl.lodz.p.it.tks.ports.userPort.AddUserPort;
import pl.lodz.p.it.tks.ports.userPort.DeleteUserPort;
import pl.lodz.p.it.tks.ports.userPort.GetUserPort;
import pl.lodz.p.it.tks.ports.userPort.UpdateUserPort;
import pl.lodz.p.it.tks.useCases.userUseCase.AddUserUseCase;
import pl.lodz.p.it.tks.useCases.userUseCase.DeleteUserUseCase;
import pl.lodz.p.it.tks.useCases.userUseCase.UpdateUserUseCase;
import pl.lodz.p.it.tks.useCases.userUseCase.UtilsUserUseCase;

import java.util.List;

@Service
public class UserService implements AddUserUseCase, DeleteUserUseCase, UpdateUserUseCase, UtilsUserUseCase {

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
