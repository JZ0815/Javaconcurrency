package CreateThread;

public class TicketRunnable implements Runnable {
    private int tickets = 100; // Shared by all threads

    @Override
    public void run() {
        while (tickets > 0) {
            System.out.println(Thread.currentThread().getName() +
                    " sells ticket #" + tickets--);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TicketRunnable runnable = new TicketRunnable();

        // Create multiple threads sharing the same runnable
        Thread thread1 = new Thread(runnable, "Window 1");
        Thread thread2 = new Thread(runnable, "Window 2");
        Thread thread3 = new Thread(runnable, "Window 3");

        thread1.start();
        thread2.start();
        thread3.start();

        // Note: All threads share the same ticket count (100 tickets total)
    }
}
