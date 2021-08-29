package io.nirahtech.ride4ever.microservices.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("reservationRepository")
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    
}
