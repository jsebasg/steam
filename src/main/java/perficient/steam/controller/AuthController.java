package perficient.steam.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import perficient.steam.domain.RoleEnum;
import perficient.steam.domain.User;
import perficient.steam.dto.LoginDto;
import perficient.steam.dto.TokenDto;
import perficient.steam.exceptions.NotFoundException;
import perficient.steam.service.UserService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static perficient.steam.domain.Constants.CLAIMS_ROLES_KEY;
import static perficient.steam.domain.Constants.TOKEN_DURATION_MINUTES;


@RestController
@RequestMapping( "/v1/auth" )
public class AuthController
{

    @Value( "${app.secret}" )
    String secret;

    private final UserService userService;

    public AuthController( @Autowired UserService userService )
    {
        this.userService = userService;
    }

    @PostMapping
    public TokenDto login(@RequestBody LoginDto loginDto )
    {
        User user = userService.findByEmail(loginDto.getEmail());
        System.out.println(user.getRoleEnum());
        System.out.println(loginDto.getPassword());
        System.out.println(user.getPasswordHash());
        System.out.println(BCrypt.checkpw(loginDto.getPassword(), user.getPasswordHash()));

        if ( BCrypt.checkpw(loginDto.getPassword(), user.getPasswordHash() ) )
        {
            return generateTokenDto( user );
        }
        else
        {
            throw new NotFoundException("INVALID CREDENTIALS");
        }

    }

    private String generateToken( User user, Date expirationDate )
    {
        List<RoleEnum> m = new ArrayList<>();
        m.add(user.getRoleEnum());
        String a = Jwts.builder()
                .setSubject( user.getId().toString() )
                .claim( CLAIMS_ROLES_KEY,m )
                .setIssuedAt(new Date() )
                .setExpiration( expirationDate )
                .signWith( SignatureAlgorithm.HS256, secret )
                .compact();
        System.out.println(a);
        return a;
    }

    private TokenDto generateTokenDto( User user )
    {
        Calendar expirationDate = Calendar.getInstance();
        expirationDate.add( Calendar.MINUTE, TOKEN_DURATION_MINUTES );
        String token = generateToken( user, expirationDate.getTime() );
        return new TokenDto( token, expirationDate.getTime() );
    }
}