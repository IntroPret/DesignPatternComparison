package benchmark;

import prototype.model.GameObject;
import prototype.naiveApproach.NaiveGameObject;

public class PrototypeBenchmark extends BenchmarkBase {

    @Override
    public BenchmarkResult run() {
        System.out.println("============================================================");
        System.out.println("Prototype Test (Stabilized)");
        System.out.println("============================================================");

        long totalNaiveTime = 0, totalPatternTime = 0;
        long totalNaiveMem = 0, totalPatternMem = 0;

        GameObject prototype = new GameObject(); // Prototipe dibuat sekali

        // Warm-up
        for (int i = 0; i < 50_000; i++) {
            new NaiveGameObject();
            prototype.clone();
        }

        for (int run = 1; run <= RUNS; run++) {
            stabilize();

            // ---- Naive ----
            long memBeforeNaive = usedMemory();
            long startNaive = System.nanoTime();
            for (int i = 0; i < ITERATIONS; i++) {
                new NaiveGameObject();
            }
            long naiveTime = System.nanoTime() - startNaive;
            long naiveMem = usedMemory() - memBeforeNaive;

            totalNaiveTime += naiveTime;
            totalNaiveMem += naiveMem;

            // ---- Prototype Pattern ----
            stabilize();
            long memBeforePattern = usedMemory();
            long startPattern = System.nanoTime();
            for (int i = 0; i < ITERATIONS; i++) {
                prototype.clone();
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

        return new BenchmarkResult("Prototype (Naive vs Pattern)", avgTimeDiff, avgMemDiff);
    }
}