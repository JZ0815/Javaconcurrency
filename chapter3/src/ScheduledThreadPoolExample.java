import java.util.concurrent.*;

public class ScheduledThreadPoolExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        Runnable task = () -> System.out.println("Running at: " + System.currentTimeMillis());

        // Schedule the task to run every 2 seconds with an initial delay of 1 second
        scheduler.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);
    }
}
