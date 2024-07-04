//209852706 Maya Diamant
package Sprites;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The Sprites.SpriteCollection class manages a collection of sprites.
 * It allows adding sprites, notifying all sprites that time has passed,
 * and drawing all sprites on a given drawing surface.
 *
 * @author Maya Diamant usamaya@gmail.com
 * @version 1
 * @since 2024-07-04
 */
public class SpriteCollection {
    private final List<Sprite> spriteList;

    /**
     * Constructs a Sprites.SpriteCollection with the specified list of sprites.
     *
     * @param spriteList The list of sprites to be managed.
     */
    public SpriteCollection(List<Sprite> spriteList) {
        this.spriteList = spriteList;
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param s The sprite to be added.
     */
    public void addSprite(Sprite s) {
        spriteList.add(s);
    }
    /**
     * Removes the specified sprite from the list of sprites.
     *
     * @param s the sprite to be removed.
     */
    public void removeSprite(Sprite s) {
        spriteList.remove(s);
    }

    /**
     * Calls the timePassed() method on all sprites in the collection.
     * This notifies each sprite that time has passed.
     */
    public void notifyAllTimePassed() {
        List<Sprite> newSprites = new ArrayList<>(spriteList);
        for (Sprite sprite : newSprites) {
            sprite.timePassed();
        }
    }

    /**
     * Calls the drawOn(d) method on all sprites in the collection.
     * This draws each sprite on the given drawing surface.
     *
     * @param d The drawing surface on which to draw the sprites.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : spriteList) {
            sprite.drawOn(d);
        }
    }
}
