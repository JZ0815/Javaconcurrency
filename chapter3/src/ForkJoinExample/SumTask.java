package ForkJoinExample;

import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

class SumTask extends RecursiveTask<Integer> {
    private final int start, end;
    private static final int THRESHOLD = 10;  // Base case threshold  

    SumTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - start <= THRESHOLD) {
            // Sequential calculation for small ranges  
            return IntStream.rangeClosed(start, end).sum();
        } else {
            int mid = (start + end) / 2;
            SumTask left = new SumTask(start, mid);
            SumTask right = new SumTask(mid + 1, end);
            left.fork();  // Parallelize left half  
            return right.compute() + left.join();  // Compute right half synchronously  
        }
    }
} 