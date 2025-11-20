package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para a classe abstrata Ship (via Carrack)")
class ShipTest {

    private Ship ship;

    @BeforeEach
    void setup() {
        ship = new Carrack(Compass.NORTH, new Position(5, 5));
    }

    @Test
    @DisplayName("Categoria deve ser Nau")
    void testCategory() {
        assertEquals("Nau", ship.getCategory());
    }

    @Test
    @DisplayName("Bearing deve ser corretamente guardado")
    void testBearing() {
        assertEquals(Compass.NORTH, ship.getBearing());
    }

    @Test
    @DisplayName("getPosition deve retornar a posição inicial")
    void testGetPosition() {
        assertEquals(new Position(5, 5), ship.getPosition());
    }

    @Test
    @DisplayName("stillFloating deve ser false após todos os tiros")
    void testStillFloating() {
        assertTrue(ship.stillFloating());

        for (IPosition p : ship.getPositions()) {
            ship.shoot(p);
        }

        assertFalse(ship.stillFloating());
    }

    @Test
    @DisplayName("tooCloseTo(IPosition) deve detetar adjacências")
    void testTooCloseToPosition() {
        assertTrue(ship.tooCloseTo(new Position(4, 5))); // acima
        assertTrue(ship.tooCloseTo(new Position(6, 5))); // abaixo
        assertFalse(ship.tooCloseTo(new Position(20, 20)));
    }

    @Test
    @DisplayName("tooCloseTo(IShip) deve detetar barcos vizinhos")
    void testTooCloseToShip() {
        Ship other = new Carrack(Compass.EAST, new Position(4, 5));
        assertTrue(ship.tooCloseTo(other));
    }

    @Test
    @DisplayName("toString deve conter categoria, bearing e posição")
    void testToString() {
        String s = ship.toString();

        // Deve conter a categoria
        assertTrue(s.contains(ship.getCategory()));

        // Deve conter o bearing (NORTH, EAST, etc.)
        assertTrue(s.contains(ship.getBearing().toString()));

        // Deve conter a posição exatamente como toString() define
        assertTrue(s.contains(ship.getPosition().toString()));
    }
}
