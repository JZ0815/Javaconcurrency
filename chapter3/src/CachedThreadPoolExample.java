import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolExample {
    public static void main(String[] args) {
        // Create a cached thread pool
        ExecutorService cachedPool = Executors.newCachedThreadPool();

        // Submit 6 short-lived tasks
        for (int i = 1; i <= 6; i++) {
            final int taskId = i;
            cachedPool.execute(() -> {
                System.out.println("Task " + taskId + " running in: " +
                        Thread.currentThread().getName());
                try {
                    // Simulate a short task
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // Gracefully shut down the executor
        cachedPool.shutdown();
    }
}
