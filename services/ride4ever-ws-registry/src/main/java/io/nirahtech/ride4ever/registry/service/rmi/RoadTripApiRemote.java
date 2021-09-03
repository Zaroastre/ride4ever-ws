package io.nirahtech.ride4ever.registry.service.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import io.nirahtech.ride4ever.registry.entity.RoadTrip;

public interface RoadTripApiRemote extends Remote {
    RoadTrip create(RoadTrip roadtrip) throws RemoteException;
    RoadTrip read(RoadTrip roadtrip) throws RemoteException;
    RoadTrip uddate(RoadTrip roadtrip) throws RemoteException;
    RoadTrip delete(RoadTrip roadtrip) throws RemoteException;
    List<RoadTrip> findAll() throws RemoteException;
}
