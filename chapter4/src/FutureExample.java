import java.util.concurrent.*;

public class FutureExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(() -> {
            // Time-consuming computation
            return "Task Result";
        });

        // Main thread continues other work...
        try {
            String result = future.get(2, TimeUnit.SECONDS); // Wait max 2 seconds for result
            System.out.println("Got result: " + result);
        } catch (TimeoutException e) {
            future.cancel(true); // Cancel the task if it times out
            System.out.println("Task timed out and was cancelled.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
