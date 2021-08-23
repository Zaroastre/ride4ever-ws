package io.nirahtech.ride4ever.domain.roadtrip;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nirahtech.ride4ever.core.environment.RoadTrip;
import io.nirahtech.ride4ever.core.environment.RoadTripType;

@CrossOrigin("*")
@RequestMapping("/roadtrips")
@RestController
public final class RoadTripController implements RoadTripApi {

    private final RoadTripService service = RoadTripService.getInstance();

    @PostMapping
    @Override
    public RoadTrip create(@RequestBody RoadTrip entity) {
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

    @GetMapping
    @Override
    public List<RoadTrip> findAll() {
        return this.service.findAll();
    }

    @GetMapping("/types")
    public RoadTripType[] getRoadTripsTypes() {
        return RoadTripType.values();
    }

}
