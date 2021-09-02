/******************************************************************
 * Copyright 2021 Ride4Ever
 * 
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.microservices.reservation;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nirahtech.ride4ever.microservices.biker.Biker;
import io.nirahtech.ride4ever.microservices.biker.BikerService;

@CrossOrigin("*")
@RequestMapping("/reservations")
@RestController
public final class ReservationController implements ReservationApi {

    @Autowired
    private ReservationService service;

    @Autowired
    private BikerService bikerService;

    @PostMapping
    @Override
    public Reservation create(@RequestBody Reservation entity) {
        System.out.println(entity);
        return this.service.create(entity);
    }

    @GetMapping("/{identifier}")
    @Override
    public Reservation read(@PathVariable Integer identifier) {
        return this.service.read(identifier);
    }

    @PutMapping("/{identifier}")
    @Override
    public Reservation update(@PathVariable Integer identifier, @RequestBody Reservation entity) {
        return this.service.update(identifier, entity);
    }

    @DeleteMapping("/{identifier}")
    @Override
    public Reservation delete(@PathVariable Integer identifier) {
        return this.service.delete(identifier);
    }

    @Override
    public List<Reservation> findAll() {
        return this.service.findAll();
    }
    @GetMapping
    public List<Reservation> findAll(HttpServletRequest request) {
        List<Reservation> reservations = this.findAll();
        if (request.getParameterMap().keySet().contains("biker_pseudo")) {
            Biker biker = bikerService.findByPseudo(request.getParameter("biker_pseudo"));
            if (biker != null) {
                reservations = reservations
                    .stream()
                    .filter((reservation) -> reservation.getBiker() != null && reservation.getBiker().getIdentifier() == biker.getIdentifier())
                    .collect(Collectors.toList());
            }
        }
        if (request.getParameterMap().keySet().contains("date")) {
            reservations = reservations
                .stream()
                .filter((reservation) -> reservation.getDate() == Timestamp.valueOf(request.getParameter("date")))
                .collect(Collectors.toList());
        }
        if (request.getParameterMap().keySet().contains("status")) {
            String[] allStatus = request.getParameter("status").split(",");
            List<Reservation> matchedList = new ArrayList<>();
            for (String status : allStatus) {
                matchedList.addAll(reservations
                    .stream()
                    .filter((reservation) -> reservation.getStatus() == ReservationStatus.parse(status))
                    .collect(Collectors.toList()));
            }
            reservations = matchedList;
        }
        return reservations;
    }

    @GetMapping("/status")
    public ReservationStatus[] getReservationsStatus() {
        return ReservationStatus.values();
    }

}
