package io.nirahtech.ride4ever.microservices.motorbike;

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
@RequestMapping("/motorbikes")
@RestController
public final class MotorbikeController implements MotorbikeApi {
    
    @Autowired
    private MotorbikeService service;

    @PostMapping
    @Override
    public Motorbike create(@RequestBody Motorbike entity) {
        return this.service.create(entity);
    }

    @GetMapping("/{identifier}")
    @Override
    public Motorbike read(@PathVariable Integer identifier) {
        return this.service.read(identifier);
    }

    @PutMapping("/{identifier}")
    @Override
    public Motorbike update(@PathVariable Integer identifier, @RequestBody Motorbike entity) {
        return this.service.update(identifier, entity);
    }

    @DeleteMapping("/{identifier}")
    @Override
    public Motorbike delete(@PathVariable Integer identifier) {
        return this.service.delete(identifier);
    }

    @GetMapping
    @Override
    public List<Motorbike> findAll() {
        return this.service.findAll();
    }

    @Override
    public Motorbike findByLicensePlate(String licensePlate) {
        return this.service.findByLicensePlate(licensePlate);
    }

    @GetMapping("/types")
    public MotorbikeType[] getMotorbikesTypes() {
        return MotorbikeType.values();
    }

    @GetMapping("/brands")
    public Brand[] getBrands() {
        return Brand.values();
    }
}
