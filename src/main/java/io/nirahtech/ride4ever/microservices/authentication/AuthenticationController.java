package io.nirahtech.ride4ever.microservices.authentication;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RequestMapping("/authentication")
@RestController
public final class AuthenticationController implements AuthenticationApi {

    private final AuthenticationService service = new AuthenticationService();

    @PostMapping("/login")
    @ResponseBody
    @Override
    public final Session login(@RequestBody final Credential credential) throws RuntimeException {
        return this.service.login(credential);
    }

    @DeleteMapping("/logout")
    @ResponseBody
    @Override
    public void logout(@RequestBody Session session) throws RuntimeException {
        this.service.logout(session);
    }
    
}
