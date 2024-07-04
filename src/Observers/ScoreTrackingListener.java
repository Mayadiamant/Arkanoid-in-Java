//209852706 Maya Diamant
package Observers;

import Collidable.Block;
import Movement.Ball;

/**
 * ScoreTrackingListener is a HitListener that updates the score when a block is hit.
 * @author Maya Diamant usamaya@gmail.com
 * @version 1
 * @since 2024-07-04
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;

    /**
     * Constructs a ScoreTrackingListener with the specified score counter.
     *
     * @param scoreCounter the counter that tracks the current score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * It increases the current score by 5 points.
     *
     * @param beingHit the block that was hit.
     * @param hitter the ball that hit the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}
