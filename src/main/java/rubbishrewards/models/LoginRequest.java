package rubbishrewards.models;

/**
 * Represents and stores an incoming authentication request, which consists of a username and password String.
 */
public class LoginRequest
{
    private String username, password;

    public LoginRequest() {}

    public LoginRequest(String username, String password)
    {
        this.username = username;
        this.password = password;
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
}
