package iscteiul.ista.battleship;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes globais de cobertura")
class CoverageTests {

    @Nested
    @DisplayName("Testes de criação de barcos (factory)")
    class FactoryTests {

        @Test
        @DisplayName("Factory cria Caravela corretamente")
        void testFactoryCaravel() {
            Ship s = Ship.buildShip("caravela", Compass.NORTH, new Position(0,0));
            assertEquals("caravela", s.getCategory().toLowerCase());
        }

        @Test
        @DisplayName("Factory cria Barca corretamente")
        void testFactoryBarge() {
            Ship s = Ship.buildShip("barca", Compass.EAST, new Position(1,1));
            assertEquals("barca", s.getCategory().toLowerCase());
        }
    }

    @Nested
    @DisplayName("Testes de limites do tabuleiro")
    class BoardTests {

        @Test
        @DisplayName("Barco fora do tabuleiro não é adicionado à frota")
        void testOutOfBounds() {
            Fleet fleet = new Fleet();
            IShip ship = new Caravel(Compass.EAST, new Position(20,20));
            assertFalse(fleet.addShip(ship), "Barcos fora dos limites não devem ser adicionados");
        }

        @Test
        @DisplayName("Barco dentro do tabuleiro é adicionado")
        void testInBounds() {
            Fleet fleet = new Fleet();
            IShip ship = new Barge(Compass.NORTH, new Position(5,5));
            assertTrue(fleet.addShip(ship));
        }
    }

    @Nested
    @DisplayName("Testes de colisão entre barcos")
    class CollisionTests {

        @Test
        @DisplayName("Barcos diagonalmente adjacentes não podem ser adicionados")
        void testDiagonalCollision() {
            Fleet f = new Fleet();
            assertTrue(f.addShip(new Barge(Compass.NORTH, new Position(5,5))));
            assertFalse(f.addShip(new Barge(Compass.NORTH, new Position(6,6))));
        }

        @Test
        @DisplayName("Barcos afastados podem ser adicionados")
        void testNonCollision() {
            Fleet f = new Fleet();
            assertTrue(f.addShip(new Barge(Compass.NORTH, new Position(0,0))));
            assertTrue(f.addShip(new Barge(Compass.NORTH, new Position(8,8))));
        }
    }
}
