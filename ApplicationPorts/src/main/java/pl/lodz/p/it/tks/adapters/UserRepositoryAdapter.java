package pl.lodz.p.it.tks.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.lodz.p.it.tks.data.*;
import pl.lodz.p.it.tks.model.*;
import pl.lodz.p.it.tks.ports.AddUserPort;
import pl.lodz.p.it.tks.repository.UserRepository;

@Component
public class UserRepositoryAdapter implements AddUserPort {
    private UserRepository repository;

    @Autowired
    public UserRepositoryAdapter(UserRepository repository) {
        this.repository = repository;
    }

    private ClientTypeEnt convertClientTypeEnt(ClientType clientType){
        if(clientType instanceof NormalClient) return new NormalClientEnt();
        else if(clientType instanceof RegularClient) return new RegularClientEnt();
        else return new PremiumClientEnt();
    }

    private UserEnt convertUserEnt(User user){
        UserEnt userEnt;
        if(user instanceof Client){
            userEnt = new ClientEnt(user.getLogin(), user.getPassword(), user.getName(), user.getSurname(), convertClientTypeEnt(((Client) user).getType()));
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

    @Override
    public void addUser(User user) {
        repository.add(user.getLogin(), convertUserEnt(user));
    }
}
