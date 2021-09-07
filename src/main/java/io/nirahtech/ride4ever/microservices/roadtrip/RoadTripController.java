/******************************************************************
 * Copyright 2021 Ride4Ever
 *
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.microservices.roadtrip;

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
@RequestMapping("/roadtrips")
@RestController
public final class RoadTripController implements RoadTripApi {

    @Autowired
    private RoadTripService service;

    @Autowired
    private BikerService bikerService;

    @PostMapping
    @Override
    public RoadTrip create(@RequestBody RoadTrip entity) {
        System.out.println(entity);
        return this.service.create(entity);
    }

    @GetMapping("/{identifier}")
    @Override
    public RoadTrip read(@PathVariable Integer identifier) {
        return this.service.read(identifier);
    }

    @PutMapping("/{identifier}")
    @Override
    public RoadTrip update(@PathVariable Integer identifier, @RequestBody RoadTrip entity) {
        return this.service.update(identifier, entity);
    }

    @DeleteMapping("/{identifier}")
    @Override
    public void delete(@PathVariable Integer identifier) {
        this.service.delete(identifier);
    }

    @Override
    public List<RoadTrip> findAll() {
        return this.service.findAll();
    }
    @GetMapping
    public List<RoadTrip> findAll(HttpServletRequest request) {
        List<RoadTrip> roadTrips = this.findAll();
        if (request.getParameterMap().keySet().contains("organizer_pseudo")) {
            Biker biker = bikerService.findByPseudo(request.getParameter("organizer_pseudo"));
            if (biker != null) {
                roadTrips = roadTrips
                    .stream()
                    .filter((roadtrip) -> roadtrip.getOrganizer() != null && roadtrip.getOrganizer().getIdentifier() == biker.getIdentifier())
                    .collect(Collectors.toList());
            }
        }
        if (request.getParameterMap().keySet().contains("status")) {
            roadTrips = roadTrips
                .stream()
                .filter((roadtrip) -> roadtrip.getStatus().name().equalsIgnoreCase(request.getParameter("status")))
                .collect(Collectors.toList());
        }
        return roadTrips;
    }

    @GetMapping("/types")
    public RoadTripType[] getRoadTripsTypes() {
        return RoadTripType.values();
    }

}
