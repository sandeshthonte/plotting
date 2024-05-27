package com.arundhati.backend.plotting.service.configuration;

import com.arundhati.backend.plotting.config.security.AppJwtProperties;
import com.arundhati.backend.plotting.models.UserDetails;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtService {

    private final AppJwtProperties appJwtProperties;


    public String generateJWT(Map<String, Object> claims) {
        var key = appJwtProperties.getKey();
        var algorithm = appJwtProperties.getAlgorithm();

        var header = new JWSHeader(algorithm);
        var claimsSet = buildClaimsSet(claims);

        var jwt = new SignedJWT(header, claimsSet);

        try {
            var signer = new MACSigner(key);
            jwt.sign(signer);
        } catch (JOSEException e) {
            throw new RuntimeException("Unable to generate JWT", e);
        }

        return jwt.serialize();
    }

    private JWTClaimsSet buildClaimsSet(Map<String, Object> claims) {
        var issuer = appJwtProperties.getIssuer();
        var issuedAt = Instant.now();
        var expirationTime = issuedAt.plus(appJwtProperties.getExpiresIn());

        var builder = new JWTClaimsSet.Builder()
                .issuer(issuer)
                .issueTime(Date.from(issuedAt))
                .expirationTime(Date.from(expirationTime));

        claims.forEach(builder::claim);

        return builder.build();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(appJwtProperties.getKey().getEncoded()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails, int i) {
        try {
            if (StringUtils.isEmpty(token)) {
                return false;
            }


            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(appJwtProperties.getKey());

            return signedJWT.verify(verifier) && !signedJWT.getJWTClaimsSet().getExpirationTime().before(new Date());
        }
        catch (JOSEException ex){
            throw new RuntimeException("Unable to validate JWT", ex);
        }
        catch (ParseException ex) {
            throw new RuntimeException("Unable to validate JWT", ex);
        }

    }

    public boolean validateToken(String token, UserDetails userDetails) {
        if (StringUtils.isEmpty(token)) {
            return false;
        }

        try {
            SignedJWT signedJWT = SignedJWT.parse(token);

            // Check if the token has been tampered with
            JWSVerifier verifier = new MACVerifier(appJwtProperties.getKey());
            if (!signedJWT.verify(verifier)) {
                return false;
            }

            // Check if the token has expired
            Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
            if (expirationTime != null && expirationTime.before(new Date())) {
                return false;
            }

            final String username = getUsernameFromToken(token);
            return (username.equals(userDetails.getUserName()) && !isTokenExpired(token));
        } catch (ParseException | JOSEException e) {
            // Token parsing or verification failed
            return false;
        }
    }


}