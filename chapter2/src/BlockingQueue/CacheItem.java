package BlockingQueue;

import java.util.concurrent.*;

class CacheItem implements Delayed {
    String key;
    Object value;
    long expiryTime;

    CacheItem(String key, Object value, long ttlMs) {
        this.key = key;
        this.value = value;
        this.expiryTime = System.nanoTime() +
                TimeUnit.NANOSECONDS.convert(ttlMs, TimeUnit.MILLISECONDS);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(expiryTime - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return Long.compare(this.expiryTime, ((CacheItem) o).expiryTime);
    }
}
