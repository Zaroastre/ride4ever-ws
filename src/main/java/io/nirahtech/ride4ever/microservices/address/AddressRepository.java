package io.nirahtech.ride4ever.microservices.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("addressRepository")
interface AddressRepository extends JpaRepository<Address, Integer> {
    
}
