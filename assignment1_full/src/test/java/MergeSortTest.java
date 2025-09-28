import algos.MergeSort;
import util.Metrics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MergeSortTest {
    @Test
    void simpleSort() {
        int[] a = {5,2,9,1,5,6};
        MergeSort.sort(a);
        assertArrayEquals(new int[]{1,2,5,5,6,9}, a);
    }
}
