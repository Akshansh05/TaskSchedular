package TaskScheduler.TaskHandler;

import TaskScheduler.TaskHandler.BaseCustomTask;

import java.util.concurrent.TimeUnit;

public class ScheduleAtFixDelayCustomTask extends BaseCustomTask {
    public ScheduleAtFixDelayCustomTask(Runnable task, TimeUnit timeUnit, Long initialDelay, Long period) {
        super(task, timeUnit, initialDelay, period);
    }
}
