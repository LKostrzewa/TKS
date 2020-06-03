package pl.lodz.p.it.tks.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pl.lodz.p.it.tks.converters.UserViewConverter;
import pl.lodz.p.it.tks.dto.MyUserDetailsDTO;
import pl.lodz.p.it.tks.dto.UserDTO;
import pl.lodz.p.it.tks.service.UserService;

@Component
public class MyUserDetailsAdapter implements UserDetailsService {
    private UserService userService;
    private UserViewConverter userViewConverter;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
        userViewConverter = new UserViewConverter();
    }

    public MyUserDetailsAdapter(UserService userService){
        this.userService = userService;
        //userService.addUser(new Administrator("admin", "password", "Jan", "Kowalski"));
        //userService.addUser(new Manager("manager", "password", "Piotr", "Nowak"));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDTO user = userViewConverter.convertUser(userService.getUserByName(s));
        if(user == null)
            throw new UsernameNotFoundException(String.format( "A user with username {} doesn't exist", s));
        return new MyUserDetailsDTO(user);
    }
}
