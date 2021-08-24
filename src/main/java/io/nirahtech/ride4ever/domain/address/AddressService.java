package io.nirahtech.ride4ever.domain.address;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.nirahtech.ride4ever.core.environment.Address;

@Component("addressService")
public final class AddressService implements AddressApi {

    @Autowired
    private AddressRepository repository;

    private static final AddressService SINGLETON = new AddressService();

    private AddressService() { }

    public static AddressService getInstance() {
        return SINGLETON;
    }

    @Override
    public Address create(Address entity) {
        return this.repository.save(entity);
    }

    @Override
    public Address read(Integer identifier) {
        Optional<Address> entity = this.repository.findById(identifier);
        if (entity.isPresent()) {
            return entity.get();
        }
        return null;
    }

    @Override
    public Address update(Integer identifier, Address entity) {
        return this.repository.save(entity);
    }

    @Override
    public Address delete(Integer identifier) {
        Address entity = this.read(identifier);
        if (entity != null) {
            this.repository.deleteById(entity.getIdentifier());
        }
        return entity;
    }

    @Override
    public List<Address> findAll() {
        List<Address> list = new ArrayList<>();
        this.repository.findAll().forEach((item) -> {
            list.add(item);
        });
        return list;
    }

}
