package rubbishrewards.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rubbishrewards.services.UserDetailsServiceImpl;

@RestController
public class UserController
{
    @Autowired
    UserDetailsServiceImpl userService;

    /*GET REQUESTS*/

    @GetMapping("/{id}/{username}")
    public void getUser()
    {

    }

    @GetMapping("/settings/profile")
    public void getProfileSettings()
    {

    }

    @GetMapping("/settings/account")
    public void getAccountSettings()
    {

    }

    @GetMapping("/settings/security")
    public void getSecuritySettings()
    {

    }

    /*PUT / DEL REQUESTS*/

    @PutMapping("/settings/profile")
    public void updateProfileSettings()
    {

    }

    @PutMapping("/settings/account/change-username")
    public void updateUsername()
    {

    }

    @PutMapping("/settings/account/change-email")
    public void updateEmail()
    {

    }

    @DeleteMapping("/settings/account/delete-account")
    public void deleteAccount()
    {

    }

    @PutMapping("/settings/security/change-password")
    public void updatePassword()
    {

    }

    @PutMapping("/settings/security/enable-2fa")
    public void enable2FA()
    {

    }
}
