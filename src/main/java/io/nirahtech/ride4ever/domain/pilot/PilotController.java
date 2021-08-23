package io.nirahtech.ride4ever.domain.pilot;

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

import io.nirahtech.ride4ever.core.environment.Biker;

@CrossOrigin("*")
@RequestMapping("/pilots")
@RestController
public final class PilotController implements PilotApi {

    private final PilotService service = PilotService.getInstance();

    @PostMapping
    @Override
    public Biker create(@RequestBody Biker entity) {
        return this.service.create(entity);
    }

    @GetMapping("/{identifier}")
    @Override
    public Biker read(@PathVariable Integer identifier) {
        return this.service.read(identifier);
    }

    @PutMapping("/{identifier}")
    @Override
    public Biker update(@PathVariable Integer identifier, @RequestBody Biker entity) {
        return this.service.update(identifier, entity);
    }

    @DeleteMapping("/{identifier}")
    @Override
    public Biker delete(@PathVariable Integer identifier) {
        return this.service.delete(identifier);
    }

    @GetMapping
    @Override
    public List<Biker> findAll() {
        return this.service.findAll();
    }

    @Override
    public Biker findByEmail(String email) {
        // TODO Auto-generated method stub
        return null;
    }

}
