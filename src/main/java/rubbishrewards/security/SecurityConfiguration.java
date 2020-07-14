package rubbishrewards.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    //@Autowired
    //UserService myUserDetailsService;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        //auth.userDetailsService(myUserDetailsService);
        auth.userDetailsService(userDetailsService);
    }

    /**
     * Determines the flow of authentication and authorization.
     *
     * First cross site request forgery is disabled and the admin page rules are set up to ensure only users with
     * the role ADMIN can view the page.
     *
     * Next the public pages such as the login and sign up forms are set up so they don't require authentication or
     * authorization to view, after which any other request requires the user to authenticate to view.
     *
     * The session policy is then set to be stateless, as session management is being handled by jwt instead.
     *
     * Finally we add a filter that will intercept all incoming requests and check to see of the jwt is valid.
     *
     * @param http the HttpSecurity object that has useful methods for authentication and authorization flow.
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable().
                authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN") // only admins can access
                .antMatchers("/", "/authenticate", "/register", "/login").permitAll()// everyone can access
                .anyRequest().authenticated() // anything else must be authenticated
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                //.and().formLogin(); // used to test auto generated login form

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * Determines how a password should be encoded for storage in the database.
     *
     * TODO AT THE MOMENT THERE IS NO HASHING BEING USED, WHEN THE APP IS FINISHED THIS NEEDS TO BE CHANGED
     *
     * @return
     */
    @Bean
    public PasswordEncoder getPasswordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * Used to determine the shape of a AuthenticationManager, without this an error occurs whenever an instance of
     * this is autowired into a class.
     *
     * @return an AuthenticationManager from the super class.
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }
}
