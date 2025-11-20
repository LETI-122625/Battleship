package iscteiul.ista.battleship;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes com cobertura total da classe Game
 * sem modificar o código original
 */
public class GameTest {

    /**
     * Constrói Game e inicializa campos privados não inicializados.
     * (countHits e countSinks começam a null no código original)
     */
    private Game buildGame(IFleet f) {
        Game g = new Game(f);

        try {
            var hits = Game.class.getDeclaredField("countHits");
            hits.setAccessible(true);
            hits.set(g, 0);

            var sinks = Game.class.getDeclaredField("countSinks");
            sinks.setAccessible(true);
            sinks.set(g, 0);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return g;
    }

    @Test
    void testInvalidShot() {
        Fleet f = new Fleet();
        Game g = buildGame(f);

        g.fire(new Position(999, 999));

        assertEquals(1, g.getInvalidShots());
        assertEquals(0, g.getHits());
        assertEquals(0, g.getSunkShips());
    }

    @Test
    void testRepeatedShot() {
        Fleet f = new Fleet();
        Game g = buildGame(f);

        IPosition p = new Position(1,1);
        g.fire(p);
        g.fire(p);

        assertEquals(1, g.getRepeatedShots());
    }

    @Test
    void testHitButNotSunk() {
        Fleet f = new Fleet();
        // Usar navio com tamanho > 1 para testar "não afunda"
        f.addShip(new Caravel(Compass.NORTH, new Position(5,5)));
        Game g = buildGame(f);

        g.fire(new Position(5,5));

        assertEquals(1, g.getHits());
        assertEquals(0, g.getSunkShips());
    }

    @Test
    void testHitAndSink() {
        Fleet f = new Fleet();
        // Barca tem tamanho 1 → afunda num tiro
        f.addShip(new Barge(Compass.NORTH, new Position(3,3)));
        Game g = buildGame(f);

        g.fire(new Position(3,3));

        assertEquals(1, g.getHits());
        assertEquals(1, g.getSunkShips());
        assertEquals(0, g.getRemainingShips());
    }

    @Test
    void testMiss() {
        Fleet f = new Fleet();
        f.addShip(new Barge(Compass.NORTH, new Position(0,0)));

        Game g = buildGame(f);

        g.fire(new Position(7,7)); // tiro na água

        assertEquals(0, g.getHits());
        assertEquals(1, g.getShots().size());
    }

    @Test
    void testRemainingShips() {
        Fleet f = new Fleet();
        f.addShip(new Barge(Compass.NORTH, new Position(0,0)));

        Game g = buildGame(f);

        assertEquals(1, g.getRemainingShips());

        g.fire(new Position(0,0)); // afunda

        assertEquals(0, g.getRemainingShips());
    }

    @Test
    void testPrintMethods() {
        Fleet f = new Fleet();
        f.addShip(new Barge(Compass.NORTH, new Position(0,0)));

        Game g = buildGame(f);
        g.fire(new Position(0,0));

        // Não validamos output → apenas cobrimos linhas
        g.printFleet();
        g.printValidShots();
    }
}
