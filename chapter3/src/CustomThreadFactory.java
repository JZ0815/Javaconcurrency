import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.*;


public class CustomThreadFactory implements ThreadFactory {
    private final AtomicInteger count = new AtomicInteger(1);
    private final String namePrefix;

    public CustomThreadFactory(String prefix) {
        this.namePrefix = prefix;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, namePrefix + "-thread-" + count.getAndIncrement());
        t.setDaemon(false);
        t.setPriority(Thread.NORM_PRIORITY);
        return t;
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2, new CustomThreadFactory("worker"));
        executor.submit(() -> System.out.println("Running in: " + Thread.currentThread().getName()));
        executor.shutdown();
    }
}