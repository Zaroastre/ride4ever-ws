package io.nirahtech.ride4ever.threading.scheduler;

/**
 * Abstract class that represetns a Task.
 *
 * @author METIVIER Nicolas
 * @since 0.0.1
 */
public abstract class Task extends Thread {

    /**
     * Default Task Constructor.
     * @param taskName Task Name
     */
    public Task(String taskName) {
        super(taskName);
    }
}
