package pl.lodz.p.it.tks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.lodz.p.it.tks.model.MyUserDetails;
import pl.lodz.p.it.tks.model.User;

import javax.transaction.Transactional;

//import pl.bialekkostrzewa.converter.UserToSecurityUserConverter;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    public MyUserDetailsService(UserService userService){
        this.userService = userService;
        //userService.addUser(new Administrator("admin", "password", "Jan", "Kowalski"));
        //userService.addUser(new Manager("manager", "password", "Piotr", "Nowak"));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.getUser(s);
        if(user == null)
            throw new UsernameNotFoundException(String.format( "A user with username {} doesn't exist", s));
        return new MyUserDetails(user);
    }
}
