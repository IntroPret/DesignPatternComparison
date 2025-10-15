package benchmark;

import builder.model.ServerConfig;
import builder.naiveApproach.ServerConfigTelescoping;

public class BuilderBenchmark extends BenchmarkBase {

    @Override
    public BenchmarkResult run() {
        System.out.println("============================================================");
        System.out.println("Builder Test (Stabilized)");
        System.out.println("============================================================");

        long totalNaiveTime = 0, totalPatternTime = 0;
        long totalNaiveMem = 0, totalPatternMem = 0;

        // Warm-up
        for (int i = 0; i < 50_000; i++) {
            new ServerConfigTelescoping(8080, "localhost", 1000, 50);
            new ServerConfig.Builder(8080, "localhost").timeout(1000).maxConnections(50).build();
        }

        for (int run = 1; run <= RUNS; run++) {
            stabilize();

            // ---- Naive ----
            long memBeforeNaive = usedMemory();
            long startNaive = System.nanoTime();
            for (int i = 0; i < ITERATIONS; i++) {
                new ServerConfigTelescoping(8080, "localhost", 1000, 50);
            }
            long naiveTime = System.nanoTime() - startNaive;
            long naiveMem = usedMemory() - memBeforeNaive;

            totalNaiveTime += naiveTime;
            totalNaiveMem += naiveMem;

            // ---- Builder Pattern ----
            stabilize();
            long memBeforePattern = usedMemory();
            long startPattern = System.nanoTime();
            for (int i = 0; i < ITERATIONS; i++) {
                new ServerConfig.Builder(8080, "localhost").timeout(1000).maxConnections(50).build();
            }
            long patternTime = System.nanoTime() - startPattern;
            long patternMem = usedMemory() - memBeforePattern;

            totalPatternTime += patternTime;
            totalPatternMem += patternMem;

            System.out.printf("Run #%d -> Naive: %10.2f ms | Pattern: %10.2f ms%n",
                    run, ms(naiveTime), ms(patternTime));
        }

        double avgTimeDiff = ms((totalNaiveTime - totalPatternTime) / RUNS);
        double avgMemDiff = kb((totalNaiveMem - totalPatternMem) / RUNS);

        System.out.println("============================================================\n");

        return new BenchmarkResult("Builder (Naive vs Pattern)", avgTimeDiff, avgMemDiff);
    }
}