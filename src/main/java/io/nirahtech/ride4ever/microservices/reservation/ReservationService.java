/******************************************************************
 * Copyright 2021 Ride4Ever
 * 
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.microservices.reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("reservationService")
public final class ReservationService implements ReservationApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationService.class);

    @Autowired
    private ReservationRepository repository;

    private static final ReservationService SINGLETON = new ReservationService();

    private ReservationService() { }

    public static ReservationService getInstance() {
        return SINGLETON;
    }

    @Override
    public Reservation create(Reservation entity) {
        LOGGER.info("A new reservation will be registered...");
        Reservation createdReservation = this.repository.save(entity);
        if (createdReservation != null) {
            LOGGER.info("Reservation is successfully registered as " + createdReservation.getIdentifier());
        } else {
            LOGGER.warn("New reservation is not registered.");
        }
        return createdReservation;
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
