package benchmark;

public abstract class BenchmarkBase {
    protected static final int ITERATIONS = 2000; // DIUBAH DARI 200_000
    protected static final int RUNS = 5;

    protected long usedMemory() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }

    protected double ms(long nanos) {
        return nanos / 1_000_000.0;
    }

    protected double kb(long bytes) {
        return bytes / 1024.0;
    }

    protected void stabilize() {
        System.gc();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {}
    }

    public abstract BenchmarkResult run();
}