package io.nirahtech.ride4ever.scheduler;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Class that represents a Scheduled Task.
 * 
 * @author METIVIER Nicolas
 * @since 0.0.1
 */
public abstract class ScheduledTask extends Task implements Schedulable {

    private boolean canContinue = false;
    private int totalExecutions = 0;

    private final Date startDate;
    private final Date endDate;
    private final long executionIntervalInMillisecondes;
    private final boolean canRestartOnFailed;
    private final long restartDelayInMilliSecondes;
    private final int maxRetry;
    private final int maxExecutions;

    private boolean isProcessing = false;
    private boolean isDone = false;
    private boolean isWaiting = false;

    /**
     * Scheduled Task Constructor
     * 
     * @param startDate                        Date when must start the task. (Use
     *                                         'null' for no delay).
     * @param endDate                          Date when must stop the task. (Use
     *                                         'null' for infinite delay).
     * @param executionIntervalInMillisecondes Interval in millisecondes between
     *                                         each task execution.
     * @param maxExecutions                    Total execution cycles.
     * @param canRestartOnFailed               Allow task to auto restart when
     *                                         execution failed.
     * @param restartDelayInMilliSecondes      Delay for auto restart after
     *                                         execution failed.
     * @param maxRetry                         Max execution failed.
     */
    protected ScheduledTask(Date startDate, Date endDate, Long executionIntervalInMillisecondes,
            Integer maxExecutions, Boolean canRestartOnFailed, Long restartDelayInMilliSecondes, Integer maxRetry) {
                super("AbstractScheduledTask-Thread");
        this.startDate = startDate;
        this.endDate = endDate;
        this.executionIntervalInMillisecondes = executionIntervalInMillisecondes;
        this.maxExecutions = maxExecutions;
        this.canRestartOnFailed = canRestartOnFailed;
        this.restartDelayInMilliSecondes = restartDelayInMilliSecondes;
        this.maxRetry = maxRetry;
        this.onCreate();
    }

    /**
     * Wait while the start date is not now.
     */
    private void waitRequiredDelayBeforeStart() {
        if (this.startDate != null) {
            final LocalDate now = LocalDate.now();
            final LocalDate nextTime = this.startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            final long delay = ChronoUnit.MILLIS.between(now, nextTime);
            if (delay > 0) {
                try {
                    ScheduledTask.sleep(delay);
                } catch (InterruptedException error) {
                    error.printStackTrace();
                }
            }
        }
    }

    /**
     * Wait Required Delay Before New Cycle
     */
    private void waitRequiredDelayBeforeNewCycle() {
        try {
            ScheduledTask.sleep(this.executionIntervalInMillisecondes);
        } catch (InterruptedException error) {
            error.printStackTrace();
        }
    }

    /**
     * Check if can start an new process cycle.
     * 
     * @return True if can process a new process cycle, else False.
     */
    private boolean canProcessNewCycle() {
        if (this.endDate == null) {
            if (this.maxExecutions == -1) {
                return true;
            }
            return this.maxExecutions > this.totalExecutions;
        }
        final LocalDateTime now = LocalDateTime.now();
        final LocalDateTime nextTime = this.endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        final Duration delay = Duration.between(now, nextTime);
        if (delay.toMillis() <= 0) {
            return false;
        }
        return true;
    }

    /**
     * Action to execute when the Scheduled task is created (ex: new ScheduledTask();).
     */
    protected void onCreate() {
        System.out.println("Scheduled Task is created.");
    }

    /**
     * Action to execute when the Scheduled task is starting (ex: task.start();).
     */
    protected void onStart() {
        System.out.println("Scheduled Task is starting...");
    }

    /**
     * Action to execute when the Scheduled task is starting to process a new cycle.
     */
    protected void onProcess() {
        System.out.println("Scheduled Task is procesing...");
    }

    /**
     * Action to execute when the Scheduled task is terminated.
     */
    protected void onTerminate() {
        System.out.println("Scheduled Task is terminated.");
    }

    /**
     * Action to execute when the Scheduled task process has failed.
     */
    protected void onFail() {
        System.err.println("Scheduled Task had failed.");
    }

    /**
     * Action to execute when the Scheduled task is restarting.
     */
    protected void onRestart() {
        System.out.println("Scheduled Task is restarting...");
    }

    @Override
    public final void run() {
        this.onStart();
        this.canContinue = true;
        this.isWaiting = true;
        this.waitRequiredDelayBeforeStart();

        while (this.canContinue) {
            try {
                this.isProcessing = true;
                this.onProcess();
                this.execute();
            } catch (RuntimeException error) {
                error.printStackTrace();
                this.onFail();
                if (this.canRestartOnFailed) {
                    this.restart();
                }
            }
            this.isProcessing = false;
            if (this.maxExecutions > 0) {
                this.totalExecutions++;
            }
            this.canContinue = this.canProcessNewCycle();
            if (this.canContinue) {
                this.waitRequiredDelayBeforeNewCycle();
            }
        }
        this.isDone = true;
        this.onTerminate();
    }

    /**
     * Restart the process.
     */
    private final void restart() {
        this.onRestart();
        int totalRestartAttempts = 0;
        while (totalRestartAttempts < this.maxRetry) {
            try {
                this.onProcess();
                this.execute();
                break;
            } catch (RuntimeException processError) {
                processError.printStackTrace();
                try {
                    ScheduledTask.sleep(this.restartDelayInMilliSecondes);
                } catch (InterruptedException sleepError) {
                    sleepError.printStackTrace();
                }
            }
            totalRestartAttempts++;
        }
        if (totalRestartAttempts == this.maxRetry) {

        }
    }

    @Override
    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public boolean isProcessing() {
        return this.isProcessing;
    }

    @Override
    public boolean isWaiting() {
        return this.isWaiting;
    }

}
