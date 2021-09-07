/******************************************************************
 * Copyright 2021 Ride4Ever
 *
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.microservices.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.nirahtech.ride4ever.infrastructure.exceptions.NotImplementedException;
import io.nirahtech.ride4ever.io.EmailBroker;
import io.nirahtech.ride4ever.microservices.biker.Biker;

@CrossOrigin("*")
@RequestMapping("/registration")
@RestController
public final class RegistrationController implements RegistrationApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private RegistrationService service;

    @PostMapping("/register")
    @ResponseBody
    @Override
    public final Biker create(@RequestBody final Biker account) throws RuntimeException {
        String password = new String(account.getPassword());
        Biker createdAcount = this.service.create(account);
        LOGGER.info("Created account: " + createdAcount);
        if (createdAcount != null) {
            LOGGER.info("Sending email to " + createdAcount.getEmail());
            EmailBroker.sendEmail(
                "Ride4Ever - Registration",
                String.format(
                    "Welcome %s ! You are now registered on the Ride4Ever application. Don't forget your password: %s",
                    createdAcount.getPseudo(),
                    password
                ),
                createdAcount.getEmail()
            );
        }
        return createdAcount;
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
    public void delete(@RequestBody final Biker account) throws RuntimeException {
        this.service.delete(account);
    }



}
