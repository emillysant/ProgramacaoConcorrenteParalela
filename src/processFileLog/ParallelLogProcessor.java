package processFileLog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelLogProcessor {
    private final ExecutorService executor;

    public ParallelLogProcessor(int numberOfThreads) {
        this.executor = Executors.newFixedThreadPool(numberOfThreads);
    }

    public int processLogs(List<String> filePaths, String word) {
        List<Future<Integer>> futures = new ArrayList<>();
        for (String filePath : filePaths) {
            LogProcessor processor = new LogProcessor(filePath, word);
            Future<Integer> future = executor.submit(processor);
            futures.add(future);
        }

        int totalOccurrences = 0;
        for (Future<Integer> future : futures) {
            try {
                totalOccurrences += future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
        return totalOccurrences;
    }
}
