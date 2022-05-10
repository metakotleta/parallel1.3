import java.util.concurrent.Callable;

public class DirectSum implements Callable<SumResult> {

    int[] array;

    public DirectSum (int[] array) {
        this.array = array;
    }

    @Override
    public SumResult call() throws Exception {
        long startTime = System.currentTimeMillis();
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            result += array[i];
        }

        return new SumResult(System.currentTimeMillis() - startTime, result);
    }
}
