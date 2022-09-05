package TaskScheduler.Manager;

import TaskScheduler.TaskHandler.AbstractCustomTask;
import TaskScheduler.TaskHandler.ScheduleAtFixDelayCustomTask;
import TaskScheduler.TaskHandler.ScheduleCustomTask;
import TaskScheduler.TaskHandler.ScheduleFixedRateCustomTask;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TaskSchedulerManager {
    private final ThreadPoolExecutor workerPool;

    public TaskSchedulerManager(int nThreads) {
        workerPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(nThreads);
    }

    public static Runnable getRunnableTask(String task) {
        return () -> {
            System.out.println(task + " Started at " + System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(task + " Completed at " + System.currentTimeMillis());

        };
    }

    public void schedule(Runnable task, TimeUnit timeUnit, long initialDelay) {
        AbstractCustomTask customTask = new ScheduleCustomTask(task, timeUnit, initialDelay, null);
        workerPool.submit(customTask);
    }

    public void scheduleAtFixRate(Runnable task, TimeUnit timeUnit, long initialDelay, long period) {
        AbstractCustomTask customTask = new ScheduleFixedRateCustomTask(task, timeUnit, initialDelay, period);
        workerPool.submit(customTask);
    }

    public void scheduleAtFixDelay(Runnable task, TimeUnit timeUnit, long initialDelay, long period) {
        AbstractCustomTask customTask = new ScheduleAtFixDelayCustomTask(task, timeUnit, initialDelay, period);
        workerPool.submit(customTask);
    }
}
