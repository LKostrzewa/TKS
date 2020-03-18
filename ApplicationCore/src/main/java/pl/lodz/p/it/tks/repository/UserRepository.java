package pl.lodz.p.it.tks.repository;

import org.springframework.stereotype.Repository;
import pl.lodz.p.it.tks.model.Client;
import pl.lodz.p.it.tks.model.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository extends RepositoryTemplate<User> {

    /*public UserRepository(){
        add("admin", new Administrator("admin", "password", "Jan", "Kowalski"));
        add("manger", new Manager("manager", "password", "Piotr", "Nowak"));
    }*/

    public List<Client> getAllClients(){
        List<Client> clients = new ArrayList<>();
        for(User c : getAll()){
            if(c instanceof Client) clients.add((Client) c);
        }
        return clients;
    }

    public List<Client> getAllActiveClients(){
        List<Client> clients = new ArrayList<>();
        for(User c : getAll()){
            if(c instanceof Client && c.isActive()) clients.add((Client) c);
        }
        return clients;
    }
}
