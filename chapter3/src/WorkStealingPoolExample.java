import java.util.concurrent.*;
import java.util.*;
import java.util.stream.*;

public class WorkStealingPoolExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newWorkStealingPool();

        List<Callable<String>> tasks = IntStream.range(1, 6)
                .mapToObj(i -> (Callable<String>) () -> {
                    Thread.sleep(1000);
                    return "Task " + i + " completed by " + Thread.currentThread().getName();
                })
                .collect(Collectors.toList());

        List<Future<String>> results = executor.invokeAll(tasks);

        for (Future<String> result : results) {
            System.out.println(result.get());
        }

        executor.shutdown();
    }
}
