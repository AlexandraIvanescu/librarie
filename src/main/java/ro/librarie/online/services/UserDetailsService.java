package ro.librarie.online.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ro.librarie.online.repositories.UserRepository;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Alexandra Ale on 19.03.2017.
 */

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private UserRepository repository;

    @Autowired
    public UserDetailsService(UserRepository repository) {
        Assert.notNull(repository, "User Repository must be not null !");

        this.repository = repository;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ro.librarie.online.models.User user = repository.findByEmail(email);

        List<GrantedAuthority> authorities = buildUserAuthority();

        return buildUserForAuthentication(user, authorities);
    }

    private User buildUserForAuthentication(ro.librarie.online.models.User user, List<GrantedAuthority> authorities) {
        return new User(user.getEmail(), user.getPassword(), true, true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority() {
        Set<GrantedAuthority> setAuths = new HashSet<>();
        setAuths.add(new SimpleGrantedAuthority("USER"));

        return new ArrayList<>(setAuths);
    }

}
