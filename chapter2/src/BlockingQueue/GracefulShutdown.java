package BlockingQueue;

import java.util.concurrent.*;

public class GracefulShutdown {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        queue.add("Task1");
        queue.add("Task2");

// Poison pill pattern
        queue.add("STOP");

        while (true) {
            try {
                String task = queue.take();
                if (task.equals("STOP")) {
                    System.out.println("Stopped after processing tasks");
                    break;
                }
                System.out.println("Processing: " + task);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
