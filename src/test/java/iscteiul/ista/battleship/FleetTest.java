package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para Fleet")
class FleetTest {

    @Test
    @DisplayName("Deve adicionar barcos à frota")
    void testAddShips() {
        Fleet fleet = new Fleet();
        boolean added = fleet.addShip(new Barge(Compass.NORTH, new Position(1,1)));

        assertTrue(added, "Barco deveria ser adicionado");
        assertEquals(1, fleet.getShips().size());
    }

    @Test
    @DisplayName("Não deve permitir barcos sobrepostos ou colados")
    void testNoOverlap() {
        Fleet fleet = new Fleet();

        IShip s1 = new Caravel(Compass.EAST, new Position(1,1));
        IShip s2 = new Barge(Compass.NORTH, new Position(1,1)); // mesma posição

        assertTrue(fleet.addShip(s1));
        assertFalse(fleet.addShip(s2), "Não pode adicionar barco sobreposto");
    }

    @Test
    @DisplayName("Frota só está destruída quando todos os barcos afundam")
    void testFleetDestroyed() {
        Fleet fleet = new Fleet();

        IShip s1 = new Barge(Compass.NORTH, new Position(1,1));
        IShip s2 = new Caravel(Compass.EAST, new Position(3,3));

        fleet.addShip(s1);
        fleet.addShip(s2);

        // Afundar barco 1
        s1.shoot(new Position(1,1));
        assertEquals(1, fleet.getFloatingShips().size());

        // Afundar barco 2
        s2.shoot(new Position(3,3));
        s2.shoot(new Position(3,4));

        assertEquals(0, fleet.getFloatingShips().size(),
                "Depois de todos afundados, a frota deve estar destruída");
    }
}
