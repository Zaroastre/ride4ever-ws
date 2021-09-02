/******************************************************************
 * Copyright 2021 Ride4Ever
 * 
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.microservices.recovery;

import java.time.temporal.ChronoUnit;

final class GuardianThread extends Thread {

    private final int duration;
    private final ChronoUnit unit;
    private final Recovery recovery;
    private String code = null;
    
    GuardianThread(final int duration, final ChronoUnit unit, final Recovery recovery) {
        this.duration = duration;
        this.unit = unit;
        this.recovery = recovery;
    }

    final void setCode(final String code) {
        this.code = code;
    }

    final String getCode() {
        return this.code;
    }

    @Override
    public void run() {
        super.run();
    }

    void notifySuccess() {

    }

    final Recovery getRecovery() {
        return this.recovery;
    }

    void terminate() {
        
    }

}
