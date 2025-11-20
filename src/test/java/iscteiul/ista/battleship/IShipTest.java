package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para a interface IShip (usando Carrack como implementação)")
class IShipTest {

    private IShip ship;

    @BeforeEach
    void setup() {
        ship = new Carrack(Compass.EAST, new Position(2, 2));
    }

    @Test
    @DisplayName("Categoria deve ser Nau")
    void testCategory() {
        assertEquals("Nau", ship.getCategory());
    }

    @Test
    @DisplayName("Tamanho deve ser 3")
    void testSize() {
        assertEquals(3, ship.getSize());
    }

    @Test
    @DisplayName("As posições devem ser corretamente calculadas para EAST")
    void testPositions() {
        List<IPosition> positions = ship.getPositions();
        assertEquals(3, positions.size());
        assertEquals(new Position(2, 2), positions.get(0));
        assertEquals(new Position(2, 3), positions.get(1));
        assertEquals(new Position(2, 4), positions.get(2));
    }

    @Test
    @DisplayName("Teste do método occupies()")
    void testOccupies() {
        assertTrue(ship.occupies(new Position(2, 2)));
        assertFalse(ship.occupies(new Position(10, 10)));
    }

    @Test
    @DisplayName("Teste do método stillFloating() antes e depois de tiros")
    void testStillFloating() {
        assertTrue(ship.stillFloating());
        ship.shoot(new Position(2, 2));
        ship.shoot(new Position(2, 3));
        ship.shoot(new Position(2, 4));
        assertFalse(ship.stillFloating());
    }

    @Test
    @DisplayName("Teste ao método shoot()")
    void testShoot() {
        IPosition p = ship.getPositions().get(0);
        assertFalse(p.isHit());
        ship.shoot(p);
        assertTrue(p.isHit());
    }

    @Test
    @DisplayName("Teste aos limites (top, bottom, left, right)")
    void testBoundaries() {
        assertEquals(2, ship.getTopMostPos());
        assertEquals(2, ship.getBottomMostPos());
        assertEquals(2, ship.getLeftMostPos());
        assertEquals(4, ship.getRightMostPos());
    }
}
