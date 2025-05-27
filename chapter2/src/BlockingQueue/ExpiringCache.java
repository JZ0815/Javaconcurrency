package BlockingQueue;

import java.util.concurrent.*;


public class ExpiringCache {
    private DelayQueue<CacheItem> queue = new DelayQueue<>();

    public void put(String key, Object value, long ttlMs) {
        queue.put(new CacheItem(key, value, ttlMs));
    }

    public void startCleanup() {
        new Thread(() -> {
            while (true) {
                try {
                    CacheItem expired = queue.take();  // Blocks until an item expires
                    System.out.println("Expired: " + expired.key);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }).start();
    }

    public static void main(String[] args) throws InterruptedException {
        ExpiringCache cache = new ExpiringCache();
        cache.startCleanup();

        cache.put("key1", "value1", 2000); // expire after 2 seconds
        cache.put("key2", "value2", 4000); // expire after 4 seconds

        System.out.println("Items inserted into cache.");

        Thread.sleep(5000);
        System.out.println("Main thread finished.");
    }
}
