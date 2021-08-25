package io.nirahtech.ride4ever.microservices.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.nirahtech.ride4ever.infrastructure.exceptions.NotImplementedException;
import io.nirahtech.ride4ever.microservices.biker.Biker;

@CrossOrigin("*")
@RequestMapping("/registration")
@RestController
public final class RegistrationController implements RegistrationApi {

    @Autowired
    private RegistrationService service;

    @PostMapping("/register")
    @ResponseBody
    @Override
    public final Biker create(@RequestBody final Biker account) throws RuntimeException {
        System.out.println(account);
        return this.service.create(account);
    }

    @Override
    public Biker find(String email) throws RuntimeException {
        throw new NotImplementedException();
    }

    @Override
    public Biker update(@RequestBody final Biker biker) throws RuntimeException {
        throw new NotImplementedException();
    }

    @DeleteMapping("/unregister")
    @ResponseBody
    @Override
    public Biker delete(@RequestBody final Biker account) throws RuntimeException {
        return this.service.delete(account);
    }



}
