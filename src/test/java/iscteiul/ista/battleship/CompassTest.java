package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da enum Compass")
class CompassTest {
//a
    @Test
    @DisplayName("Cada direção deve retornar o char correto via getDirection()")
    void testGetDirection() {
        assertEquals('n', Compass.NORTH.getDirection());
        assertEquals('s', Compass.SOUTH.getDirection());
        assertEquals('e', Compass.EAST.getDirection());
        assertEquals('o', Compass.WEST.getDirection());
        assertEquals('u', Compass.UNKNOWN.getDirection());
    }

    @Test
    @DisplayName("toString deve devolver o mesmo char da direção")
    void testToString() {
        assertEquals("n", Compass.NORTH.toString());
        assertEquals("s", Compass.SOUTH.toString());
        assertEquals("e", Compass.EAST.toString());
        assertEquals("o", Compass.WEST.toString());
        assertEquals("u", Compass.UNKNOWN.toString());
    }

    @Test
    @DisplayName("charToCompass deve mapear corretamente valores válidos")
    void testCharToCompassValid() {
        assertEquals(Compass.NORTH, Compass.charToCompass('n'));
        assertEquals(Compass.SOUTH, Compass.charToCompass('s'));
        assertEquals(Compass.EAST, Compass.charToCompass('e'));
        assertEquals(Compass.WEST, Compass.charToCompass('o'));
    }

    @Test
    @DisplayName("charToCompass deve retornar UNKNOWN para entradas inválidas")
    void testCharToCompassInvalid() {
        assertEquals(Compass.UNKNOWN, Compass.charToCompass('x'));
        assertEquals(Compass.UNKNOWN, Compass.charToCompass(' '));
        assertEquals(Compass.UNKNOWN, Compass.charToCompass('1'));
    }

    @Test
    @DisplayName("Enum deve conter todas as direções esperadas")
    void testEnumValues() {
        Compass[] values = Compass.values();
        assertEquals(5, values.length);
        assertArrayEquals(
                new Compass[]{Compass.NORTH, Compass.SOUTH, Compass.EAST, Compass.WEST, Compass.UNKNOWN},
                values
        );
    }
}
