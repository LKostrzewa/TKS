package pl.lodz.p.it.tks.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.lodz.p.it.tks.converters.UserConverter;
import pl.lodz.p.it.tks.data.AdministratorEnt;
import pl.lodz.p.it.tks.data.ManagerEnt;
import pl.lodz.p.it.tks.data.UserEnt;
import pl.lodz.p.it.tks.db.UserDBRepository;
import pl.lodz.p.it.tks.model.User;
import pl.lodz.p.it.tks.ports.userPort.AddUserPort;
import pl.lodz.p.it.tks.ports.userPort.DeleteUserPort;
import pl.lodz.p.it.tks.ports.userPort.GetUserPort;
import pl.lodz.p.it.tks.ports.userPort.UpdateUserPort;
import pl.lodz.p.it.tks.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepositoryAdapter implements AddUserPort, DeleteUserPort, GetUserPort, UpdateUserPort {
    private UserDBRepository repository;
    private UserConverter converter;

    @Autowired
    public UserRepositoryAdapter(UserDBRepository repository) {
        this.repository = repository;
        converter = new UserConverter();
    }

    @Override
    public void addUser(User user) {
        repository.save(converter.convertUser(user));
    }

    @Override
    public void deleteUser(int id) {
        repository.deleteById(id);
    }


    @Override
    public User getUser(int id) {
        if(repository.getOne(id) instanceof AdministratorEnt) return converter.convertAdministratorEnt((AdministratorEnt)repository.getOne(id));
        else if(repository.getOne(id) instanceof ManagerEnt) return converter.convertManagerEnt((ManagerEnt)repository.getOne(id));
        else return converter.convertUserEnt(repository.getOne(id));
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        for (UserEnt userEnt : repository.findAll()){
            users.add(getUser(userEnt.getId()));
        }
        return users;
    }

    @Override
    public User getUserByName(String name) {
        if(repository.getByName(name) instanceof AdministratorEnt) return converter.convertAdministratorEnt((AdministratorEnt)repository.getByName(name));
        else if(repository.getByName(name) instanceof ManagerEnt) return converter.convertManagerEnt((ManagerEnt)repository.getByName(name));
        else return converter.convertUserEnt(repository.getByName(name));
    }

    @Override
    public void updateUser(int id, User user) {
        if(repository.existsById(id)){
            repository.save(converter.convertUser(user));
        }

    }
}
