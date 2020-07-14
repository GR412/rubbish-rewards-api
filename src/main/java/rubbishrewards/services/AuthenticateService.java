package rubbishrewards.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rubbishrewards.models.LoginRequest;
import rubbishrewards.models.LoginResponse;
import rubbishrewards.repositories.UserRepository;
import rubbishrewards.util.JwtUtilTwo;

/**
 * This service class is responsible for all user action logic such as registering, authenticating and viewing /
 * updating account and profile information.
 *
 * This class also implements the Spring Security UserDetailsService interface which uses the loadUserByUsername
 * method that retrieves a users details for authentication purposes.
 */
@Service
public class AuthenticateService
{
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtilTwo jwtUtil2;

    @Autowired
    UserRepository userRepository;

    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authenticate);

        String token = jwtUtil2.generateToken(authenticate);

        return new LoginResponse(token, loginRequest.getUsername());

    }
}
