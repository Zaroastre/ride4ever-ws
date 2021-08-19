package io.nirahtech.ride4ever.application.scheduler;

import java.util.Date;

/**
 * Class that represents a Scheduled Task Factory.
 *
 * @author METIVIER Nicolas
 * @since 0.0.1
 */
public final class ScheduledTaskFactory {

    private final Class<? extends ScheduledTask> schedultedTaskClass;
    private Date start = null;
    private Date end = null;
    private long executionIntervalInMillisecondes = 500;
    private boolean canRestartOnFailed = true;
    private long restartDelayInMilliSecondes = 500;
    private int maxRetry = 3;
    private int maxExecutions = -1;

    /**
     * Constructor
     * @param schedultedTaskClass Class that extends AbstractScheduledTask.
     */
    public ScheduledTaskFactory(Class<? extends ScheduledTask> schedultedTaskClass) {
        this.schedultedTaskClass = schedultedTaskClass;
    }

    /**
     * Set when scheduled task must stop.
     * @param end Date and time when task must stop. If null, task never stop.
     * @return  The factory.
     */
    public final ScheduledTaskFactory withEnd(Date end) {
        this.end = end;
        return this;
    }

    /**
     * Allow or not reexecution on failure.
     * @param canRestartOnFailed All task reexecution if an error occured.
     * @return  The factory.
     */
    public final ScheduledTaskFactory withCanRestartOnFailed(boolean canRestartOnFailed) {
        this.canRestartOnFailed = canRestartOnFailed;
        return this;
    }

    /**
     * Set execution interval.
     * @param executionIntervalInMillisecondes Execution Interval In Millisecondes
     * @return  The factory.
     */
    public final ScheduledTaskFactory withExecutionIntervalInMillisecondes(long executionIntervalInMillisecondes) {
        this.executionIntervalInMillisecondes = executionIntervalInMillisecondes;
        return this;
    }

    /**
     * Set max retry.
     * @param maxRetry Total atempts if task failed.
     * @return  The factory.
     */
    public final ScheduledTaskFactory withMaxRetry(int maxRetry) {
        this.maxRetry = maxRetry;
        return this;
    }

    /**
     * Set defered start date.
     * @param start Date and time when task must start. If null, task start immediatly without delay.
     * @return  The factory.
     */
    public final ScheduledTaskFactory withStart(Date start) {
        this.start = start;
        return this;
    }

    /**
     * Set restart delay.
     * @param restartDelayInMilliSecondes Restart Delay In Milli Secondes
     * @return  The factory.
     */
    public final ScheduledTaskFactory withRestartDelayInMilliSecondes(long restartDelayInMilliSecondes) {
        this.restartDelayInMilliSecondes = restartDelayInMilliSecondes;
        return this;
    }

    /**
     * Set max execution.
     * @param maxExecutions Total task execution. If 0 then no limit.
     * @return  The factory.
     */
    public final ScheduledTaskFactory withMaxExecution(int maxExecutions) {
        this.maxExecutions = maxExecutions;
        return this;
    }

    /**
     * Build the scheduled task from the factory.
     *
     * @return The scheduled task.
     * @throws ApiException
     */
    public ScheduledTask build() throws RuntimeException {
        ScheduledTask task = null;
        try {
            task = this.schedultedTaskClass
                    .getConstructor(
                        Date.class,
                        Date.class,
                        Long.class,
                        Integer.class,
                        Boolean.class,
                        Long.class,
                        Integer.class)
                    .newInstance(this.start, this.end, this.executionIntervalInMillisecondes, this.maxExecutions, this.canRestartOnFailed,
                            this.restartDelayInMilliSecondes, this.maxRetry);
        } catch (Exception error) {
            throw new RuntimeException(error);
        }
        return task;
    }
}
