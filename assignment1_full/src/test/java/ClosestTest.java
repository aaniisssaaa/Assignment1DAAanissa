import algos.ClosestPair;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClosestTest {
    @Test
    void closestSmall() {
        ClosestPair.Point[] pts = { new ClosestPair.Point(0,0), new ClosestPair.Point(1,0), new ClosestPair.Point(0,2) };
        double d = ClosestPair.closest(pts);
        assertTrue(d>0 && d<1.5);
    }
}
