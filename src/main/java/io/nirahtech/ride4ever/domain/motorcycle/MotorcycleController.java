package io.nirahtech.ride4ever.domain.motorcycle;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RequestMapping("/motorcycles")
@RestController
public final class MotorcycleController implements MotorcycleApi {

    private final MotorcycleService service = new MotorcycleService();

    
}
