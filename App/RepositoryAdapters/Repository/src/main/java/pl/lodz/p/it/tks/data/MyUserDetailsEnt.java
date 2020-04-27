package pl.lodz.p.it.tks.data;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//@PasswordMatches
public class MyUserDetailsEnt implements UserDetails {

    private UserEnt user;

    public MyUserDetailsEnt(UserEnt user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(user instanceof AdministratorEnt){
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        else if(user instanceof ManagerEnt){
            authorities.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
        }
        else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return authorities;
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

    public String getMatchingPassword() {
        return user.getMatchingPassword();
    }
}
