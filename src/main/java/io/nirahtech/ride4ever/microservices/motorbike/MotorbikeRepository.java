package io.nirahtech.ride4ever.microservices.motorbike;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("motorbikeRepository")
public interface MotorbikeRepository extends JpaRepository<Motorbike, Integer> {
    Motorbike findByLicensePlate(final String licensePlate);
}
