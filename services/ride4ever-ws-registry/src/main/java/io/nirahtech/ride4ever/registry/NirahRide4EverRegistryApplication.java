/******************************************************************
 * Copyright 2021 Ride4Ever
 * 
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.registry;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.nirahtech.ride4ever.registry.service.rmi.RoadTripApiRemote;
import io.nirahtech.ride4ever.registry.service.rmi.RoadTripRmiService;

public class NirahRide4EverRegistryApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(NirahRide4EverRegistryApplication.class);

    private static final ApplicationConfiguration CONFIGURATION = ApplicationConfiguration.getInstance();
    public static void main(String[] args) {
        LOGGER.info("Starting the " + NirahRide4EverRegistryApplication.class.getSimpleName() + " RMI micro-service...");
        String rmiPort = CONFIGURATION.getProperty("rmi.port");
        LOGGER.info("rmi.port found in configuration file: " + rmiPort);
        if (rmiPort != null) {
            int port = 0;
            try {
                port = Integer.parseInt(rmiPort);
            } catch (NumberFormatException exception) {
                LOGGER.error(exception.getMessage());
                exception.printStackTrace();
                System.exit(1);
            }
            if (port > 0) {
                try {
                    LOGGER.info("Starting the RMI registry with port: " + rmiPort);
                    LocateRegistry.createRegistry(port);
                    final String RMI_URL = String.format("rmi://%s:%s/", "localhost", port);
                    LOGGER.info("RMI server URL: " + RMI_URL);
                    LOGGER.info("Registring " + RoadTripApiRemote.class.getSimpleName() + " into RMI...");
                    Naming.rebind(RMI_URL + RoadTripApiRemote.class.getSimpleName(), new RoadTripRmiService());
                    LOGGER.info(RoadTripApiRemote.class.getSimpleName() + " successfully registered in RMI server.");
                } catch (RemoteException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                    System.exit(1);
                } catch (MalformedURLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                    System.exit(1);
                }
            } else {
                LOGGER.error("Invalid port number.");
                System.exit(1);
            }
        } else {
            LOGGER.error("Unable to find property 'rmi.port' in configuration file.");
            System.exit(1);
        }
    }
}
