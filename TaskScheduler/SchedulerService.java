package TaskScheduler;

import TaskScheduler.Manager.TaskSchedulerManager;

import java.util.concurrent.TimeUnit;

import static TaskScheduler.Manager.TaskSchedulerManager.getRunnableTask;

public class SchedulerService {

    public static void main(String[] args) {

        System.out.println("Started at " + System.currentTimeMillis());
        TaskSchedulerManager taskSchedulerManager = new TaskSchedulerManager(10);
        taskSchedulerManager.schedule(getRunnableTask("task1"), TimeUnit.SECONDS, 1);
        taskSchedulerManager.scheduleAtFixRate(getRunnableTask("task2"), TimeUnit.SECONDS, 1, 2);
        taskSchedulerManager.scheduleAtFixDelay(getRunnableTask("task3"), TimeUnit.SECONDS, 1, 2);
        taskSchedulerManager.scheduleAtFixRate(getRunnableTask("task4"), TimeUnit.SECONDS, 1, 2);
    }


}