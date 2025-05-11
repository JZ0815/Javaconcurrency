package DaemonThread;

public class UserThreadDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " :: Daemon? " + Thread.currentThread().isDaemon());
            while (true) {
                // Simulates a long-running user thread
                System.out.println("printed from thead t1");
            }
        }, "t1");

        t1.setDaemon(false);  // Marked as a user thread (default)
        t1.start();
        System.out.println(Thread.currentThread().getName() + " ends");
    }
}
