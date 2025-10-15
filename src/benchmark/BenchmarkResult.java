package benchmark;

public class BenchmarkResult {
    public String name;
    public double totalTimeMs;
    public double totalMemoryKb;

    public BenchmarkResult(String name, double totalTimeMs, double totalMemoryKb) {
        this.name = name;
        this.totalTimeMs = totalTimeMs;
        this.totalMemoryKb = totalMemoryKb;
    }
}
