package io.nirahtech.ride4ever.domain.pilot;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nirahtech.ride4ever.core.environment.Pilot;

@CrossOrigin("*")
@RequestMapping("/pilots")
@RestController
public final class PilotController implements PilotApi {

    private final PilotService service = PilotService.getInstance();

    @Override
    public Pilot create(@RequestBody Pilot entity) {
        return this.service.create(entity);
    }

    @Override
    public Pilot read(Integer identifier) {
        return this.service.read(identifier);
    }

    @Override
    public Pilot update(Integer identifier, @RequestBody Pilot entity) {
        return this.service.update(identifier, entity);
    }

    @Override
    public Pilot delete(Integer identifier) {
        return this.service.delete(identifier);
    }

    @Override
    public List<Pilot> findAll() {
        return this.service.findAll();
    }

    @Override
    public Pilot findByEmail(String email) {
        // TODO Auto-generated method stub
        return null;
    }

}
