package BlockingQueue;

import java.util.concurrent.*;

public class TimeoutHandling {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(1);

        // First offer succeeds
        boolean success1 = queue.offer("First", 3, TimeUnit.SECONDS);
        System.out.println(success1); // true

        // Second offer waits for 3 second then fails
        boolean success2 = queue.offer("Second", 3, TimeUnit.SECONDS);
        System.out.println(success2); // false
    }
}
