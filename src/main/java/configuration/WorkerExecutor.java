package configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class WorkerExecutor
{
    private ThreadPoolExecutor executor;

    private final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private final int MAX_POOL_SIZE = 10;
    private final int QUEUE_CAPACITY = 60;
    private final int KEEP_ALIVE = 100;

    public WorkerExecutor()
    {
        executor = new ThreadPoolExecutor(CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY));
    }

    public void execute(ReadTask task)
    {
        executor.execute(task);
    }

    public void terminated()
    {
        while (!executor.isTerminated())
        {
        }
    }

    public void shutdown()
    {
        executor.shutdown();
    }
}
