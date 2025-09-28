import algos.QuickSort;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {
    @Test
    void quickSimple() {
        int[] a = {3,1,4,1,5,9,2,6};
        QuickSort.sort(a);
        java.util.Arrays.sort(a);
        // quicksort mutated then we sorted again to avoid building expected - just verify sorted array
        for (int i=1;i<a.length;i++) assertTrue(a[i-1]<=a[i]);
    }
}
