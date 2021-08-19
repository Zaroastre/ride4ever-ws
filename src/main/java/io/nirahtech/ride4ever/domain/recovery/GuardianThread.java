package io.nirahtech.ride4ever.domain.recovery;

import java.time.temporal.ChronoUnit;

public final class GuardianThread extends Thread {

    private final int duration;
    private final ChronoUnit unit;
    private final Recovery recovery;
    private String code = null;
    
    public GuardianThread(final int duration, final ChronoUnit unit, final Recovery recovery) {
        this.duration = duration;
        this.unit = unit;
        this.recovery = recovery;
    }

    public final void setCode(final String code) {
        this.code = code;
    }

    public final String getCode() {
        return this.code;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        super.run();
    }

    public void notifySuccess() {

    }

    public final Recovery getRecovery() {
        return this.recovery;
    }

    public void terminate() {
        
    }

}
