/**
 * PerformanceTest class
 *
 * This class is used to measure and display the running time
 * of the main data structure operations in the project.
 *
 * In this project, we test:
 * - MinHeap insertion for emergency patients
 * - MinHeap removal for emergency patients
 * - CircularQueue enqueue for regular patients
 * - CircularQueue dequeue for regular patients
 *
 * The printed timing results can later be copied into Excel
 * or another graphing tool to make performance charts.
 */
public class PerformanceTest {

    /**
     * Number of timed iterations per test
     */
    private static final int ITERATIONS = 5;

    /**
     * Number of warm-up runs before timing
     */
    private static final int WARMUP_RUNS = 2;

    /**
     * main method
     *
     * This runs the performance tests.
     */
    public static void main(String[] args) {

        /**
         * Different input sizes to test
         */
        int[] sizes = {1000, 5000, 10000, 50000, 100000};

        long[] minHeapInsertAverages = new long[sizes.length];
        long[] minHeapRemoveAverages = new long[sizes.length];
        long[] circularQueueEnqueueAverages = new long[sizes.length];
        long[] circularQueueDequeueAverages = new long[sizes.length];

        runWarmup();

        System.out.println("=== PERFORMANCE TEST RESULTS ===");
        System.out.println("Iterations per test: " + ITERATIONS);
        System.out.println();

        System.out.println("=== MinHeap Insert Performance ===");
        for (int i = 0; i < sizes.length; i++) {
            minHeapInsertAverages[i] = testMinHeapInsert(sizes[i]);
        }

        System.out.println("=== MinHeap Remove Performance ===");
        for (int i = 0; i < sizes.length; i++) {
            minHeapRemoveAverages[i] = testMinHeapRemove(sizes[i]);
        }

        System.out.println("=== CircularQueue Enqueue Performance ===");
        for (int i = 0; i < sizes.length; i++) {
            circularQueueEnqueueAverages[i] = testCircularQueueEnqueue(sizes[i]);
        }

        System.out.println("=== CircularQueue Dequeue Performance ===");
        for (int i = 0; i < sizes.length; i++) {
            circularQueueDequeueAverages[i] = testCircularQueueDequeue(sizes[i]);
        }

        System.out.println("=== SUMMARY TABLE ===");
        System.out.println("Structure,Operation,Patients,Average Time (ns)");

        for (int i = 0; i < sizes.length; i++) {
            System.out.println("MinHeap,Insert," + sizes[i] + "," + minHeapInsertAverages[i]);
        }
        for (int i = 0; i < sizes.length; i++) {
            System.out.println("MinHeap,Remove," + sizes[i] + "," + minHeapRemoveAverages[i]);
        }
        for (int i = 0; i < sizes.length; i++) {
            System.out.println("CircularQueue,Enqueue," + sizes[i] + "," + circularQueueEnqueueAverages[i]);
        }
        for (int i = 0; i < sizes.length; i++) {
            System.out.println("CircularQueue,Dequeue," + sizes[i] + "," + circularQueueDequeueAverages[i]);
        }
    }

    /**
     * Runs warm-up tests to reduce JVM/JIT startup fluctuation.
     */
    private static void runWarmup() {
        int[] warmupSizes = {100, 500};

        for (int i = 0; i < WARMUP_RUNS; i++) {
            for (int n : warmupSizes) {
                measureMinHeapInsert(n);
                measureMinHeapRemove(n);
                measureCircularQueueEnqueue(n);
                measureCircularQueueDequeue(n);
            }
        }
    }

    /**
     * Tests MinHeap insertion performance for a given size.
     *
     * @param n number of patients
     * @return average elapsed time in nanoseconds
     */
    private static long testMinHeapInsert(int n) {
        long total = 0;
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;

        for (int i = 1; i <= ITERATIONS; i++) {
            long elapsed = measureMinHeapInsert(n);
            total += elapsed;

            if (elapsed < min) {
                min = elapsed;
            }
            if (elapsed > max) {
                max = elapsed;
            }

            System.out.println("Iteration " + i + " - Inserted " + n
                    + " emergency patients in " + elapsed + " ns");
        }

        long average = total / ITERATIONS;

        System.out.println("Average for " + n + " patients: " + average + " ns");
        System.out.println("Min: " + min + " ns, Max: " + max + " ns");
        System.out.println();

        return average;
    }

    /**
     * Tests MinHeap removal performance for a given size.
     *
     * @param n number of patients
     * @return average elapsed time in nanoseconds
     */
    private static long testMinHeapRemove(int n) {
        long total = 0;
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;

        for (int i = 1; i <= ITERATIONS; i++) {
            long elapsed = measureMinHeapRemove(n);
            total += elapsed;

            if (elapsed < min) {
                min = elapsed;
            }
            if (elapsed > max) {
                max = elapsed;
            }

            System.out.println("Iteration " + i + " - Removed " + n
                    + " emergency patients from MinHeap in " + elapsed + " ns");
        }

        long average = total / ITERATIONS;

        System.out.println("Average for " + n + " patients: " + average + " ns");
        System.out.println("Min: " + min + " ns, Max: " + max + " ns");
        System.out.println();

        return average;
    }

