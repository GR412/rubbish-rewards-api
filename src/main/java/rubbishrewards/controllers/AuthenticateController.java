package rubbishrewards.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rubbishrewards.models.LoginRequest;
import rubbishrewards.models.LoginResponse;
import rubbishrewards.services.AuthenticateService;

@RestController
public class AuthenticateController
{
    @Autowired
    AuthenticateService authenticateService;

    @PostMapping("/register")
    public void register()
    {

    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest)
    {
        return authenticateService.login(loginRequest);
    }

    @PostMapping("/logout")
    public void logout()
    {

    }
}
