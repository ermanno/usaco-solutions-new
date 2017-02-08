import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class frac1Test {

    @Test
    public void fractionsSortTest() {
        List<Fraction> input = new ArrayList<Fraction>();
        input.add(new Fraction(1, 1));
        input.add(new Fraction(1, 5));
        input.add(new Fraction(1, 4));
        input.add(new Fraction(0, 1));
        input.add(new Fraction(1, 3));
        input.add(new Fraction(2, 5));
        input.add(new Fraction(1, 2));
        input.add(new Fraction(3, 5));
        input.add(new Fraction(4, 5));
        input.add(new Fraction(2, 3));
        input.add(new Fraction(3, 4));
        
        List<Fraction> expectedOutput = new ArrayList<Fraction>();
        expectedOutput.add(new Fraction(0, 1));
        expectedOutput.add(new Fraction(1, 5));
        expectedOutput.add(new Fraction(1, 4));
        expectedOutput.add(new Fraction(1, 3));
        expectedOutput.add(new Fraction(2, 5));
        expectedOutput.add(new Fraction(1, 2));
        expectedOutput.add(new Fraction(3, 5));
        expectedOutput.add(new Fraction(2, 3));
        expectedOutput.add(new Fraction(3, 4));
        expectedOutput.add(new Fraction(4, 5));
        expectedOutput.add(new Fraction(1, 1));
        
        Collections.sort(input);
        
        assertEquals(expectedOutput, input);
    }
    
}
