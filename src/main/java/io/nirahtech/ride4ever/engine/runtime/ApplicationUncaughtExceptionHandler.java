/******************************************************************
 * Copyright 2021 Ride4Ever
 * 
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.engine.runtime;

/**
 * Class that represents an Application Uncaught Exception Handler.
 *
 * @author METIVIER Nicolas
 * @since 0.0.1
 */
public class ApplicationUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        System.err.println("THREAD " + thread.getId() + "("+thread.getName()+")" + " : " + throwable.getMessage());
        throwable.printStackTrace();
    }
}
