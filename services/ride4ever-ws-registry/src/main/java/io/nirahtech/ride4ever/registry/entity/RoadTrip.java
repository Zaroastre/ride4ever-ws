package io.nirahtech.ride4ever.registry.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoadTrip implements Serializable {
    private int identifier;
    private String title;
}
