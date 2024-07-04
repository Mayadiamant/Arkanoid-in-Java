//209852706 Maya Diamant
package Observers;

import Game.Game;
import Collidable.Block;
import Movement.Ball;

/**
 * BlockRemover is a HitListener that removes blocks from the game when they are hit.
 * It also keeps track of the remaining number of blocks in the game.
 * @author Maya Diamant usamaya@gmail.com
 * @version 1
 * @since 2024-07-04
 */
public class BlockRemover implements HitListener {
    private final Game game;
    private final Counter remainingBlocks;

    /**
     * Constructs a BlockRemover with the specified game and counter.
     * @param game the game from which blocks will be removed.
     * @param remainingBlocks the counter that tracks the remaining blocks.
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    // Blocks that are hit should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(game);
        remainingBlocks.decrease(1);
        beingHit.removeHitListener(this);
        hitter.setColor(beingHit.getColor());
    }
}
