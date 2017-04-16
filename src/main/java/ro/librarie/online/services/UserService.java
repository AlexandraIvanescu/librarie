package ro.librarie.online.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.librarie.online.models.User;
import ro.librarie.online.repositories.UserRepository;

/**
 * Created by Alexandra Ale on 16.04.2017.
 */

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean registerNewUserAccount(User user) {

        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            userRepository.saveAndFlush(user);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

}
