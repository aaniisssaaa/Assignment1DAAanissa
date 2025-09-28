package algos;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    public static class Point { public double x,y; public Point(double x,double y){this.x=x;this.y=y;} }

    public static double closest(Point[] pts) {
        if (pts == null || pts.length < 2) return Double.POSITIVE_INFINITY;
        Point[] byX = pts.clone();
        Arrays.sort(byX, Comparator.comparingDouble(p->p.x));
        Point[] aux = byX.clone();
        return rec(byX, aux, 0, byX.length-1);
    }

    private static double rec(Point[] byX, Point[] aux, int lo, int hi) {
        if (hi - lo <= 3) {
            double best = Double.POSITIVE_INFINITY;
            for (int i=lo;i<=hi;i++) for (int j=i+1;j<=hi;j++) best = Math.min(best, dist(byX[i], byX[j]));
            Arrays.sort(byX, lo, hi+1, Comparator.comparingDouble(p->p.y));
            return best;
        }
        int mid = (lo+hi)>>>1;
        double midx = byX[mid].x;
        double d1 = rec(byX, aux, lo, mid);
        double d2 = rec(byX, aux, mid+1, hi);
        double d = Math.min(d1,d2);
        // merge by y into aux
        int i=lo, j=mid+1, k=lo;
        while (i<=mid || j<=hi) {
            if (j>hi || (i<=mid && byX[i].y <= byX[j].y)) aux[k++] = byX[i++];
            else aux[k++] = byX[j++];
        }
        System.arraycopy(aux, lo, byX, lo, hi-lo+1);
        // build strip
        Point[] strip = new Point[hi-lo+1];
        int m=0;
        for (int t=lo;t<=hi;t++) if (Math.abs(byX[t].x - midx) < d) strip[m++] = byX[t];
        for (int p=0;p<m;p++) {
            for (int q=p+1; q<m && (strip[q].y - strip[p].y) < d && q-p<=8; q++) {
                d = Math.min(d, dist(strip[p], strip[q]));
            }
        }
        return d;
    }

    private static double dist(Point a, Point b) {
        double dx = a.x-b.x, dy = a.y-b.y;
        return Math.hypot(dx, dy);
    }
}
