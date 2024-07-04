//209852706 Maya Diamant
package Sprites;

import biuoop.DrawSurface;
import Game.Game;

/**
 * The gui.Sprite.gui.Sprite interface represents objects that can be drawn to the screen
 * and can be notified when time has passed.
 * @author Maya Diamant usamaya@gmail.com
 * @version 1
 * @since 2024-07-04
 */
public interface Sprite {
    /**
     * Draws the sprite to the given drawing surface.
     *
     * @param d The drawing surface on which to draw the sprite.
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that time has passed, allowing it to update its state.
     */
    void timePassed();

    /**
     * Adds this block to the game as both a collidable and a sprite.
     *
     * @param game The game to which this block will be added.
     */
    void addToGame(Game game);
}
