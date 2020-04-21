package pl.lodz.p.it.tks.repository;

import org.springframework.stereotype.Repository;
import pl.lodz.p.it.tks.data.ClientEnt;
import pl.lodz.p.it.tks.data.UserEnt;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository extends RepositoryTemplate<UserEnt> {

    /*public UserRepository(){
        add("admin", new Administrator("admin", "password", "Jan", "Kowalski"));
        add("manger", new Manager("manager", "password", "Piotr", "Nowak"));
    }*/

    public List<ClientEnt> getAllClients(){
        List<ClientEnt> clients = new ArrayList<>();
        for(UserEnt c : getAll()){
            if(c instanceof ClientEnt) clients.add((ClientEnt) c);
        }
        return clients;
    }

    public List<ClientEnt> getAllActiveClients(){
        List<ClientEnt> clients = new ArrayList<>();
        for(UserEnt c : getAll()){
            if(c instanceof ClientEnt && c.isActive()) clients.add((ClientEnt) c);
        }
        return clients;
    }
}
