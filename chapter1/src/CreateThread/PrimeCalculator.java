package CreateThread;
import java.util.concurrent.*;
import java.util.ArrayList;
import java.util.List;

public class PrimeCalculator implements Callable<List<Integer>> {
    private final int start;
    private final int end;

    public PrimeCalculator(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public List<Integer> call() throws Exception {
        List<Integer> primes = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }
        return primes;
    }

    private boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Create tasks
        Callable<List<Integer>> task1 = new PrimeCalculator(1, 1000);
        Callable<List<Integer>> task2 = new PrimeCalculator(1001, 2000);
        Callable<List<Integer>> task3 = new PrimeCalculator(2001, 3000);

        // Submit tasks and get Futures
        Future<List<Integer>> future1 = executor.submit(task1);
        Future<List<Integer>> future2 = executor.submit(task2);
        Future<List<Integer>> future3 = executor.submit(task3);

        // Get results (this blocks until the computation is complete)
        List<Integer> primes1 = future1.get();
        List<Integer> primes2 = future2.get();
        List<Integer> primes3 = future3.get();

        // Combine results
        List<Integer> allPrimes = new ArrayList<>();
        allPrimes.addAll(primes1);
        allPrimes.addAll(primes2);
        allPrimes.addAll(primes3);

        System.out.println("Total primes found: " + allPrimes.size());

        executor.shutdown();
    }
}
