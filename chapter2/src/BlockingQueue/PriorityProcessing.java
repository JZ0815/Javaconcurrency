package BlockingQueue;

import java.util.Comparator;
import java.util.concurrent.*;

public class PriorityProcessing {
    public static void main(String[] args) throws InterruptedException {
        //BlockingQueue<String> queue = new PriorityBlockingQueue<>();
        BlockingQueue<String> queue = new PriorityBlockingQueue<>(5, Comparator.reverseOrder());
        queue.put("Orange");
        queue.put("Apple");
        queue.put("Banana");
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
    }
}
