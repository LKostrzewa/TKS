package pl.lodz.p.it.tks.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import pl.lodz.p.it.tks.repository.UserRepository;

public class UserRepositoryAdapter {
    @Autowired
    private UserRepository repository;
}
