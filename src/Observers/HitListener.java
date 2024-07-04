//209852706 Maya Diamant
package Observers;

import Collidable.Block;
import Movement.Ball;

/**
 * HitListener is an interface for objects that want to be notified when a block is hit by a ball.
 * @author Maya Diamant usamaya@gmail.com
 * @version 1
 * @since 2024-07-04
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the block that was hit.
     * @param hitter the ball that hit the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
