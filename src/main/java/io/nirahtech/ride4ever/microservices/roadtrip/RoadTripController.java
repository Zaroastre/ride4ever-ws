package io.nirahtech.ride4ever.microservices.roadtrip;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
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
import io.nirahtech.ride4ever.microservices.location.RequestService;

@CrossOrigin("*")
@RequestMapping("/roadtrips")
@RestController
public final class RoadTripController implements RoadTripApi {

    @Autowired
    private RoadTripService service;

    @Autowired
	private RequestService requestService;

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
    public RoadTrip delete(@PathVariable Integer identifier) {
        return this.service.delete(identifier);
    }

    @Override
    public List<RoadTrip> findAll() {
        return this.service.findAll();
    }
    @GetMapping
    public List<RoadTrip> findAll(HttpServletRequest request) {
        List<RoadTrip> roadTrips = this.findAll();
        if (request.getParameterMap().keySet().contains("organizer_pseudo")) {
            roadTrips = roadTrips
                .stream()
                .filter((roadtrip) -> roadtrip.getOrganizer().getPseudo().equalsIgnoreCase(request.getParameter("organizer_pseudo")))
                .collect(Collectors.toList());
        }
        if (request.getParameterMap().keySet().contains("status")) {
            roadTrips = roadTrips
                .stream()
                .filter((roadtrip) -> roadtrip.getStatus().name().equalsIgnoreCase(request.getParameter("status")))
                .collect(Collectors.toList());
        }
        if (request.getParameterMap().keySet().contains("candidate_pseudo")) {
            Iterator<RoadTrip> iterator = roadTrips.iterator();
            while (iterator.hasNext()) {
                RoadTrip roadTrip = iterator.next(); // must be called before you can call i.remove()
                Optional<Biker> candidateFound = null;
                if (roadTrip.getCandidates() != null && !roadTrip.getCandidates().isEmpty()) {
                    candidateFound = roadTrip.getCandidates()
                        .stream()
                        .filter((item) -> item.getPseudo().equalsIgnoreCase(request.getParameter("candidate_pseudo")))
                        .findFirst();
                }
                if (candidateFound == null || (candidateFound != null && candidateFound.isEmpty())) {
                    iterator.remove();
                }
            }
        }
        if (request.getParameterMap().keySet().contains("biker_pseudo")) {
            Iterator<RoadTrip> iterator = roadTrips.iterator();
            while (iterator.hasNext()) {
                RoadTrip roadTrip = iterator.next(); // must be called before you can call i.remove()
                Optional<Biker> bikerFound = null;
                if (roadTrip.getBikers() != null && !roadTrip.getBikers().isEmpty()) {
                    bikerFound = roadTrip.getBikers()
                        .stream()
                        .filter((item) -> item.getPseudo().equalsIgnoreCase(request.getParameter("biker_pseudo")))
                        .findFirst();
                }
                if (bikerFound == null || (bikerFound != null && bikerFound.isEmpty())) {
                    iterator.remove();
                }
            }
        }
        return roadTrips;
    }

    @GetMapping("/types")
    public RoadTripType[] getRoadTripsTypes() {
        return RoadTripType.values();
    }

}
