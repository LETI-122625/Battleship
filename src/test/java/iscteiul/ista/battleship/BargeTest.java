package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para Barge (tamanho 1)")
class BargeTest {

    @Test
    @DisplayName("Barge deve ter tamanho 1")
    void testSize() {
        Ship s = new Barge(Compass.NORTH, new Position(2,3));
        assertEquals(1, s.getSize());
    }

    @Test
    @DisplayName("Barge deve afundar após 1 tiro")
    void testSinkOnSingleShot() {
        Ship s = new Barge(Compass.NORTH, new Position(2,3));

        s.shoot(new Position(2,3));

        assertFalse(s.stillFloating(), "Barca deve afundar após ser atingida");
    }
}
