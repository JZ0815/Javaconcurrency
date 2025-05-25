package Semaphore;

import java.util.Random;
import java.util.concurrent.*;

public class BankCounterExample {
    public static void main(String[] args) {
        Semaphore tableSpot = new Semaphore(3); // 3 table spots
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                try {
                    tableSpot.acquire(); // Request a table spot
                    System.out.println(Thread.currentThread().getName() + " occupies a counter.");
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    System.out.println(Thread.currentThread().getName() + " leaves the counter.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    tableSpot.release(); // Leave the table, free the spot
                }
            }, "Guest " + i).start();
        }
    }
}
