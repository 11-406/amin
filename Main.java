import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] numbers = BinomialHeap.generateRandomArray(10000);
        BinomialHeap heap = new BinomialHeap();
        List<Long> insertTimes = new ArrayList<>();
        List<Integer> insertOperations = new ArrayList<>();

        for (int num : numbers) {
            long startTime = System.nanoTime();
            heap.insert(num);
            long endTime = System.nanoTime();
            insertTimes.add(endTime - startTime);
            insertOperations.add((int) (Math.log(heap.size()) / Math.log(2)) + 1);
        }


        Random random = new Random();
        List<Long> searchTimes = new ArrayList<>();
        List<Integer> searchOperations = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            int index = random.nextInt(numbers.length);
            int target = numbers[index];

            long startTime = System.nanoTime();
            Integer found = heap.find(target);
            long endTime = System.nanoTime();

            searchTimes.add(endTime - startTime);
            searchOperations.add((int) (Math.log(heap.size()) / Math.log(2)) + 1);
        }

        List<Long> deleteTimes = new ArrayList<>();
        List<Integer> deleteOperations = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            int index = random.nextInt(numbers.length);
            int target = numbers[index];

            long startTime = System.nanoTime();
            boolean deleted = heap.delete(target);
            long endTime = System.nanoTime();

            if (deleted) {
                deleteTimes.add(endTime - startTime);
                deleteOperations.add((int) (Math.log(heap.size()) / Math.log(2)) + 1);
            }
        }

        double avgInsertTime = insertTimes.stream().mapToLong(Long::longValue).average().orElse(0);
        double avgInsertOps = insertOperations.stream().mapToInt(Integer::intValue).average().orElse(0);

        double avgSearchTime = searchTimes.stream().mapToLong(Long::longValue).average().orElse(0);
        double avgSearchOps = searchOperations.stream().mapToInt(Integer::intValue).average().orElse(0);

        double avgDeleteTime = deleteTimes.stream().mapToLong(Long::longValue).average().orElse(0);
        double avgDeleteOps = deleteOperations.stream().mapToInt(Integer::intValue).average().orElse(0);

        System.out.println("Среднее время вставки (нс): " + avgInsertTime);
        System.out.println("Среднее количество операций вставки: " + avgInsertOps);

        System.out.println("Среднее время поиска (нс): " + avgSearchTime);
        System.out.println("Среднее количество операций поиска: " + avgSearchOps);

        System.out.println("Среднее время удаления (нс): " + avgDeleteTime);
        System.out.println("Среднее количество операций удаления: " + avgDeleteOps);
    }
}