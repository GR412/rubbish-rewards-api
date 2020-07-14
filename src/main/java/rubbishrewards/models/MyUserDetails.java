package rubbishrewards.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that is an implementation of the UserDetails interface. It implements all the methods associated with this
 * interface.
 *
 * This is used to store details about the user for authentication purposes in the UserService as well as to control
 * other aspects of the user such as if their account is locked.
 */
public class MyUserDetails implements UserDetails
{
    private long points;
    private String email, username, password;
    private Date dateJoined;
    private boolean isActive;
    private List<GrantedAuthority> authorities;

    /**
     * First constructor that takes in a User entity object as a parameter and assigns the User object
     * information to the MyUserDetails fields via the use of the User objects get methods.
     * to the
     * @param user the entity object used to retrieve information about the user
     */
    public MyUserDetails(User user)
    {
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.points = user.getPoints();
        this.dateJoined = user.getDateJoined();
        this.isActive = user.isEnabled();
        this.authorities = Arrays.stream(user.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

    }

    /**
     * Blank second constructor.
     */
    public MyUserDetails()
    {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return isActive;
    }
}
