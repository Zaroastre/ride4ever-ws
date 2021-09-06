/******************************************************************
 * Copyright 2021 Ride4Ever
 *
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.motorcycleclub.club;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("motorbikeService")
final class MemberService implements MemberApi {

    @Autowired
    private MemberRepository repository;

    private static final MemberService SINGLETON = new MemberService();

    private MemberService() { }

    public static MemberService getInstance() {
        return SINGLETON;
    }

    @Override
    public Member create(Member entity) {
        return this.repository.save(entity);
    }

    @Override
    public Member read(Integer identifier) {
        Optional<Member> entity = this.repository.findById(identifier);
        if (entity.isPresent()) {
            return entity.get();
        }
        return null;
    }

    @Override
    public Member update(Integer identifier, Member entity) {
        return this.repository.save(entity);
    }

    @Override
    public Member delete(Integer identifier) {
        Member entity = this.read(identifier);
        if (entity != null) {
            this.repository.deleteById(entity.getIdentifier());
        }
        return entity;
    }

    @Override
    public List<Member> findAll() {
        List<Member> list = new ArrayList<>();
        this.repository.findAll().forEach((item) -> {
            list.add(item);
        });
        return list;
    }

}
