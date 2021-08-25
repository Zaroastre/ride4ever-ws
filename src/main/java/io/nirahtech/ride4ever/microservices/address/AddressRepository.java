package io.nirahtech.ride4ever.microservices.address;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.nirahtech.ride4ever.core.environment.Address;

@Repository("addressRepository")
interface AddressRepository extends CrudRepository<Address, Integer> {
    
}
