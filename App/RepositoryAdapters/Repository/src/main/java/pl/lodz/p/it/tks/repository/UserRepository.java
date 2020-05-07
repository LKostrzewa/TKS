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
}
