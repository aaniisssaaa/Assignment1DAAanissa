package cli;

import algos.*;
import util.Metrics;
import java.util.Random;

public class Runner {
    public static void main(String[] args) {
        // Simple arg parsing: --algo <name> --n <size> --runs <r> --out <file>
        String algo = "mergesort"; int n=10000; int runs=3; String out="metrics.csv";
        for (int i=0;i<args.length;i++) {
            switch(args[i]) {
                case "--algo": algo = args[++i]; break;
                case "--n": n = Integer.parseInt(args[++i]); break;
                case "--runs": runs = Integer.parseInt(args[++i]); break;
                case "--out": out = args[++i]; break;
            }
        }
        Metrics.writeCsvHeader(out);
        Random rnd = new Random(12345);
        for (int r=0;r<runs;r++) {
            int[] a = new int[n];
            for (int i=0;i<n;i++) a[i]=rnd.nextInt();
            long t0 = System.currentTimeMillis();
            Metrics.reset();
            switch(algo.toLowerCase()) {
                case "mergesort": algos.MergeSort.sort(a); break;
                case "quicksort": algos.QuickSort.sort(a); break;
                default: System.err.println("Unknown algo: "+algo); return;
            }
            long dt = System.currentTimeMillis()-t0;
            Metrics.appendCsv(out, String.format("%s,%d,%d,%d,%d,%d,%d", algo, n, r, dt, Metrics.getMaxDepth(), Metrics.getComparisons(), Metrics.getAssignments()));
        }
        System.out.println("Done. Wrote: " + out);
    }
}
