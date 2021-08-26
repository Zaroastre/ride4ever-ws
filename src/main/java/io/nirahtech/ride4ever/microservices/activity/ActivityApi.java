package io.nirahtech.ride4ever.microservices.activity;

import java.util.List;

import io.nirahtech.ride4ever.infrastructure.api.Crud;

interface ActivityApi extends Crud<Integer,  Activity> {
    List<Activity> findAll();
    List<Activity> findByEvent(EventType event);
    List<Activity> findByPseudo(String pseudo);
}
