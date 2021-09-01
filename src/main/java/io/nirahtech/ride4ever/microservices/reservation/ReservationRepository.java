package io.nirahtech.ride4ever.microservices.reservation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("reservationRepository")
public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
    
}
