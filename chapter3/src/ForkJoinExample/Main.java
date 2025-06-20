package ForkJoinExample;

import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        ForkJoinPool pool = ForkJoinPool.commonPool();  // Use shared pool
        SumTask task = new SumTask(1, 100);
        System.out.println(pool.invoke(task));  // Output: 5050
    }
}
