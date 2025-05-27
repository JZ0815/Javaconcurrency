package CountDownLatch;

import java.util.concurrent.*;


public class TradingBookSync {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);

        new Thread(() -> {
            System.out.println("Market Data" + "loaded.");
            latch.countDown();
        }).start();

        new Thread(() -> {
            System.out.println("Position Data" + "loaded.");
            latch.countDown();
        }).start();

        latch.await(); // Wait for both components
        System.out.println("Trading book is ready.");
    }

}
