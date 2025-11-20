package iscteiul.ista.battleship;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GalleonTest {

    @Test
    void testSize() {
        Galleon g = new Galleon(Compass.NORTH, new Position(5,5));
        assertEquals(5, g.getSize());
    }

    @Test
    void testNorthPositions() {
        Galleon g = new Galleon(Compass.NORTH, new Position(5,5));
        assertTrue(g.getPositions().contains(new Position(5,5)));
        assertTrue(g.getPositions().contains(new Position(5,6)));
        assertTrue(g.getPositions().contains(new Position(5,7)));
        assertTrue(g.getPositions().contains(new Position(6,6)));
        assertTrue(g.getPositions().contains(new Position(7,6)));
    }

    @Test
    void testSouthPositions() {
        Galleon g = new Galleon(Compass.SOUTH, new Position(5,5));
        assertTrue(g.getPositions().contains(new Position(5,5)));
        assertTrue(g.getPositions().contains(new Position(6,5)));
        assertTrue(g.getPositions().contains(new Position(7,4)));
        assertTrue(g.getPositions().contains(new Position(7,5)));
        assertTrue(g.getPositions().contains(new Position(7,6)));
    }

    @Test
    void testEastPositions() {
        Galleon g = new Galleon(Compass.EAST, new Position(5,5));
        assertTrue(g.getPositions().contains(new Position(5,5)));
        assertTrue(g.getPositions().contains(new Position(6,3)));
        assertTrue(g.getPositions().contains(new Position(6,4)));
        assertTrue(g.getPositions().contains(new Position(6,5)));
        assertTrue(g.getPositions().contains(new Position(7,5)));
    }

    @Test
    void testWestPositions() {
        Galleon g = new Galleon(Compass.WEST, new Position(5,5));
        assertTrue(g.getPositions().contains(new Position(5,5)));
        assertTrue(g.getPositions().contains(new Position(6,5)));
        assertTrue(g.getPositions().contains(new Position(6,6)));
        assertTrue(g.getPositions().contains(new Position(6,7)));
        assertTrue(g.getPositions().contains(new Position(7,5)));
    }

    @Test
    void testSink() {
        Galleon g = new Galleon(Compass.NORTH, new Position(5,5));

        for (IPosition pos : g.getPositions())
            g.shoot(pos);

        assertFalse(g.stillFloating());
    }

    @Test
    void testInvalidBearingThrowsException() {
        assertThrows(AssertionError.class, () -> {
            new Galleon(null, new Position(5,5));
        });
    }
}
