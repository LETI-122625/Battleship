package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para Caravel (tamanho 2)")
class CaravelTest {

    @Test
    @DisplayName("Caravel deve ter tamanho 2")
    void testSize() {
        Ship s = new Caravel(Compass.EAST, new Position(1,1));
        assertEquals(2, s.getSize());
    }

    @Test
    @DisplayName("Caravel só afunda após 2 tiros")
    void testSinking() {
        Ship s = new Caravel(Compass.EAST, new Position(1,1));

        s.shoot(new Position(1,1));
        assertTrue(s.stillFloating(), "Não deve afundar após 1 tiro");

        s.shoot(new Position(1,2));
        assertFalse(s.stillFloating(), "Deve afundar após 2 tiros");
    }
}
