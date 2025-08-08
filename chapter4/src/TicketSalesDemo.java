import java.util.concurrent.locks.ReentrantLock;

public class TicketSalesDemo {
    private int number = 30;
    ReentrantLock lock = new ReentrantLock(); // Defaults to non-fair lock

    public void sale() {
        lock.lock();
        try {
            if(number > 0) {
                System.out.println(Thread.currentThread().getName()+" sold ticket #"+(number--)+", remaining:"+number);
            }
        } finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        TicketSalesDemo ticket = new TicketSalesDemo();

        new Thread(() -> {
            for (int i = 0; i < 35; i++) ticket.sale();
        }, "Seller-A").start();

        new Thread(() -> {
            for (int i = 0; i < 35; i++) ticket.sale();
        }, "Seller-B").start();

        new Thread(() -> {
            for (int i = 0; i < 35; i++) ticket.sale();
        }, "Seller-C").start();
    }
}
