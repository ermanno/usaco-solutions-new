import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class sort3Test {

    @Test
    public void testCalcNumOfSwaps() {
        int[] numbers = { 1, 1, 3, 2, 1, 1, 1, 3, 2, 1, 3, 3, 2, 1, 3, 1, 1, 2, 3, 1 };
        int ones = 10;
        int twos = 4;

        int minSwaps = sort3.minSwaps(numbers, ones, twos);
        assertEquals(6, minSwaps);
    }

}
