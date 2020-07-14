package rubbishrewards.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import rubbishrewards.models.LoginRequest;
import rubbishrewards.models.LoginResponse;
import rubbishrewards.services.UserDetailsServiceImpl;
import rubbishrewards.util.JwtUtil;

@RestController
public class TestController
{
   /* @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/")
    public String home()
    {
        return("<h1>Hello all</h1>");
    }

    @GetMapping("/member")
    public String user()
    {
        return("<h1>Hello member</h1>");
    }

    @GetMapping("/admin")
    public String admin()
    {
        return("<h1>Hello admin</h1>");
    }

    @GetMapping("/hello")
    public String hello()
    {
        return("<h1>Hello hello</h1>");
    }

    /**
     * Takes in a username and password from the request body, which is used to authenticate.
     *
     * If authentication is successful the userService loadUserByUsername method is called by supplying it with the
     * request username. This looks for the user in the repository and returns a UserDetails instance which is assigned
     * to the userDetails variable.
     *
     * Then a jwt token is created by calling the jwtUtil generateToken method and passing in the userDetails object as
     * a parameter so the token can be associated with a user. This returns a String so we assign this call to the
     * jwt variable of type String.
     *
     * Finally the jwt String variable is passed to a new AuthenticationResponse instance, which is sent back as
     * a response to the user and the browser can keep hold of the jwt for future requests.
     *
     * @param loginRequest holds the incoming username and password in the request body.
     * @return an AuthenticationResponse which holds a jwt String.
     * @throws Exception If the username or password are wrong or don't exist then a BadCredentialsException is thrown.
     */
    /*@PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws Exception
    {
        try
        {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(), loginRequest.getPassword()));
        }

        catch(BadCredentialsException e)
        {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userService.loadUserByUsername(loginRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new LoginResponse(jwt, username));
    }*/
}
