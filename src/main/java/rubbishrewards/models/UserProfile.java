package rubbishrewards.models;
import javax.persistence.*;

/**
 * Represents the users profile details which are public.
 * This class is an entity so it also mapped to the user_profile database table in the rubbish_rewards_db schema.
 */

@Entity
public class UserProfile
{
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String displayName, bio;
    private String profilePicture; // TODO ASSIGN THIS THE CORRECT TYPE LATER

    public UserProfile() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Object getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
