import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class castleTest {

    @Test
    public void hasWallCheckTest() {
        int encodedWalls = 11;
        assertTrue(castle.hasWall(encodedWalls, castle.NORTH));
        assertTrue(castle.hasWall(encodedWalls, castle.WEST));
        assertTrue(castle.hasWall(encodedWalls, castle.SOUTH));
        assertFalse(castle.hasWall(encodedWalls, castle.EAST));
    }
    
}
