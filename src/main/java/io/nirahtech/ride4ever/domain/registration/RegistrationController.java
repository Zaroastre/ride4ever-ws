package io.nirahtech.ride4ever.domain.registration;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.nirahtech.ride4ever.core.environment.Biker;
import io.nirahtech.ride4ever.core.web.exceptions.MethodNotAllowedException;

@CrossOrigin("*")
@RequestMapping("/registration")
@RestController
public final class RegistrationController implements RegistrationApi {

    private final RegistrationService service = new RegistrationService();

    @PostMapping("/register")
    @ResponseBody
    @Override
    public final Biker create(@RequestBody final Biker account) throws RuntimeException {
        return this.service.create(account);
    }

    @Override
    public Biker find(String email) throws RuntimeException {
        throw new MethodNotAllowedException();
    }

    @Override
    public Biker update(@RequestBody final Biker biker) throws RuntimeException {
        throw new MethodNotAllowedException();
    }

    @DeleteMapping("/unregister")
    @ResponseBody
    @Override
    public Biker delete(@RequestBody final Biker account) throws RuntimeException {
        return this.service.delete(account);
    }



}
