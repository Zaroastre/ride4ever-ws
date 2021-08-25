package io.nirahtech.ride4ever.threading.scheduler;

/**
 * Interface to define schedulable API.
 *
 * @author METIVIER Nicolas
 * @since 0.0.1
 */
interface Schedulable {

    /**
     * Execute the scheduled action.
     * @throws ApiException The exception is the task failed.
     */
    void execute() throws RuntimeException;

    /**
     * Check if the task is processing.
     * @return
     */
    boolean isProcessing();

    /**
     * Check if the task is waiting for a ne process cycle.
     * @return
     */
    boolean isWaiting();

    /**
     * Check if the task is terminated.
     * @return
     */
    boolean isDone();
}
