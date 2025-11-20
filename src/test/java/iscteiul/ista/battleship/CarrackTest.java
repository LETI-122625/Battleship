package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes específicos para o barco Carrack")
class CarrackTest {

    @Test
    @DisplayName("Carrack deve ter tamanho 3")
    void testSize() {
        Carrack c = new Carrack(Compass.WEST, new Position(1, 1));
        assertEquals(3, c.getSize());
    }

    @Test
    @DisplayName("Construção vertical (NORTH)")
    void testNorthConstruction() {
        Carrack c = new Carrack(Compass.NORTH, new Position(5, 5));
        assertEquals(new Position(5, 5), c.getPositions().get(0));
        assertEquals(new Position(6, 5), c.getPositions().get(1));
        assertEquals(new Position(7, 5), c.getPositions().get(2));
    }

    @Test
    @DisplayName("Construção horizontal (EAST)")
    void testEastConstruction() {
        Carrack c = new Carrack(Compass.EAST, new Position(3, 3));
        assertEquals(new Position(3, 3), c.getPositions().get(0));
        assertEquals(new Position(3, 4), c.getPositions().get(1));
        assertEquals(new Position(3, 5), c.getPositions().get(2));
    }

    @Test
    @DisplayName("Construção com bearing inválido deve lançar erro")
    void testInvalidBearing() {
        // O código real lança AssertionError (por causa do assert no construtor Ship)
        assertThrows(AssertionError.class, () ->
                new Carrack(null, new Position(1, 1))
        );
    }

    @Test
    @DisplayName("shoot deve marcar apenas a posição correta")
    void testShoot() {
        Carrack c = new Carrack(Compass.EAST, new Position(3, 3));
        IPosition p = c.getPositions().get(1);

        assertFalse(p.isHit());
        c.shoot(p);
        assertTrue(p.isHit());

        // garantir que só marcou essa posição
        assertFalse(c.getPositions().get(0).isHit());
        assertFalse(c.getPositions().get(2).isHit());
    }
}
