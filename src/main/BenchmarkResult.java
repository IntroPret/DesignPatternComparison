package main;

public class BenchmarkResult {
	String name;
    long avgTimeNs;
    long avgMemoryBytes;

    public BenchmarkResult(String name, long time, long memory) {
        this.name = name;
        this.avgTimeNs = time;
        this.avgMemoryBytes = memory;
    }
}
