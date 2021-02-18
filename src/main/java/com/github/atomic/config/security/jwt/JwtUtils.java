package com.github.atomic.config.security.jwt;

import com.github.atomic.config.EnvironmentConfig;
import com.github.atomic.model.entity.User;
import com.github.atomic.utils.Constants;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.security.Key;
import java.util.*;

@Service
@Slf4j
public class JwtUtils {

    @Autowired
    EnvironmentConfig environment;

    @Value("${jsonwebtoken.jjwt.secret}")
    private String jwtSecret;

    @Value("${jsonwebtoken.jjwt.expiration}")
    private String expirationTime;

    private Key key;


    @PostConstruct
    public void init() {
//        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        this.key = getKey();
    }

    public String generateKey(){
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512); //or HS384 or HS512
        return Encoders.BASE64URL.encode(key.getEncoded());
    }

    public Key getKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(jwtSecret));
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration != null && expiration.before(new Date());
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", (long)user.getId());
//        claims.put("email", user.getEmail());
//        claims.put("phone", user.getPhone());

        List<String> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(role.getTag());
        });

        if (authorities.isEmpty()){
            authorities.add(Constants.ROLE_USER);
        }

        claims.put("authorities", authorities);
        return generateToken(user.getUsername(), claims);
    }

    public String generateToken(String username, Map<String, Object> claims) {


        final Date createdDate = new Date();

        /*return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(key)
                .compact();*/

        JwtBuilder jwtBuilder = Jwts.builder()
                .setClaims(claims)
                .setIssuer(environment.getAppName())
                .setSubject(username);
//                .setAudience("you")
//                .setNotBefore(notBefore) //a java.util.Date

        try {
            long expirationTimeLong = Long.parseLong(expirationTime); //in second
            if (expirationTimeLong > 0) {
                final Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong * 1000);
                jwtBuilder.setExpiration(expirationDate);
            }
        }catch (Exception ignored){

        }

        jwtBuilder.setIssuedAt(createdDate) // for example, now
                .setId(UUID.randomUUID().toString()) //just an example id
                .signWith(key);

        return jwtBuilder.compact();
    }

    /*public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }*/

    public boolean isTokenValid(String token) {
        try {

            Jwts.parserBuilder().setSigningKey(this.key).build().parseClaimsJws(token);
            //OK, we can trust this JWT\
            return !isTokenExpired(token);

        } catch (JwtException e) {
            //don't trust the JWT!
            return false;
        }

    }

}
