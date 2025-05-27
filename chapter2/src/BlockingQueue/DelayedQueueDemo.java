package BlockingQueue;

import java.util.concurrent.*;

public class DelayedQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayedItem> queue = new DelayQueue<>();

// Add elements with different delays
        queue.put(new DelayedItem("A", 2000));  // 2 second delay
        queue.put(new DelayedItem("B", 1000));  // 1 second delay

// Retrieve elements in order of expiration
        while (!queue.isEmpty()) {
            System.out.println(queue.take());  // "B" (1s) will be retrieved before "A" (2s)
        }
    }
}
