import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomThreadPoolExample {

    public static void main(String[] args) {
        ThreadPoolExecutor customPool = new ThreadPoolExecutor(
                2,                              // Core threads
                5,                              // Max threads
                10, TimeUnit.SECONDS,           // Thread keep-alive
                new ArrayBlockingQueue<>(100),  // Bounded queue
                new NamedThreadFactory("app-worker"), // Custom thread naming
                new MetricsTrackingPolicy()      // Custom rejection with metrics
        ) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                // Custom task completion handling
            }
        };

        // Example usage
        customPool.execute(() -> System.out.println(Thread.currentThread().getName() + ": Task running"));

        customPool.shutdown();
    }

    // Custom thread factory example
    static class NamedThreadFactory implements ThreadFactory {
        private final String namePrefix;
        private final AtomicInteger counter = new AtomicInteger(1);

        NamedThreadFactory(String prefix) {
            this.namePrefix = prefix;
        }

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, namePrefix + "-" + counter.getAndIncrement());
        }
    }

    // Placeholder for custom rejection policy
    static class MetricsTrackingPolicy implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.err.println("Task rejected: " + r);
            // Add custom metrics tracking here
        }
    }
}
