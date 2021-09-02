/******************************************************************
 * Copyright 2021 Ride4Ever
 * 
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.microservices.recovery;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RequestMapping("/recovery")
@RestController
public final class RecoveryController implements RecoveryApi {

    private final RecoveryService service = new RecoveryService();

    @PostMapping("/search-account")
    @ResponseBody
    @Override
    public Recovery findAccountByEmail(String email) throws RuntimeException {
        return this.service.findAccountByEmail(email);
    }

    @PostMapping("/send-email")
    @ResponseBody
    @Override
    public Recovery sendCodeByEmailTo(Recovery recovery) throws RuntimeException {
        return this.service.sendCodeByEmailTo(recovery);
    }

    @PostMapping("/send-sms")
    @ResponseBody
    @Override
    public Recovery sendCodeBySmsTo(Recovery recovery) throws RuntimeException {
        return this.service.sendCodeBySmsTo(recovery);
    }

    @PostMapping("/validate-code")
    @ResponseBody
    @Override
    public Recovery verifyCode(Recovery recovery) throws RuntimeException {
        return this.service.verifyCode(recovery);
    }

    @PostMapping("/change-password")
    @ResponseBody
    @Override
    public Recovery changePassword(Recovery recovery) throws RuntimeException {
        return this.service.changePassword(recovery);
    }

    
}
