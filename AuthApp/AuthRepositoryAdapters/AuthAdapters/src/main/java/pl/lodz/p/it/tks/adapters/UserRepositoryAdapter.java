package pl.lodz.p.it.tks.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.lodz.p.it.tks.converters.UserConverter;
import pl.lodz.p.it.tks.data.UserEnt;
import pl.lodz.p.it.tks.db.UserDBRepository;
import pl.lodz.p.it.tks.model.User;
import pl.lodz.p.it.tks.ports.userPort.AddUserPort;
import pl.lodz.p.it.tks.ports.userPort.DeleteUserPort;
import pl.lodz.p.it.tks.ports.userPort.GetUserPort;
import pl.lodz.p.it.tks.ports.userPort.UpdateUserPort;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Transactional
public class UserRepositoryAdapter implements AddUserPort, DeleteUserPort, GetUserPort, UpdateUserPort {
    private UserDBRepository repository;
    private UserConverter converter;

    @Autowired
    public UserRepositoryAdapter(UserDBRepository repository) {
        this.repository = repository;
        converter = new UserConverter();
    }

    @Override
    public boolean addUser(User user) {
        if(repository.existsByLogin(user.getLogin())){
            return false;
        }
        repository.save(converter.convertUser(user));

        return repository.existsByKey(user.getKey());
    }

    @Override
    public void deleteUser(int id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteUserByKey(UUID key){
        repository.deleteByKey(key);
    }


    @Override
    public User getUser(int id) {
        return converter.convertUserEnt(repository.getOne(id));
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
        return converter.convertUserEnt(repository.getByName(name));
    }

    @Override
    public void updateUser(int id, User user) {
        if(repository.existsById(id)){
            repository.save(converter.convertUser(user));
        }

    }
}
