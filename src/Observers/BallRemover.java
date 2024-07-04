//209852706 Maya Diamant
package Observers;

import Collidable.Block;
import Game.Game;
import Movement.Ball;
/**
 * BallRemover is a HitListener that removes balls from the game when they are hit.
 * It also keeps track of the remaining number of balls in the game.
 * @author Maya Diamant usamaya@gmail.com
 * @version 1
 * @since 2024-07-04
 */
public class BallRemover implements HitListener {
    private final Counter remainingBalls;
    private final Game game;
    /**
     * Constructs a BallRemover with the specified counter and game.
     *
     * @param counter the counter that tracks the remaining balls.
     * @param game the game from which balls will be removed.
     */
    public BallRemover(Counter counter, Game game) {
        this.remainingBalls = counter;
        this.game = game;

    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainingBalls.decrease(1);
    }
}
