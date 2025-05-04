package CreateThread;

public class TicketThread extends Thread {
    private int tickets = 100;

    @Override
    public void run() {
        while (tickets > 0) {
            System.out.println(Thread.currentThread().getName() +
                    " sells ticket #" + tickets--);
            try {
                // Simulate processing time
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // Create two separate thread instances
        TicketThread thread1 = new TicketThread();
        TicketThread thread2 = new TicketThread();

        // Start the threads
        thread1.start();
        thread2.start();

        // Note: Each thread has its own ticket count (200 tickets total)
    }
}
