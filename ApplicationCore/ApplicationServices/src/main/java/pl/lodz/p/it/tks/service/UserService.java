package pl.lodz.p.it.tks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import pl.lodz.p.it.tks.model.*;
import pl.lodz.p.it.tks.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private UserRepository users;

    @Autowired
    public UserService(UserRepository users) {
        this.users = users;
        addUser(new Administrator("admin", "password", "Jan", "Kowalski"));
        addUser(new Manager("manager", "password", "Piotr", "Nowak"));
        addUser(new Client("romek", "password", "Roman", "Bialek", new NormalClient()));
    }

    public void addUser(User user){
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        users.add(user.getLogin(), user);
    }

    public void changeClientsType(String login, ClientType type) throws RuntimeException {
        User user = users.get(login);
        if(user instanceof Client){
            Client client = (Client)user;
            client.setType(type);
        }
        else throw new RuntimeException("This user is not a client");
    }

    public User getUser(String login){
        return users.get(login);
    }

    public void updateUser(String id, User user){
        users.update(id, user);
    }

    public void activateUser(String login){
        users.get(login).setActive(true);
    }

    public void deactivateUser(String login){
        users.get(login).setActive(false);
    }

    public List<User> getAllUsers(){
        return users.getAll();
    }

    public List<Client> getAllClients(){
        return users.getAllClients();
    }

    public List<Client> getAllActiveClients(){
        return users.getAllActiveClients();
    }
}
