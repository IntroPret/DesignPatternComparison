package main;

import java.util.ArrayList;
import factory.PatternApproach.ConcreteNotificationFactory;
import factory.PatternApproach.NotificationFactory;
import factory.model.EmailNotification;
import factory.model.Notification;
import factory.model.PushNotification;
import factory.model.SMSNotification;

public class Main {

    private static final int ITERATIONS = 1_000_000;

    private static long usedMemory() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }
    
    private static int convertToMiliseconds(long nano) {
		return (int) (nano / 1_000_000);
	}
    
    private static int convertToKilobytes(long bytes) {
		return (int) (bytes / 1_024);
    }

    public static void main(String[] args) {
        ArrayList<BenchmarkResult> results = new ArrayList<>();
        results.add(runFactoryTest());

        System.out.println("=============================== Final Benchmark Results (Per Iterations) ===============================");
        for (BenchmarkResult r : results) {
            System.out.format("%-40s Time: %10d ms | Memory: %10d kilobytes%n",
                    r.name, r.avgTimeNs, r.avgMemoryBytes);
        }
    }

    // ============================================================
    // FACTORY TEST
    // ============================================================
    private static BenchmarkResult runFactoryTest() {
        long startTime, endTime, totalTimeNaive = 0, totalTimePattern = 0;
        long totalMemoryNaive = 0, totalMemoryPattern = 0;
        String type = "EMAIL";
        
        System.out.println("============================================================");
        System.out.println("Factory Test");
        System.out.println("============================================================");
        

        // Naive
        for (int i = 0; i < ITERATIONS; i++) {
            long memBefore = usedMemory();
            startTime = System.nanoTime();

            if (type.equalsIgnoreCase("EMAIL")) {
                Notification n = new EmailNotification();
            } else if (type.equalsIgnoreCase("SMS")) {
                Notification n = new SMSNotification();
            } else {
                Notification n = new PushNotification();
            }

            endTime = System.nanoTime();
            totalTimeNaive += (endTime - startTime);
            totalMemoryNaive += (usedMemory() - memBefore);
        }

        // Pattern
        NotificationFactory factory = new ConcreteNotificationFactory();
        for (int i = 0; i < ITERATIONS; i++) {
            long memBefore = usedMemory();
            startTime = System.nanoTime();
            Notification n = factory.createNotification("EMAIL");
            endTime = System.nanoTime();
            totalTimePattern += (endTime - startTime);
            totalMemoryPattern += (usedMemory() - memBefore);
        }
        
        System.out.println("Naive Approach - Total Time: " + convertToMiliseconds(totalTimeNaive) + " ms, Total Memory: " + convertToKilobytes(totalMemoryNaive) + " kilobytes");
        System.out.println("Factory Pattern Approach - Total Time: " + convertToMiliseconds(totalTimePattern) + " ms, Total Memory: " + convertToKilobytes(totalMemoryPattern) + " kilobytes");
        System.out.println("============================================================\n");
        

        return new BenchmarkResult("FactoryMethod (Naive vs Pattern)",
                (totalTimeNaive - totalTimePattern) / ITERATIONS,
                (totalMemoryNaive - totalMemoryPattern) / ITERATIONS);
    }
}
