import algos.DeterministicSelect;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SelectTest {
    @Test
    void selectSimple() {
        int[] a = {7,2,1,8,6,3,5,4};
        int k = 3;
        int val = DeterministicSelect.select(a.clone(), k);
        java.util.Arrays.sort(a);
        assertEquals(a[k], val);
    }
}
