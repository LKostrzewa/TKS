package pl.lodz.p.it.tks.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//TODO na 99% niepotrzebne przy 100% usunąć
//@PasswordMatches
public class MyUserDetailsDTO implements UserDetails {

    private UserDTO user;

    public MyUserDetailsDTO(UserDTO user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /*List<GrantedAuthority> authorities = new ArrayList<>();
        if(user instanceof AdministratorDTO){
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        else if(user instanceof ManagerDTO){
            authorities.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
        }
        else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return authorities;*/
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isActive();
    }

}
