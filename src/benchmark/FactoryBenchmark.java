package benchmark;

import factory.PatternApproach.ConcreteNotificationFactory;
import factory.PatternApproach.NotificationFactory;
import factory.model.EmailNotification;
import factory.model.Notification;
import factory.model.PushNotification;
import factory.model.SMSNotification;

public class FactoryBenchmark extends BenchmarkBase {

    @Override
    public BenchmarkResult run() {
        NotificationFactory factory = new ConcreteNotificationFactory();
        String type = "EMAIL";

        System.out.println("============================================================");
        System.out.println("Factory Method Test (Stabilized)");
        System.out.println("============================================================");

        long totalNaiveTime = 0, totalPatternTime = 0;
        long totalNaiveMem = 0, totalPatternMem = 0;

        // Warm-up
        for (int i = 0; i < 50_000; i++) {
            factory.createNotification(type);
            new EmailNotification();
        }

        for (int run = 1; run <= RUNS; run++) {
            stabilize();

            // ---- Naive ----
            long memBeforeNaive = usedMemory();
            long startNaive = System.nanoTime();
            for (int i = 0; i < ITERATIONS; i++) {
                Notification n;
                if (type.equalsIgnoreCase("EMAIL"))
                    n = new EmailNotification();
                else if (type.equalsIgnoreCase("SMS"))
                    n = new SMSNotification();
                else
                    n = new PushNotification();
            }
            long naiveTime = System.nanoTime() - startNaive;
            long naiveMem = usedMemory() - memBeforeNaive;

            totalNaiveTime += naiveTime;
            totalNaiveMem += naiveMem;

            // ---- Factory Pattern ----
            stabilize();
            long memBeforePattern = usedMemory();
            long startPattern = System.nanoTime();
            for (int i = 0; i < ITERATIONS; i++) {
                factory.createNotification(type);
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

        return new BenchmarkResult("FactoryMethod (Naive vs Pattern)", avgTimeDiff, avgMemDiff);
    }
}
