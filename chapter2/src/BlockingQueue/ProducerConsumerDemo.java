package BlockingQueue;

import java.util.concurrent.*;

public class ProducerConsumerDemo {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

        // Producer thread
        new Thread(() -> {
            try {
                for (int i = 1; i < 6; i++) {
                    queue.put(i); // Blocks if queue is full
                    System.out.println("Produced: " + i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        // Consumer thread
        new Thread(() -> {
            try {
                for (int i = 1; i < 6; i++) {
                    Integer value = queue.take(); // Blocks if queue is empty
                    System.out.println("Consumed: " + value);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
