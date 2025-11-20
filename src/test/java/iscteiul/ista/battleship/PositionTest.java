package iscteiul.ista.battleship;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class PositionTest {
    void testCreatePosition() {
        Position p = new Position(3, 5);
        assertEquals(3, p.getRow());
        assertEquals(5, p.getColumn());
    }

    @Test
    @DisplayName("Duas posições iguais devem ser consideradas iguais")
    void testEqualsSameCoordinates() {
        Position p1 = new Position(2, 4);
        Position p2 = new Position(2, 4);
        assertEquals(p1, p2);
    }

    @Test
    @DisplayName("Posições diferentes não são iguais")
    void testNotEquals() {
        Position p1 = new Position(1, 1);
        Position p2 = new Position(2, 1);
        assertNotEquals(p1, p2);
    }

    @Test
    @DisplayName("equals retorna false se o objeto não for IPosition")
    void testEqualsInvalidType() {
        Position p = new Position(1, 1);
        assertFalse(p.equals("texto"));
    }

    @Test
    @DisplayName("hashCode é consistente com equals")
    void testHashCode() {
        Position p1 = new Position(3, 3);
        Position p2 = new Position(3, 3);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    @DisplayName("Posições adjacentes são corretamente reconhecidas")
    void testIsAdjacent() {
        Position center = new Position(5, 5);
        Position adjacent = new Position(6, 6);
        assertTrue(center.isAdjacentTo(adjacent));
    }

    @Test
    @DisplayName("Posições não adjacentes são corretamente reconhecidas")
    void testIsNotAdjacent() {
        Position center = new Position(5, 5);
        Position far = new Position(7, 8);
        assertFalse(center.isAdjacentTo(far));
    }

    @Test
    @DisplayName("Occupy marca a posição como ocupada")
    void testOccupy() {
        Position p = new Position(1, 1);
        assertFalse(p.isOccupied()); // default
        p.occupy();
        assertTrue(p.isOccupied());
    }

    @Test
    @DisplayName("Shoot marca a posição como atingida")
    void testShoot() {
        Position p = new Position(3, 3);
        assertFalse(p.isHit()); // default1
        p.shoot();
        assertTrue(p.isHit());
    }

    @Test
    @DisplayName("toString deve seguir o formato esperado")
    void testToString() {
        Position p = new Position(3, 4);
        assertEquals("Linha = 3 Coluna = 4", p.toString());
    }
}
