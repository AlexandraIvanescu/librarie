package ro.biblioteca.online.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ro.biblioteca.online.models.Library;
import ro.biblioteca.online.repositories.LibraryRepository;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Alexandra Ale on 19.03.2017.
 */

@Service
public class LibraryDetailsService implements UserDetailsService {

    private LibraryRepository repository;

    @Autowired
    public LibraryDetailsService(LibraryRepository repository) {
        Assert.notNull(repository, "Library Repository must be not null !");

        this.repository = repository;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Library library = repository.findByEmail(email);

        List<GrantedAuthority> authorities = buildUserAuthority();

        return buildUserForAuthentication(library, authorities);
    }

    private User buildUserForAuthentication(Library library, List<GrantedAuthority> authorities) {
        return new User(library.getEmail(), library.getPassword(), true, true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority() {
        Set<GrantedAuthority> setAuths = new HashSet<>();
        setAuths.add(new SimpleGrantedAuthority("USER"));

        return new ArrayList<>(setAuths);
    }

}
