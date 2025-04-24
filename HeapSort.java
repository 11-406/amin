public class HeapSort{

    private static int iterations = 0;

    public static int getIterations() {
        return iterations;
    }
    public static void zeroIterations() {
        iterations = 0;
    }


    public static void heapSort(int[] array) {
        int n = array.length;
        iterations = 0;
        buildHeap(array, n);

        for (int i = n - 1; i > 0; i--) {
            iterations++;
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapify(array, i, 0);
        }
    }

    private static void buildHeap(int[] array, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }
    }

    private static void heapify(int[] array, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && array[left] > array[largest]) {
            largest = left;
        }

        if (right < n && array[right] > array[largest]) {
            largest = right;
        }

        if (largest != i) {
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;

            heapify(array, n, largest);
        }
    }
}
