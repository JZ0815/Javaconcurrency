package BlockingQueue;

import java.util.concurrent.*;

public class SynchronousHandoff {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new SynchronousQueue<>();

// Producer
        new Thread(() -> {
            try {
                String data = "Important Message";
                queue.put(data); // Waits for consumer to take
                System.out.println("Message delivered");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

// Consumer
        new Thread(() -> {
            try {
                Thread.sleep(1000); // Simulate processing delay
                String received = queue.take();
                System.out.println("Received: " + received);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
