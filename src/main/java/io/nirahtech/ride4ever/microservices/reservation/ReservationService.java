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

import io.nirahtech.ride4ever.microservices.activity.Activity;
import io.nirahtech.ride4ever.microservices.activity.ActivityService;
import io.nirahtech.ride4ever.microservices.activity.EventType;

@Component("reservationService")
public final class ReservationService implements ReservationApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationService.class);

    @Autowired
    private ReservationRepository repository;

    @Autowired
    private ActivityService activityService;

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
        Activity activity = new Activity(EventType.CREATE_CANDIDATE, entity.getBiker().getPseudo(), "Candidate for road trip: " + entity.getRoadTrip().getTitle());
        activityService.create(activity);
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
        Activity candidateActivity = null;
        Activity organizerActivity = null;
        if (entity.getStatus() == ReservationStatus.ACCEPTED) {
            candidateActivity = new Activity(EventType.CANDIDATE_ACCEPTED, entity.getBiker().getPseudo(), "Accepted for road trip: " + entity.getRoadTrip().getTitle());
            candidateActivity = new Activity(EventType.ACCEPT_CANDIDATE, entity.getRoadTrip().getOrganizer().getPseudo(), String.format("Reservation accepted for %s on road trip %s.", entity.getBiker().getPseudo(), entity.getRoadTrip().getTitle()));
        } else if (entity.getStatus() == ReservationStatus.DENIED) {
            candidateActivity = new Activity(EventType.CANDIDATE_DENIED, entity.getBiker().getPseudo(), "Denied for road trip: " + entity.getRoadTrip().getTitle());
            candidateActivity = new Activity(EventType.DECLINE_CANDIDATE, entity.getRoadTrip().getOrganizer().getPseudo(), String.format("Reservation denied for %s on road trip %s" + entity.getBiker().getPseudo(), entity.getRoadTrip().getTitle()));
        }
        if (candidateActivity != null && organizerActivity != null) {
            activityService.create(candidateActivity);
            activityService.create(organizerActivity);
        }
        return this.repository.save(entity);
    }

    @Override
    public void delete(Integer identifier) {
        Reservation entity = this.read(identifier);
        if (entity != null) {
            this.repository.deleteById(entity.getIdentifier());
        }
        Activity activity = new Activity(EventType.CANCEL_CANDIDATE, entity.getBiker().getPseudo(), "Reservation canceled for road trip: " + entity.getRoadTrip().getTitle());
        activityService.create(activity);
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
