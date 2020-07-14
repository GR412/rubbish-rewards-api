package rubbishrewards.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * A utility class that can create a new jwt associated with a username, as well as extracting information
 * from an existing jwt token to check for validity.
 */
@Service
public class JwtUtil
{
    private final String SECRET_KEY = "secret"; // TODO NEEDS TO BE MORE SECURE

    /**
     * Extracts the username from an existing jwt, which is used to check for validity. This is done by calling
     * the extractClaim method where the jwt and the Claims getSubject method is passed.
     *
     * @param token the jwt that is to be checked for validity.
     * @return returns the extracted username from the given jwt.
     */
    public String extractUsername(String token)
    {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts the expiration date from an existing jwt, which is used to check for validity. This is done by calling
     * the extractClaim method where the jwt and the Claims getExpiration method is passed.
     *
     * @param token the jwt that is to be checked for validity.
     * @return returns the extracted expiration date from the given jwt.
     */
    public Date extractExpiration(String token)
    {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * TODO MAKE SURE THIS DESCRIPTION IS CORRECT, IM NOT QUITE SURE WHAT IT'S DOING
     * Gets the passed in tokens signature by calling the extractAllClaims method, this is then assigned to a
     * variable called claims.
     *
     * The passed in claimsResolver parameter then applies the generated matching signature, by passing the claims
     * variable to the apply method. So that the passed in Claims method call can verify that the signature matches.
     *
     * @param token the jwt that is to be checked for validity.
     * @param claimsResolver the passed in Claims method call.
     * @param <T> TODO NEED TO READ UP ON GENERIC TYPES
     * @return TODO NEED TO READ UP ON GENERIC TYPES
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver)
    {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * TODO MAKE SURE THIS DESCRIPTION IS CORRECT, IM NOT QUITE SURE WHAT IT'S DOING
     * Parses the passed in jwt, it then applies the same secret key signature as when the jwt was first created to
     * ensure the original and passed jwt signature match.
     *
     * @param token the jwt that is to be checked for validity.
     * @return TODO NOT QUITE SURE WHAT THE CLAIMS INTERFACE DOES
     */
    private Claims extractAllClaims(String token)
    {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    /**
     * Checks that an incoming jwt is not expired. It does this by calling the extractExpiration method by
     * passing in the token parameter to find out the expiration date of when the the jwt was originally created.
     *
     * A boolean check is then performed to check if the original expiration date was set before the date of of the
     * incoming request.
     *
     * @param token the jwt that is to be checked for validity.
     * @return true or false depending on if the original jwt expiration date was set before the date of the
     * incoming request.
     */
    private Boolean isTokenExpired(String token)
    {
        return extractExpiration(token).before(new Date());
    }

    /**
     * TODO NOT SURE WHAT THE CLAIMS HASHMAP IS FOR
     * Collects the necessary data of a new HashMap and a reference to the UserDetails username, which is then passed
     * to the createToken method, which actually creates the token.
     *
     * @param userDetails used to extract the users username so it can be associated with the created jwt.
     * @return a call to the createToken which returns the generated jwt String.
     */
    public String generateToken(UserDetails userDetails)
    {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    /**
     * TODO NOT SURE WHAT THE CLAIMS HASHMAP IS FOR
     * Creates the token with the passed in claims HashMap and username which makes the token unique to that user.
     *
     * @param claims TODO NOT SURE WHAT THIS IS FOR
     * @param subject the username that is used to identify the jwt to that particular user.
     * @return a jwt in the form of a String.
     */
    private String createToken(Map<String, Object> claims, String subject)
    {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    /**
     * Used to ensure an incoming jwt request is valid and therefore can be trusted.
     *
     * First the username is extracted from the incoming jwt, and assigned to a new variable.
     *
     * Then a boolean check occurs that compares the extracted jwt username to the UserDetails username and weather a
     * call to the isTokenExpired method returns false.
     *
     * If both of these conditions are met the incoming token can be assumed to be valid and the request can take place.
     *
     * @param token the jwt that is to be checked for validity.
     * @param userDetails used to extract the users username so it can be compared against the username in UserDetails.
     * @return a true or false depending on if the token passes the boolean check.
     */
    public Boolean validateToken(String token, UserDetails userDetails)
    {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
