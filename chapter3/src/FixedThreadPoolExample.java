import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService fixedPool = Executors.newFixedThreadPool(5);
        fixedPool.execute(() -> {
            System.out.println("Task running in thread: " +
                    Thread.currentThread().getName());
        });

        fixedPool.shutdown();
    }
}