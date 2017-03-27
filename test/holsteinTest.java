import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class holsteinTest {

    @Test
    public void hasLowerCostIsBetter() {
        boolean[] newSolution = {true, false, true};
        int costNewSolution = 2;
        boolean[] oldSolution = {true, true, true};
        int costOldSolution = 3;
        
        boolean result = holstein.isBetter(newSolution, costNewSolution, oldSolution, costOldSolution);
        
        assertTrue(result);
    }
    
    @Test
    public void hasBiggerCostIsWorse() {
        boolean[] newSolution = {true, true, true};
        int costNewSolution = 3;
        boolean[] oldSolution = {true, false, true};
        int costOldSolution = 2;
        
        boolean result = holstein.isBetter(newSolution, costNewSolution, oldSolution, costOldSolution);
        
        assertFalse(result);
    }
    
    @Test
    public void tiedButHasFeedInEarlierPositionIsTrue() {
        boolean[] newSolution = {true, false, true};
        int costNewSolution = 2;
        boolean[] oldSolution = {false, true, true};
        int costOldSolution = 2;
        
        boolean result = holstein.isBetter(newSolution, costNewSolution, oldSolution, costOldSolution);
        
        assertTrue(result);
    }
    
    @Test
    public void tiedButHasFeedInLaterPositionIsFalse() {
        boolean[] newSolution = {false, true, true};
        int costNewSolution = 2;
        boolean[] oldSolution = {true, false, true};
        int costOldSolution = 2;
        
        boolean result = holstein.isBetter(newSolution, costNewSolution, oldSolution, costOldSolution);
        
        assertFalse(result);
    }
}
