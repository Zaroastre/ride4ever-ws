package io.nirahtech.ride4ever.microservices.reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("reservationService")
public final class ReservationService implements ReservationApi {

    @Autowired
    private ReservationRepository repository;

    private static final ReservationService SINGLETON = new ReservationService();

    private ReservationService() { }

    public static ReservationService getInstance() {
        return SINGLETON;
    }

    @Override
    public Reservation create(Reservation entity) {
        return this.repository.save(entity);
    }

    @Override
    public Reservation read(Integer identifier) {
        Optional<Reservation> entity = this.repository.findById(identifier);
        if (entity.isPresent()) {
            return entity.get();
        }
        return null;
    }

    @Override
    public Reservation update(Integer identifier, Reservation entity) {
        return this.repository.save(entity);
    }

    @Override
    public Reservation delete(Integer identifier) {
        Reservation entity = this.read(identifier);
        if (entity != null) {
            this.repository.deleteById(entity.getIdentifier());
        }
        return entity;
    }

    @Override
    public List<Reservation> findAll() {
        List<Reservation> list = new ArrayList<>();
        this.repository.findAll().forEach((item) -> {
            list.add(item);
        });
        return list;
    }

}
