//209850706 Maya Diamant
package Sprites;

import Game.Game;
import Observers.Counter;
import biuoop.DrawSurface;

/**
 * ScoreIndicator is a Sprite that displays the current score.
 * @author Maya Diamant usamaya@gmail.com
 * @version 1
 * @since 2024-07-04
 */
public class ScoreIndicator implements Sprite {
    private final Counter score;

    /**
     * Constructs a ScoreIndicator with the specified score counter.
     *
     * @param score the counter that tracks the current score.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        String s = Integer.toString(score.getValue());
        d.drawText(350, 40, "Score: " + s, 15);
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(Game game) {
        game.addSprite(this);
    }
}