    /**
     * Tests CircularQueue enqueue performance for a given size.
     *
     * @param n number of patients
     * @return average elapsed time in nanoseconds
     */
    private static long testCircularQueueEnqueue(int n) {
        long total = 0;
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;

        for (int i = 1; i <= ITERATIONS; i++) {
            long elapsed = measureCircularQueueEnqueue(n);
            total += elapsed;

            if (elapsed < min) {
                min = elapsed;
            }
            if (elapsed > max) {
                max = elapsed;
            }

            System.out.println("Iteration " + i + " - Enqueued " + n
                    + " regular patients in " + elapsed + " ns");
        }

        long average = total / ITERATIONS;

        System.out.println("Average for " + n + " patients: " + average + " ns");
        System.out.println("Min: " + min + " ns, Max: " + max + " ns");
        System.out.println();

        return average;
    }

    /**
     * Tests CircularQueue dequeue performance for a given size.
     *
     * @param n number of patients
     * @return average elapsed time in nanoseconds
     */
    private static long testCircularQueueDequeue(int n) {
        long total = 0;
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;

        for (int i = 1; i <= ITERATIONS; i++) {
            long elapsed = measureCircularQueueDequeue(n);
            total += elapsed;

            if (elapsed < min) {
                min = elapsed;
            }
            if (elapsed > max) {
                max = elapsed;
            }

            System.out.println("Iteration " + i + " - Dequeued " + n
                    + " regular patients in " + elapsed + " ns");
        }

        long average = total / ITERATIONS;

        System.out.println("Average for " + n + " patients: " + average + " ns");
        System.out.println("Min: " + min + " ns, Max: " + max + " ns");
        System.out.println();

        return average;
    }

    /**
     * Measures MinHeap insertion time for n emergency patients.
     *
     * @param n number of patients
     * @return elapsed time in nanoseconds
     */
    private static long measureMinHeapInsert(int n) {
        MinHeap heap = new MinHeap(n);

        long start = System.nanoTime();

        for (int i = 0; i < n; i++) {
            int severity = (i % 5) + 1;

            Patient p = new Patient(
                    i + 1,
                    "EmergencyPatient" + i,
                    30,
                    severity,
                    "Emergency",
                    "Test Case"
            );

            heap.insert(p);
        }

        long end = System.nanoTime();
        return end - start;
    }

    /**
     * Measures MinHeap removal time for n emergency patients.
     *
     * @param n number of patients
     * @return elapsed time in nanoseconds
     */
    private static long measureMinHeapRemove(int n) {
        MinHeap heap = new MinHeap(n);

        for (int i = 0; i < n; i++) {
            int severity = (i % 5) + 1;

            Patient p = new Patient(
                    i + 1,
                    "EmergencyPatient" + i,
                    30,
                    severity,
                    "Emergency",
                    "Test Case"
            );

            heap.insert(p);
        }

        long start = System.nanoTime();

        for (int i = 0; i < n; i++) {
            removeFromMinHeap(heap);
        }

        long end = System.nanoTime();
        return end - start;
    }

    /**
     * Measures CircularQueue enqueue time for n regular patients.
     *
     * @param n number of patients
     * @return elapsed time in nanoseconds
     */
    private static long measureCircularQueueEnqueue(int n) {
        CircularQueue queue = new CircularQueue(n);

        long start = System.nanoTime();

        for (int i = 0; i < n; i++) {
            Patient p = new Patient(
                    i + 1,
                    "RegularPatient" + i,
                    30,
                    5,
                    "Regular",
                    "Test Case"
            );

            queue.enqueue(p);
        }

        long end = System.nanoTime();
        return end - start;
    }

    /**
     * Measures CircularQueue dequeue time for n regular patients.
     *
     * @param n number of patients
     * @return elapsed time in nanoseconds
     */
    private static long measureCircularQueueDequeue(int n) {
        CircularQueue queue = new CircularQueue(n);

        for (int i = 0; i < n; i++) {
            Patient p = new Patient(
                    i + 1,
                    "RegularPatient" + i,
                    30,
                    5,
                    "Regular",
                    "Test Case"
            );

            queue.enqueue(p);
        }

        long start = System.nanoTime();

        for (int i = 0; i < n; i++) {
            queue.dequeue();
        }

        long end = System.nanoTime();
        return end - start;
    }

    /**
     * Helper method for MinHeap removal.
     *
     * IMPORTANT:
     * Replace the method call inside this function with the actual
     * removal method used by your MinHeap class.
     */
    private static void removeFromMinHeap(MinHeap heap) {
        // Replace ONE of these with your actual MinHeap removal method:

        heap.removeMin();

        // heap.removeMin();
        // heap.extractMin();
        // heap.delete();
    }
}