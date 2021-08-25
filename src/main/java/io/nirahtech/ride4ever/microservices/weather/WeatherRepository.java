package io.nirahtech.ride4ever.microservices.weather;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("weatherRepository")
public interface WeatherRepository extends JpaRepository<Weather, Integer> {

}
