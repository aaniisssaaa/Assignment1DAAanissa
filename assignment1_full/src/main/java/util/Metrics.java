package util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Metrics {
    private static int maxDepth = 0;
    private static long comparisons = 0;
    private static long assignments = 0;

    public static void reset() {
        maxDepth = 0; comparisons = 0; assignments = 0;
    }
    public static void trackDepth(int d) { if (d>maxDepth) maxDepth = d; }
    public static int getMaxDepth() { return maxDepth; }
    public static void incComparisons() { comparisons++; }
    public static void incAssignments(long n) { assignments += n; }
    public static long getComparisons() { return comparisons; }
    public static long getAssignments() { return assignments; }

    public static void writeCsvHeader(String path) {
        try(PrintWriter pw=new PrintWriter(new FileWriter(path, false))) {
            pw.println("algorithm,n,run_id,time_ms,max_depth,comparisons,assignments");
        } catch(IOException e){ throw new RuntimeException(e); }
    }
    public static void appendCsv(String path, String line) {
        try(PrintWriter pw=new PrintWriter(new FileWriter(path, true))) {
            pw.println(line);
        } catch(IOException e){ throw new RuntimeException(e); }
    }
}
