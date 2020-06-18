package pl.lodz.p.it.tks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import pl.lodz.p.it.tks.model.*;
import pl.lodz.p.it.tks.ports.userPort.AddUserPort;
import pl.lodz.p.it.tks.ports.userPort.DeleteUserPort;
import pl.lodz.p.it.tks.ports.userPort.GetUserPort;
import pl.lodz.p.it.tks.ports.userPort.UpdateUserPort;

import java.util.List;
import java.util.UUID;

@Service
public class UserService  {

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
    }

    public boolean addUser(User user){
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        return addUserPort.addUser(user);
    }

    public User getUser(int id){
        return getUserPort.getUser(id);
    }

    public void deleteUser(int id){
        deleteUserPort.deleteUser(id);
    }

    public void deleteUserByKey(UUID key){
        deleteUserPort.deleteUserByKey(key);
    }

    public void updateUser(int id, User user){
        updateUserPort.updateUser(id, user);
    }

    public List<User> getAllUsers(){
        return getUserPort.getAllUsers();
    }

    public User getUserByName(String name) {
        return getUserPort.getUserByName(name);
    }

    public User getUserByKey(UUID key) {
        return getUserPort.getUserByKey(key);
    }
}
