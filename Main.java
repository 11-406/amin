import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        start();

    }

    public static void start(){
        CFile cFile = new CFile();
        cFile.FFile();

        try(Scanner scanner = new Scanner(new File("file.txt"))){
            List<Integer> allNum = new ArrayList<>();
            while (scanner.hasNextInt()) {
                allNum.add(scanner.nextInt());
            }
            int currentIndex = 0;
            for(int i = 0; i < 60; i++){
                int size = cFile.getList()[i];
                int[] num = new int[size];
                for (int j = 0; j < size && currentIndex < allNum.size(); j++) {
                    num[j] = allNum.get(currentIndex);
                    currentIndex++;
                }
                long t1 = System.nanoTime();
                HeapSort.heapSort(num);
                long t2 = System.nanoTime();

                System.out.println("Количество элементов: " + size + " время: " + (t2 - t1) +" число итераций: " +  HeapSort.getIterations());
                HeapSort.zeroIterations();
            }

        }
        catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        }
    }
}