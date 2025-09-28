package algos;

import util.Metrics;

public class DeterministicSelect {

    public static int select(int[] a, int k) {
        if (a == null || k < 0 || k >= a.length) throw new IllegalArgumentException();
        Metrics.reset();
        return select(a, 0, a.length-1, k, 1);
    }

    private static int select(int[] a, int lo, int hi, int k, int depth) {
        Metrics.trackDepth(depth);
        while (true) {
            if (lo == hi) return a[lo];
            int pivot = medianOfMedians(a, lo, hi);
            int p = partition(a, lo, hi, pivot);
            if (k == p) return a[k];
            else if (k < p) hi = p-1;
            else lo = p+1;
        }
    }

    private static int medianOfMedians(int[] a, int lo, int hi) {
        int n = hi - lo + 1;
        int numMedians = (n + 4) / 5;
        for (int i = 0; i < numMedians; i++) {
            int subLo = lo + i*5;
            int subHi = Math.min(subLo + 4, hi);
            insertionSort(a, subLo, subHi);
            int medianIdx = subLo + (subHi - subLo)/2;
            swap(a, lo + i, medianIdx);
        }
        // recursively select median of medians
        int mid = lo + numMedians/2;
        return select(a, lo, lo + numMedians - 1, mid, 1);
    }

    private static int partition(int[] a, int lo, int hi, int pivotValue) {
        int pivotIndex = lo;
        for (int i = lo; i<=hi; i++) if (a[i] == pivotValue) { pivotIndex = i; break; }
        swap(a, pivotIndex, hi);
        int store = lo;
        for (int i = lo; i<hi; i++) {
            if (a[i] < pivotValue) swap(a, store++, i);
        }
        swap(a, store, hi);
        return store;
    }

    private static void insertionSort(int[] a, int lo, int hi) {
        for (int i = lo+1; i<=hi; i++) {
            int key=a[i], j=i-1;
            while (j>=lo && a[j]>key) { a[j+1]=a[j]; j--; }
            a[j+1]=key;
        }
    }

    private static void swap(int[] a, int i, int j) {
        int t=a[i]; a[i]=a[j]; a[j]=t;
    }
}
