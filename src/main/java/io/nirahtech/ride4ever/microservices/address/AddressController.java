package io.nirahtech.ride4ever.microservices.address;

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

@CrossOrigin("*")
@RequestMapping("/addresses")
@RestController
public final class AddressController implements AddressApi {

    @Autowired
    private AddressService service;

    @PostMapping
    @Override
    public Address create(@RequestBody Address entity) {
        System.out.println(entity);
        return this.service.create(entity);
    }

    @GetMapping("/{identifier}")
    @Override
    public Address read(@PathVariable Integer identifier) {
        return this.service.read(identifier);
    }

    @PutMapping("/{identifier}")
    @Override
    public Address update(@PathVariable Integer identifier, @RequestBody Address entity) {
        return this.service.update(identifier, entity);
    }

    @DeleteMapping("/{identifier}")
    @Override
    public Address delete(@PathVariable Integer identifier) {
        return this.service.delete(identifier);
    }

    @GetMapping
    @Override
    public List<Address> findAll() {
        return this.service.findAll();
    }
}
