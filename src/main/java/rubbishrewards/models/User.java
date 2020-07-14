package rubbishrewards.models;
import javax.persistence.*;
import java.util.Date;

/**
 * Represents the users account details that are not public.
 * This class is an entity so it also mapped to the users database table in the rubbish_rewards_db schema.
 */

@Entity
public class User
{
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserProfile userProfile;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserPosts userPosts;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long points;
    private String email, username, password, roles;
    private Date dateJoined;
    private boolean isEnabled;

    public User() { }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public UserPosts getUserPosts() {
        return userPosts;
    }

    public void setUserPosts(UserPosts userPosts) {
        this.userPosts = userPosts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
