package rubbishrewards.models;

/**
 * Represents a authentication response after authentication is successful. The response is a jwt token that is
 * sent back to the users browser to be stored in a cookie, so that subsequent requests from the user can be identified
 * by the jwt signature.
 */
public class LoginResponse
{
    private final String jwt, username;

    public LoginResponse(String jwt, String username)
    {
        this.jwt = jwt;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getJwt()
    {
        return jwt;
    }
}
