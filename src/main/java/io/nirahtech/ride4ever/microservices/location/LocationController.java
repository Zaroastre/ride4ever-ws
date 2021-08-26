package io.nirahtech.ride4ever.microservices.location;

import com.ip2location.IPResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RequestMapping("/locations")
@RestController
public final class LocationController implements LocationApi {
    
    @Autowired
    private LocationService service;

    @GetMapping
    @Override
    public IPResult resolve(@RequestParam(required=true) final String ipAddress) {
        return this.service.resolve(ipAddress);
    }

    
}
