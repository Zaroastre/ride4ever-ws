package io.nirahtech.ride4ever.microservices.authentication;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.nirahtech.ride4ever.infrastructure.exceptions.BadRequestException;
import io.nirahtech.ride4ever.infrastructure.exceptions.InternalProcessException;
import io.nirahtech.ride4ever.infrastructure.exceptions.ResourceNotFoundException;
import io.nirahtech.ride4ever.microservices.biker.Biker;
import io.nirahtech.ride4ever.microservices.biker.BikerService;

@Component("authenticationService")
public final class AuthenticationService implements AuthenticationApi {

    private static final UUID KEY = UUID.randomUUID();
    private static final Map<Credential, Session> SESSIONS = new HashMap<>();

    @Autowired
    private BikerService service;

    @Override
    public Session login(Credential credential) throws RuntimeException {
        String jwt = null;
        Biker biker = service.findByEmail(credential.getUsername());
        Session session = null;
        if (biker != null) {
            if (biker.getPassword().equals(credential.getPassword())) {
                try {
                    jwt = Jwts.builder()
                        .claim("email", credential.getUsername())
                        .setSubject(credential.getUsername())
                        .setId(UUID.randomUUID().toString())
                        .setIssuedAt(new Date())
                        .setExpiration(Date.from(Instant.now().plus(30, ChronoUnit.MINUTES)))
                        // .claim("groups", account.getGroups())
                        .signWith(SignatureAlgorithm.HS256, AuthenticationService.KEY.toString().getBytes("UTF-8"))
                        .compact();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    throw new InternalProcessException(e.getMessage());
                }
                session = new Session(jwt, biker);
                if (jwt != null) {
                    if (SESSIONS.keySet().contains(credential)) {
                        SESSIONS.replace(credential, SESSIONS.get(credential), session);
                    } else {
                        SESSIONS.put(credential, session);
                    }
                }
            } else {
                throw new BadRequestException("Invalid password.");
            }
        } else {
            throw new ResourceNotFoundException("Biker using email "+ credential.getUsername() + " is not found.");
        }
        return session;
    }

    @Override
    public void logout(Session session) throws RuntimeException {
        // TODO Auto-generated method stub

    }
}
