package io.nirahtech.ride4ever.domain.address;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.nirahtech.ride4ever.core.environment.Address;

@Repository("addressRepository")
public interface AddressRepository extends CrudRepository<Address, Integer> {
    
}
