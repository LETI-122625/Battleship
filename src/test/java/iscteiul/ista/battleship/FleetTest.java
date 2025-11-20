package iscteiul.ista.battleship;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Cobertura total da classe Fleet")
class FleetTest {

    @Test
    @DisplayName("Adicionar barco válido retorna true")
    void testAddShipValid() {
        Fleet fleet = new Fleet();
        assertTrue(fleet.addShip(new Barge(Compass.NORTH, new Position(1, 1))));
    }

    @Test
    @DisplayName("Adicionar barco fora do tabuleiro retorna false")
    void testAddShipOutOfBounds() {
        Fleet fleet = new Fleet();
        assertFalse(fleet.addShip(new Caravel(Compass.NORTH, new Position(99, 99))));
    }

    @Test
    @DisplayName("Adicionar barco demasiado próximo retorna false (colisão)")
    void testAddShipCollision() {
        Fleet fleet = new Fleet();
        fleet.addShip(new Barge(Compass.NORTH, new Position(2, 2)));
        assertFalse(fleet.addShip(new Barge(Compass.NORTH, new Position(3, 3))));
    }

    @Test
    @DisplayName("Adicionar barcos até não aceitar mais")
    void testAddShipLimit() {
        Fleet fleet = new Fleet();
        int added = 0;

        for (int i = 0; i < 20; i++) {
            if (fleet.addShip(new Barge(Compass.NORTH, new Position(i, i)))) {
                added++;
            }
        }

        assertTrue(added > 0, "Deve conseguir adicionar pelo menos um barco");
        assertTrue(added <= IFleet.FLEET_SIZE, "Não deve ultrapassar limite da frota");
    }



    @Test
    @DisplayName("shipAt retorna barco correto entre vários barcos")
    void testShipAtMultiple() {
        Fleet f = new Fleet();
        f.addShip(new Barge(Compass.NORTH, new Position(1, 1)));
        f.addShip(new Caravel(Compass.NORTH, new Position(5, 5)));

        assertNotNull(f.shipAt(new Position(1, 1)));
        assertNotNull(f.shipAt(new Position(5, 5)));
        assertNull(f.shipAt(new Position(9, 9)));
    }

    @Test
    @DisplayName("getShipsLike retorna lista filtrada corretamente")
    void testGetShipsLike() {
        Fleet f = new Fleet();
        f.addShip(new Barge(Compass.NORTH, new Position(1, 1)));
        f.addShip(new Caravel(Compass.NORTH, new Position(5, 5)));

        assertEquals(1, f.getShipsLike("Barca").size());
        assertEquals(1, f.getShipsLike("Caravela").size());
        assertEquals(0, f.getShipsLike("Fragata").size());
    }

    @Test
    @DisplayName("getFloatingShips retorna apenas barcos vivos")
    void testFloatingShips() {
        Fleet f = new Fleet();
        IShip s = new Barge(Compass.NORTH, new Position(1, 1));

        f.addShip(s);
        assertEquals(1, f.getFloatingShips().size());

        s.shoot(new Position(1, 1));
        assertEquals(0, f.getFloatingShips().size());
    }

    @Test
    @DisplayName("Chamada aos métodos print cobre rotinas de IO")
    void testPrintMethods() {
        Fleet f = new Fleet();
        f.addShip(new Barge(Compass.NORTH, new Position(1, 1)));

        f.printAllShips();
        f.printFloatingShips();
        f.printShipsByCategory("Barca");
        f.printStatus();
    }
}
