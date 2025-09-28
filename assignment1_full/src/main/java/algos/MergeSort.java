package algos;

import util.Metrics;

public class MergeSort {
    private static final int CUTOFF = 32;
    private static int[] tmp;

    public static void sort(int[] a) {
        if (a == null || a.length < 2) return;
        tmp = new int[a.length];
        Metrics.reset();
        sort(a, 0, a.length - 1, 1);
    }

    private static void sort(int[] a, int lo, int hi, int depth) {
        Metrics.trackDepth(depth);
        if (hi - lo + 1 <= CUTOFF) {
            insertionSort(a, lo, hi);
            return;
        }
        int mid = (lo + hi) >>> 1;
        sort(a, lo, mid, depth+1);
        sort(a, mid+1, hi, depth+1);
        merge(a, lo, mid, hi);
    }

    private static void merge(int[] a, int lo, int mid, int hi) {
        System.arraycopy(a, lo, tmp, lo, hi - lo + 1);
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = tmp[j++];
            else if (j > hi) a[k] = tmp[i++];
            else if (tmp[j] < tmp[i]) a[k] = tmp[j++];
            else a[k] = tmp[i++];
        }
    }

    private static void insertionSort(int[] a, int lo, int hi) {
        for (int i = lo+1; i<=hi; i++) {
            int key = a[i];
            int j = i-1;
            while (j>=lo && a[j] > key) {
                a[j+1] = a[j];
                j--;
            }
            a[j+1] = key;
        }
    }
}
