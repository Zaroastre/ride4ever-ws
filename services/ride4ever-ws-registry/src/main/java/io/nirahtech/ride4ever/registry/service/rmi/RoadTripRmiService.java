package io.nirahtech.ride4ever.registry.service.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import io.nirahtech.ride4ever.registry.entity.RoadTrip;

public class RoadTripRmiService extends UnicastRemoteObject implements RoadTripApiRemote {

    public RoadTripRmiService() throws RemoteException {
        super();
    }

    @Override
    public RoadTrip create(RoadTrip roadtrip) throws RemoteException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RoadTrip read(RoadTrip roadtrip) throws RemoteException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RoadTrip uddate(RoadTrip roadtrip) throws RemoteException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RoadTrip delete(RoadTrip roadtrip) throws RemoteException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<RoadTrip> findAll() throws RemoteException {
        // TODO Auto-generated method stub
        return null;
    }
    
}
