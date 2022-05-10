import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static final int DIMENSION = 3500000;
    public static final int MAX_VALUE = 1000000;
    public static final int START_INDEX = 0;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] array = generateArray(DIMENSION, MAX_VALUE);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<SumResult> directFuture = executor.submit(new DirectSum(array));

        System.out.println("Parallel: " + new ParallelSum(START_INDEX, array.length, array).compute());
        System.out.println("Direct: " + directFuture.get());
        executor.shutdown();
    }

    public static int[] generateArray(int dimension, int maxValue) {
        final Random random = new Random();
        int[] intArray = new int[dimension];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = random.nextInt(maxValue);
        }
        return intArray;
    }
}
