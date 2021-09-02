/******************************************************************
 * Copyright 2021 Ride4Ever
 * 
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.microservices.activity;

public enum EventType {
    ACCOUNT_REGISTRATION,

    LOGIN,
    LOGOUT,
    
    ROADTRIP_SEARCH,

    CREATE_CANDIDATE,
    CANCEL_CANDIDATE,
    DECLINE_CANDIDATE,
    ACCEPT_CANDIDATE,

    ORGANIZE_ROADTRIP,
    UPDATE_ROADTRIP,
    DELETE_ROADTRIP;

    public static final EventType parse(String value) {
        for (EventType enumeration : EventType.values()) {
            if (enumeration.name().equalsIgnoreCase(value)) {
                return enumeration;
            }
        }
        return null;
    }
}
