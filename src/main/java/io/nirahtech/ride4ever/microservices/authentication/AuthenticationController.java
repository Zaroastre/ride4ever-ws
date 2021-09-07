/******************************************************************
 * Copyright 2021 Ride4Ever
 *
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.microservices.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RequestMapping("/authentication")
@RestController
public final class AuthenticationController implements AuthenticationApi {


    @Autowired
    private AuthenticationService service;

    @PostMapping("/login")
    @ResponseBody
    @Override
    public final Session login(@RequestBody final Credential credential) throws RuntimeException {
        // return this.service.login(credential);
        return  null;
    }

    @DeleteMapping("/logout/{sessionID}")
    @ResponseBody
    @Override
    public void logout(@PathVariable String sessionID) throws RuntimeException {
        this.service.logout(sessionID);
    }

}
