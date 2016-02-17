package myapplication.bits;

public class SortingAlgorithm {


    public static long BubbleSort(int[] sortArray) {
        long bubbleStart = System.currentTimeMillis();

        int temp = 0, k;
        for (int j = sortArray.length; j >= 0; j--) {
            for (int i = 0; i < sortArray.length - 1; i++) {
                k = i + 1;
                if (sortArray[i] > sortArray[k]) {
                    temp = sortArray[i];
                    sortArray[i] = sortArray[k];
                    sortArray[k] = temp;
                }
            }
        }
        long bubbleEnd = System.currentTimeMillis();
        return (bubbleEnd - bubbleStart);
    }

    public static long selectionAlgo(int[] sortArray) {
        long selectionStart = System.currentTimeMillis();

        for (int i = 0; i < sortArray.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < sortArray.length; j++) {
                if (sortArray[j] < sortArray[index])
                    index = j;
                int smallerNumber = sortArray[index];
                sortArray[index] = sortArray[i];
                sortArray[i] = smallerNumber;
            }

        }
        long selectionEnd = System.currentTimeMillis();
        return (selectionEnd - selectionStart);
    }


    public static long insertionSort(int[] sortArray) {
        long insertionStart = System.currentTimeMillis();
        int temp;
        for (int i = 1; i < sortArray.length; i++) {
            for (int j = i; j > 0; j--) {
                if (sortArray[j] < sortArray[j - 1]) {
                    temp = sortArray[j];
                    sortArray[j] = sortArray[j - 1];
                    sortArray[j - 1] = temp;
                }
            }
        }
        long insertionEnd = System.currentTimeMillis();
        return (insertionEnd - insertionStart);
    }

    public static int[] quickSort(int[] inp, int low, int high) {

        int i = low;
        int j = high;
        int mid = (low + (high - low) / 2);
        int pivot = inp[mid];
        while (i <= j) {
            while (inp[i] < pivot) {
                i++;
            }
            while (inp[j] > pivot) {
                j--;
            }
            if (i <= j) {
                int swap = inp[i];
                inp[i] = inp[j];
                inp[j] = swap;
                i++;
                j--;
            }
        }
        if (low < j) {
            quickSort(inp, low, j);
        }
        if (i < high) {
            quickSort(inp, i, high);
        }
        return inp;
    }

    public static long quickSortTime(int[] inp, int low, int high) {
        long quickStart = System.currentTimeMillis();
        quickSort(inp, low, high);
        long quickEnd = System.currentTimeMillis();
        return (quickEnd - quickStart);
    }

    public static long mergeSort(int[] a) {
        long mergeStart = System.currentTimeMillis();
        int[] from = a, to = new int[a.length];
        for (int blockSize = 1; blockSize < a.length; blockSize *= 2) {
            for (int start = 0; start < a.length; start += 2 * blockSize)
                merge(from, to, start, start + blockSize, start + 2 * blockSize);
            int[] temp = from;
            from = to;
            to = temp;
        }
        if (a != from) {
            for (int k = 0; k < a.length; k++) {
                a[k] = from[k];
            }
        }
        long mergeEnd = System.currentTimeMillis();
        return (mergeEnd - mergeStart);
    }

    private static void merge(int[] from, int[] to, int lo, int mid, int hi) {
        if (mid > from.length) mid = from.length;
        if (hi > from.length) hi = from.length;
        int i = lo, j = mid;
        for (int k = lo; k < hi; k++) {
            if (i == mid) to[k] = from[j++];
            else if (j == hi) to[k] = from[i++];
            else if (from[j] < from[i]) to[k] = from[j++];
            else to[k] = from[i++];
        }
    }
}
