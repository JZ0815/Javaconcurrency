package CountDownLatch;

import java.util.concurrent.*;


public class LockDoorExample {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5); // Set the count to 5 employees

        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " leaves the office");
                latch.countDown(); // Signal that this employee has left
            }, String.valueOf(i)).start();
        }

        latch.await(); // Wait for all employees to leave
        System.out.println(Thread.currentThread().getName() + " locks the door");
    }
}
