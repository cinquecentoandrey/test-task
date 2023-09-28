package com.cinquecento.jwtstarter.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil {

    @Value("${jwt.token.subject}")
    private String SUBJECT;

    @Value("${jwt.token.expired}")
    private int EXPIRED;

    @Value("${jwt.token.issuer}")
    private String ISSUER;

    @Value("${jwt.token.secret}")
    private String SECRET;

    public String generateToken(Long id, String username, Boolean isApproved) {
        Date expirationDate = Date.from(ZonedDateTime.now().plusMinutes(EXPIRED).toInstant());

        return JWT
                .create()
                .withSubject(SUBJECT)
                .withClaim("id", id)
                .withClaim("username", username)
                .withClaim("isApproved", isApproved)
                .withIssuedAt(Instant.now())
                .withIssuer(ISSUER)
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(SECRET));
    }

    public Map<String, String> validateTokenAndRetrieveClaims(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET))
                .withSubject(SUBJECT)
                .withIssuer(ISSUER)
                .build();

        DecodedJWT decodedJWT = verifier.verify(token);
        Map<String, String> claims = new HashMap<>();

        claims.put("id", decodedJWT.getClaim("id").asString());
        claims.put("username", decodedJWT.getClaim("username").asString());
        claims.put("isApproved", decodedJWT.getClaim("isApproved").asString());

        return claims;
    }
}
