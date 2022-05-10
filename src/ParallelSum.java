import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class ParallelSum extends RecursiveTask<SumResult> {
    public static final long START_TIME = System.currentTimeMillis();
    public static final long EMPTY_TIME = 0;
    private int start = 0;
    private int[] array;

    public ParallelSum(int start, int end, int[] array) {
        this.array = Arrays.copyOfRange(array, start, end);
    }

    @Override
    protected SumResult compute() {

        final int diff = array.length - start;
        switch (diff) {
            case 0: return new SumResult(EMPTY_TIME, 0);
            case 1: return new SumResult(EMPTY_TIME, array[start]);
            case 2: return new SumResult(EMPTY_TIME, array[start] + array[start + 1]);
            default:
                return new SumResult(System.currentTimeMillis() - START_TIME, forkTasksAndGetResult());
        }
    }

    private int forkTasksAndGetResult() {
        final int middle = (array.length - start) / 2 + start;

        ParallelSum task1 = new ParallelSum(start, middle, array);
        ParallelSum task2 = new ParallelSum(middle, array.length, array);

        invokeAll(task1, task2);

        return task1.join().value + task2.join().value;
    }
}
