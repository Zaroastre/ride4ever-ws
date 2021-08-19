package io.nirahtech.ride4ever.domain.motorbike;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RequestMapping("/motorbikes")
@RestController
public final class MotorbikeController implements MotorbikeApi {

    private final MotorbikeService service = new MotorbikeService();


}
