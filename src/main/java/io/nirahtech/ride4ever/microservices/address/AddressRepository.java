package io.nirahtech.ride4ever.microservices.address;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("addressRepository")
interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findByNumberAndStreetAndZipCodeAndCountry(int number, String street, int zipCode, Country country);

    // @Modifying(clearAutomatically = true)
    // @Query("UPDATE Address a SET a.address = :address WHERE a.identifier = :identifier")
    // int update(@Param("identifier") int identifier, @Param("address") String address);
}
