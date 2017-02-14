import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class sort3Test {

    @Test
    public void swapTest() {
        List<Integer> numbers = new ArrayList<Integer>();
        numbers.add(3);
        numbers.add(1);
        numbers.add(2);
        
        sort3.setNumbers(numbers);
        
        List<Integer> expected = new ArrayList<Integer>();
        expected.add(2);
        expected.add(1);
        expected.add(3);
        
        sort3.swap(0, 2);
        
        assertEquals(expected, sort3.getNumbers());
        assertEquals(1, sort3.getSwapCount());
    }
    
    @Test
    public void sortTest() {
        List<Integer> numbers = new ArrayList<Integer>();
        numbers.add(2);
        numbers.add(1);
        numbers.add(3);
        
        sort3.setNumbers(numbers);
        
        List<Integer> expected = new ArrayList<Integer>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        
        sort3.sort(3);
        
        assertEquals(expected, sort3.getNumbers());
    }
    
}
