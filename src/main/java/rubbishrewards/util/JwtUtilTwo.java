package rubbishrewards.util;

import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import rubbishrewards.exceptions.SpringRubbishRewardsException;
import rubbishrewards.models.User;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * A utility class that can create a new jwt associated with a username, as well as extracting information
 * from an existing jwt token to check for validity.
 */
@Service
public class JwtUtilTwo
{
    private KeyStore keyStore;

    @PostConstruct
    public void init() {
        try
        {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/springblog.jks");
            keyStore.load(resourceAsStream, "secret".toCharArray());
        }
        catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e)
        {
            throw new SpringRubbishRewardsException("Exception occurred while loading keystore");
        }
    }

    public String generateToken(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .signWith(getPrivateKey())
                .compact();
    }

    private PrivateKey getPrivateKey()
    {
        try
        {
            return (PrivateKey) keyStore.getKey("springblog", "secret".toCharArray());
        }
        catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e)
        {
            throw new SpringRubbishRewardsException("Exception occurred while retrieving public key from keystore");
        }
    }
}
