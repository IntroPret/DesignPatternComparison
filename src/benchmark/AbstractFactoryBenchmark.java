package benchmark;

import abstractFactory.model.Button;
import abstractFactory.model.Checkbox;
import abstractFactory.model.DarkButton;
import abstractFactory.model.DarkCheckbox;
import abstractFactory.model.LightButton;
import abstractFactory.model.LightCheckbox;
import abstractFactory.patternApproach.DarkThemeFactory;
import abstractFactory.patternApproach.GUIFactory;
import abstractFactory.patternApproach.LightThemeFactory;

public class AbstractFactoryBenchmark extends BenchmarkBase {

    @Override
    public BenchmarkResult run() {
        System.out.println("============================================================");
        System.out.println("Abstract Factory Test (Stabilized)");
        System.out.println("============================================================");

        long totalNaiveTime = 0, totalPatternTime = 0;
        long totalNaiveMem = 0, totalPatternMem = 0;

        String theme = "Dark";
        GUIFactory factory = new DarkThemeFactory();

        // Warm-up
        for (int i = 0; i < 50_000; i++) {
            new DarkButton();
            new DarkCheckbox();
            factory.createButton();
            factory.createCheckbox();
        }

        for (int run = 1; run <= RUNS; run++) {
            stabilize();

            // ---- Naive ----
            long memBeforeNaive = usedMemory();
            long startNaive = System.nanoTime();
            for (int i = 0; i < ITERATIONS; i++) {
                Button btn;
                Checkbox cbx;
                if (theme.equalsIgnoreCase("Light")) {
                    btn = new LightButton();
                    cbx = new LightCheckbox();
                } else {
                    btn = new DarkButton();
                    cbx = new DarkCheckbox();
                }
            }
            long naiveTime = System.nanoTime() - startNaive;
            long naiveMem = usedMemory() - memBeforeNaive;

            totalNaiveTime += naiveTime;
            totalNaiveMem += naiveMem;

            // ---- Abstract Factory Pattern ----
            stabilize();
            long memBeforePattern = usedMemory();
            long startPattern = System.nanoTime();
            for (int i = 0; i < ITERATIONS; i++) {
                Button btn = factory.createButton();
                Checkbox cbx = factory.createCheckbox();
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

        return new BenchmarkResult("AbstractFactory (Naive vs Pattern)", avgTimeDiff, avgMemDiff);
    }
}