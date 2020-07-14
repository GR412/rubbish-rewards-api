package rubbishrewards.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rubbishrewards.models.MyUserDetails;
import rubbishrewards.models.User;
import rubbishrewards.repositories.UserRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * This service class is responsible for all user action logic such as registering, authenticating and viewing /
 * updating account and profile information.
 *
 * This class also implements the Spring Security UserDetailsService interface which uses the loadUserByUsername
 * method that retrieves a users details for authentication purposes.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    UserRepository userRepository;

    /**
     * Implemented interface method that returns a UserDetails interface object.
     *
     * First a Optional<User> object is assigned to the call of the findByUsername userRepository method, which takes
     * in the username parameter as an argument.
     *
     * If the passed in username parameter isn't found in the userRepository a UsernameNotFoundException is thrown.
     *
     * If the username is found then the Optional<User> object is transformed into a UserDetails interface object, by
     * mapping to a new instance of MyUserDetails (which is my implementation of a UserDetails interface object) which
     * is then retrieved with the .get and returned to complete the method.
     *
     * @param username the username String to be passed to the userRepository.
     * @return UserDetails which is a Spring Security interface object
     * @throws UsernameNotFoundException the exception thrown when a username isn't found in the repository
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException(username + "not found"));
        //return user.map(MyUserDetails::new).get();
        return new org.springframework.security.core.userdetails.User
                (user.getUsername(), user.getPassword(), user.isEnabled(), true, true,
                        true, getAuthorities("USER"));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role)
    {
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }
}
