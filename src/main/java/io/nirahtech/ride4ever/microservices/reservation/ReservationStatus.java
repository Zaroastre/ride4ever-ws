package io.nirahtech.ride4ever.microservices.reservation;

public enum ReservationStatus {
    PENDING,
    ACCEPTED,   
    DENIED;

    public static final ReservationStatus parse(final String value) {
        for (ReservationStatus enumeration : ReservationStatus.values()) {
            if (enumeration.name().equalsIgnoreCase(value)) {
                return enumeration;
            }
        }
        return null;
    }
}
