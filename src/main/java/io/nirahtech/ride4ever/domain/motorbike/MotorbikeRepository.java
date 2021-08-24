package io.nirahtech.ride4ever.domain.motorbike;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.nirahtech.ride4ever.core.environment.Motorbike;

@Repository("motorbikeRepository")
public interface MotorbikeRepository extends JpaRepository<Motorbike, Integer> {
    Motorbike findByLicensePlate(final String licensePlate);
}
