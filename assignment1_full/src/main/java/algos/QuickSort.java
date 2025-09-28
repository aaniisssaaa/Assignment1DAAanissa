package algos;

import java.util.Random;
import util.Metrics;

public class QuickSort {
    private static final Random RNG = new Random();

    public static void sort(int[] a) {
        if (a == null || a.length < 2) return;
        Metrics.reset();
        sort(a, 0, a.length-1, 1);
    }

    private static void sort(int[] a, int lo, int hi, int depth) {
        Metrics.trackDepth(depth);
        while (lo < hi) {
            if (hi - lo < 16) { insertionSort(a, lo, hi); return; }
            int p = partitionRandom(a, lo, hi);
            int leftSize = p - lo;
            int rightSize = hi - p;
            // recurse on smaller side
            if (leftSize < rightSize) {
                sort(a, lo, p-1, depth+1);
                lo = p+1; // loop on larger side
            } else {
                sort(a, p+1, hi, depth+1);
                hi = p-1;
            }
        }
    }

    private static int partitionRandom(int[] a, int lo, int hi) {
        int pivotIndex = lo + RNG.nextInt(hi-lo+1);
        swap(a, pivotIndex, hi);
        int pivot = a[hi];
        int i = lo;
        for (int j = lo; j < hi; j++) {
            if (a[j] <= pivot) {
                swap(a, i++, j);
            }
        }
        swap(a, i, hi);
        return i;
    }

    private static void insertionSort(int[] a, int lo, int hi) {
        for (int i = lo+1; i<=hi; i++) {
            int key = a[i], j=i-1;
            while (j>=lo && a[j] > key) { a[j+1]=a[j]; j--; }
            a[j+1]=key;
        }
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i]; a[i]=a[j]; a[j]=t;
    }
}
