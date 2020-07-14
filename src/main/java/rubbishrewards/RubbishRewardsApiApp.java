package rubbishrewards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import rubbishrewards.repositories.UserRepository;

@SpringBootApplication
@CrossOrigin(origins = "*")
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class RubbishRewardsApiApp
{
    public static void main(String[] args)
    {
        SpringApplication.run(RubbishRewardsApiApp.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurer()
        {
            @Override
            public void addCorsMappings(CorsRegistry registry)
            {
                registry.addMapping("/*").allowedHeaders("*").allowedOrigins("*").allowedMethods("*")
                    .allowCredentials(true);
            }
        };
    }
}
