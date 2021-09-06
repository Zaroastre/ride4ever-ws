/******************************************************************
 * Copyright 2021 Ride4Ever
 *
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.motorcycleclub.club;

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
@RequestMapping("/members")
@RestController
public final class MemberController implements MemberApi {

    @Autowired
    private MemberService service;

    @PostMapping
    @Override
    public Member create(@RequestBody Member entity) {
        return this.service.create(entity);
    }

    @GetMapping("/{identifier}")
    @Override
    public Member read(@PathVariable Integer identifier) {
        return this.service.read(identifier);
    }

    @PutMapping("/{identifier}")
    @Override
    public Member update(@PathVariable Integer identifier, @RequestBody Member entity) {
        return this.service.update(identifier, entity);
    }

    @DeleteMapping("/{identifier}")
    @Override
    public Member delete(@PathVariable Integer identifier) {
        return this.service.delete(identifier);
    }

    @GetMapping
    public List<Member> findAll(HttpServletRequest request) {
        List<Member> result = null;
        if (result == null) {
            result = this.findAll();
        }
        return result;
    }

    @Override
    public List<Member> findAll() {
        return this.service.findAll();
    }

}
