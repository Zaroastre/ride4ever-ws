package io.nirahtech.ride4ever.domain.address;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.nirahtech.ride4ever.core.environment.Address;

public final class AddressService implements AddressApi {
    private static final Map<Integer, Address> DATABASE = new HashMap<>();

    private static final AddressService SINGLETON = new AddressService();

    private AddressService() {

    }

    public static AddressService getInstance() {
        return SINGLETON;
    }

    @Override
    public Address create(Address entity) {
        Set<Integer> identifiers = DATABASE.keySet();
        int maxIdentifier = 0;
        for (Integer identifier : identifiers) {
            if (identifier > maxIdentifier) {
                maxIdentifier = identifier;
            }
        }
        maxIdentifier += 1;
        entity.setIdentifier(maxIdentifier);
        DATABASE.put(maxIdentifier, entity);
        return entity;
    }

    @Override
    public Address read(Integer identifier) {
        if (DATABASE.containsKey(identifier)) {
            return DATABASE.get(identifier);
        }
        return null;
    }

    @Override
    public Address update(Integer identifier, Address entity) {
        if (DATABASE.containsKey(identifier)) {
            return DATABASE.replace(identifier, entity);
        }
        return null;

    }

    @Override
    public Address delete(Integer identifier) {
        if (DATABASE.containsKey(identifier)) {
            return DATABASE.remove(identifier);
        }
        return null;
    }

    @Override
    public List<Address> findAll() {
        return new ArrayList<>(DATABASE.values());
    }

}
