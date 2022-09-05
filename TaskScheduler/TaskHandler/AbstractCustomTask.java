package TaskScheduler.TaskHandler;

import java.util.concurrent.TimeUnit;

public abstract class AbstractCustomTask implements Runnable {

    @Override
    public abstract void run();
}
