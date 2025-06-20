package ForkJoinExample;

import java.util.concurrent.RecursiveTask;

class FibonacciTask extends RecursiveTask<Integer> {
    final int n;

    FibonacciTask(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= 1) return n;  // Base case
        FibonacciTask f1 = new FibonacciTask(n - 1);
        f1.fork();  // Asynchronously compute f(n-1)  
        FibonacciTask f2 = new FibonacciTask(n - 2);
        return f2.compute() + f1.join();  // Compute f(n-2) synchronously + merge  
    }
}