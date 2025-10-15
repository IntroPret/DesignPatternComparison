package benchmark;

import java.util.ArrayList;

public class RunAllBenchmark {
    public static void main(String[] args) {
        ArrayList<BenchmarkResult> results = new ArrayList<>();

        results.add(new FactoryBenchmark().run());
        results.add(new SingletonBenchmark().run());
        results.add(new BuilderBenchmark().run());
        results.add(new PrototypeBenchmark().run());
        results.add(new AbstractFactoryBenchmark().run());

        System.out.println("=============================== FINAL BENCHMARK RESULTS (AVERAGE) ===============================");
        for (BenchmarkResult r : results) {
            System.out.format("%-40s | Avg Time Diff: %10.2f ms | Avg Memory Diff: %10.2f KB%n",
                    r.name, r.totalTimeMs, r.totalMemoryKb);
        }
    }
}
