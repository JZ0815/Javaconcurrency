package CyclicBarrier;

import java.util.concurrent.*;

public class TradingBookBarrierReusable {

    public static void main(String[] args) {
        final int REFRESH_CYCLES = 3;

        CyclicBarrier barrier = new CyclicBarrier(2, () ->
                System.out.println("All data loaded. Trading book is refreshed.\n"));

        Runnable marketDataTask = () -> runTask("Market Data", barrier, REFRESH_CYCLES);
        Runnable positionDataTask = () -> runTask("Position Data", barrier, REFRESH_CYCLES);

        new Thread(marketDataTask).start();
        new Thread(positionDataTask).start();
    }

    static void runTask(String name, CyclicBarrier barrier, int cycles) {
        for (int i = 1; i <= cycles; i++) {
            try {
                System.out.println(name + " loaded for cycle " + i);
                barrier.await();  // Wait for the other thread
            } catch (InterruptedException | BrokenBarrierException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}

