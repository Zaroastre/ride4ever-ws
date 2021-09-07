/******************************************************************
 * Copyright 2021 Ride4Ever
 *
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.microservices.club;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
@RequestMapping("/motorcycle-clubs")
@RestController
public final class MotorcycleClubController implements MotorcycleClubApi {

    @Autowired
    private MotorcycleClubService service;

    @PostMapping
    @Override
    public MotorcycleClub create(@RequestBody MotorcycleClub entity) {
        return this.service.create(entity);
    }

    @GetMapping("/{id}")
    @Override
    public MotorcycleClub read(@PathVariable String id) {
        return this.service.read(id);
    }

    @PutMapping("/{id}")
    @Override
    public MotorcycleClub update(@PathVariable String id, @RequestBody MotorcycleClub entity) {
        return this.service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    @Override
    public void delete(@PathVariable String id) {
        this.service.delete(id);
    }

    @GetMapping
    public List<MotorcycleClub> findAll(HttpServletRequest request) {
        List<MotorcycleClub> result = null;
        if (result == null) {
            result = this.findAll();
        }
        return result;
    }

    @Override
    public List<MotorcycleClub> findAll() {
        return this.service.findAll();
    }

}
