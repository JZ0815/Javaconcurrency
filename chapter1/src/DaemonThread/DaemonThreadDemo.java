package DaemonThread;

import java.util.concurrent.TimeUnit;

public class DaemonThreadDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " :: Daemon? " +
                    (Thread.currentThread().isDaemon() ? "Yes (daemon thread)" : "No (user thread)"));
            while (true) {
                // Simulate background work
                System.out.println("printed form t1 thread");
            }
        }, "t1");

        t1.setDaemon(true); // Set as daemon
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(3); // Give daemon thread time to run
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main thread ends.");
    }
}
