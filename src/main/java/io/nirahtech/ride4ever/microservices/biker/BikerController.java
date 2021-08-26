package io.nirahtech.ride4ever.microservices.biker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nirahtech.ride4ever.io.EmailBroker;

@CrossOrigin("*")
@RequestMapping("/bikers")
@RestController
public final class BikerController implements BikerApi {

    @Autowired
    private BikerService service;

    @PostMapping
    @Override
    public Biker create(@RequestBody Biker entity) {
        Biker createdAcount = this.service.create(entity);
        if (createdAcount != null) {
            EmailBroker.sendEmail(
                "Ride4Ever - Registration",
                String.format(
                    "Welcome %s ! You are now registered on the Ride4Ever application. Don't forget your password: %s",
                    createdAcount.getPseudo(),
                    createdAcount.getPassword()
                ),
                createdAcount.getEmail()
            );
        }
        return createdAcount;
    }

    @GetMapping("/{identifier}")
    @Override
    public Biker read(@PathVariable Integer identifier) {
        return this.service.read(identifier);
    }

    @PutMapping("/{identifier}")
    @Override
    public Biker update(@PathVariable Integer identifier, @RequestBody Biker entity) {
        return this.service.update(identifier, entity);
    }

    @DeleteMapping("/{identifier}")
    @Override
    public Biker delete(@PathVariable Integer identifier) {
        return this.service.delete(identifier);
    }

    @GetMapping
    @Override
    public List<Biker> findAll() {
        return this.service.findAll();
    }

    @Override
    public Biker findByEmail(String email) {
        return this.service.findByEmail(email);
    }

    @GetMapping("/genders")
    public Gender[] getGenders() {
        return Gender.values();
    }

}
