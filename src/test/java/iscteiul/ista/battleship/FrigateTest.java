package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para Frigate (tamanho 4)")
class FrigateTest {

    @Test
    @DisplayName("Frigate deve ter tamanho 4")
    void testSize() {
        Ship s = new Frigate(Compass.SOUTH, new Position(5,5));
        assertEquals(4, s.getSize());
    }

    @Test
    @DisplayName("Frigate só afunda após 4 tiros")
    void testSinking() {
        Ship s = new Frigate(Compass.SOUTH, new Position(5,5));

        s.shoot(new Position(5,5));
        s.shoot(new Position(6,5));
        s.shoot(new Position(7,5));
        assertTrue(s.stillFloating(), "Ainda deve flutuar após 3 tiros");

        s.shoot(new Position(8,5));
        assertFalse(s.stillFloating(), "Deve afundar após 4 tiros");
    }
}
