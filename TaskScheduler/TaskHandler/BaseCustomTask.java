package TaskScheduler.TaskHandler;

import lombok.SneakyThrows;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BaseCustomTask extends AbstractCustomTask {
    private final Runnable task;
    private final TimeUnit timeUnit;
    private final Long initialDelay;
    private final Long period;
    public BaseCustomTask(Runnable task, TimeUnit timeUnit, Long initialDelay, Long period) {
        this.task = task;
        this.timeUnit = timeUnit;
        this.initialDelay = initialDelay;
        this.period = period;
    }

    @SneakyThrows
    @Override
    public void run() {
        if (this instanceof ScheduleCustomTask) {
            Thread.sleep(timeUnit.toMillis(initialDelay));
            task.run();
        }
        if (this instanceof ScheduleFixedRateCustomTask) {
            Thread.sleep(timeUnit.toMillis(initialDelay));
            do {
                task.run();
                Thread.sleep(timeUnit.toMillis(period));
            } while (true);
        }
        if (this instanceof ScheduleAtFixDelayCustomTask) {
            Thread.sleep(timeUnit.toMillis(initialDelay));
            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
            do {
                Future result = executor.submit(task);
                result.get();
                Thread.sleep(timeUnit.toMillis(period));
            } while (true);
        }
    }
}
