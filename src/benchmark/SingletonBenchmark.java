package benchmark;

import singleton.naiveApproach.DatabaseConnection;
import singleton.patternApproach.DatabaseConnectionSingleton;

public class SingletonBenchmark extends BenchmarkBase {

    @Override
    public BenchmarkResult run() {
        System.out.println("============================================================");
        System.out.println("Singleton Test (Stabilized)");
        System.out.println("============================================================");

        long totalNaiveTime = 0, totalSingletonTime = 0;
        long totalNaiveMem = 0, totalSingletonMem = 0;

        // Warm-up
        for (int i = 0; i < 50_000; i++) {
            new DatabaseConnection();
            DatabaseConnectionSingleton.getInstance();
        }

        for (int run = 1; run <= RUNS; run++) {
            stabilize();

            // ---- Naive ----
            long memBeforeNaive = usedMemory();
            long startNaive = System.nanoTime();
            for (int i = 0; i < ITERATIONS; i++) {
                new DatabaseConnection();
            }
            long naiveTime = System.nanoTime() - startNaive;
            long naiveMem = usedMemory() - memBeforeNaive;

            totalNaiveTime += naiveTime;
            totalNaiveMem += naiveMem;

            // ---- Singleton ----
            stabilize();
            long memBeforeSingleton = usedMemory();
            long startSingleton = System.nanoTime();
            for (int i = 0; i < ITERATIONS; i++) {
                DatabaseConnectionSingleton.getInstance();
            }
            long singletonTime = System.nanoTime() - startSingleton;
            long singletonMem = usedMemory() - memBeforeSingleton;

            totalSingletonTime += singletonTime;
            totalSingletonMem += singletonMem;

            System.out.printf("Run #%d -> Naive: %10.2f ms | Singleton: %10.2f ms%n",
                    run, ms(naiveTime), ms(singletonTime));
        }

        double avgTimeDiff = ms((totalNaiveTime - totalSingletonTime) / RUNS);
        double avgMemDiff = kb((totalNaiveMem - totalSingletonMem) / RUNS);

        System.out.println("============================================================\n");

        return new BenchmarkResult("Singleton (Naive vs Pattern)", avgTimeDiff, avgMemDiff);
    }
}
