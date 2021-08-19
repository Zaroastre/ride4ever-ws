package io.nirahtech.ride4ever.domain.registration;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.nirahtech.ride4ever.core.environment.Pilot;
import io.nirahtech.ride4ever.core.web.exceptions.MethodNotAllowedException;

@CrossOrigin("*")
@RequestMapping("/registration")
@RestController
public final class RegistrationController implements RegistrationApi {

    private final RegistrationService service = new RegistrationService();

    @PostMapping("/register")
    @ResponseBody
    @Override
    public final Pilot create(@RequestBody final Pilot account) throws RuntimeException {
        return this.service.create(account);
    }

    @Override
    public Pilot find(String email) throws RuntimeException {
        throw new MethodNotAllowedException();
    }

    @Override
    public Pilot update(@RequestBody final Pilot pilot) throws RuntimeException {
        throw new MethodNotAllowedException();
    }

    @DeleteMapping("/unregister")
    @ResponseBody
    @Override
    public Pilot delete(@RequestBody final Pilot account) throws RuntimeException {
        return this.service.delete(account);
    }



}
