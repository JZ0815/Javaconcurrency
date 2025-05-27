package BlockingQueue;

import java.util.concurrent.*;

class DelayedItem implements Delayed {
    private String data;
    private long expiryTime;

    DelayedItem(String data, long delayMs) {
        this.data = data;
        this.expiryTime = System.nanoTime() +
                TimeUnit.NANOSECONDS.convert(delayMs, TimeUnit.MILLISECONDS);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(expiryTime - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed other) {
        return Long.compare(expiryTime, ((DelayedItem) other).expiryTime);
    }

    @Override
    public String toString() {
        return data;
    }
}