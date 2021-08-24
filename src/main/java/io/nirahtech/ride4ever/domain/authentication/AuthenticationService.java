package io.nirahtech.ride4ever.domain.authentication;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.nirahtech.ride4ever.core.environment.Biker;
import io.nirahtech.ride4ever.domain.biker.BikerService;
import io.nirahtech.ride4ever.infrastructure.exceptions.BadRequestException;
import io.nirahtech.ride4ever.infrastructure.exceptions.InternalProcessException;
import io.nirahtech.ride4ever.infrastructure.exceptions.ResourceNotFoundException;

public final class AuthenticationService implements AuthenticationApi {

    private static final UUID KEY = UUID.randomUUID();

    private static final BikerService PILOT_SERVICE = BikerService.getInstance();
    private Map<Credential, Session> sessions = new HashMap<>();

    @Override
    public Session login(Credential credential) throws RuntimeException {
        String jwt = null;
        Biker biker = PILOT_SERVICE.findByEmail(credential.getUsername());
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
                    if (sessions.keySet().contains(credential)) {
                        sessions.replace(credential, sessions.get(credential), session);
                    } else {
                        sessions.put(credential, session);
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
