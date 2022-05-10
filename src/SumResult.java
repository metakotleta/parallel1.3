public class SumResult {
    long time;
    int value;

    public SumResult(long time, int value) {
        this.time = time;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Time: " + time + "ms, result: " + value;
    }
}
