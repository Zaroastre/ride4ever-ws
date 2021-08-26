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
}
