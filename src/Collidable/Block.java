//209852706 Maya Diamant
package Collidable;

import Geometry.Rectangle;
import Observers.HitListener;
import Observers.HitNotifier;
import biuoop.DrawSurface;
import Geometry.Point;
import Sprites.Sprite;
import Movement.Velocity;
import Game.Game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import Movement.Ball;

/**
 * The Collidable.Block class represents a block in the game, which is a collidable and drawable sprite.
 *
 * @author Maya Diamant usamaya@gmail.com
 * @version 1
 * @since 2024-07-04
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle area;
    private final Color color;
    private final List<HitListener> hitListeners;


    /**
     * Constructs a Collidable.Block with the specified upper-left corner, width, height, and color.
     *
     * @param leftUp The upper-left corner of the block.
     * @param width  The width of the block.
     * @param height The height of the block.
     * @param color  The color of the block.
     */
    public Block(Point leftUp, double width, double height, Color color) {
        this.area = new Rectangle(leftUp, width, height);
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Constructs a Collidable.Block with the specified area and color.
     *
     * @param area  The rectangular area of the block.
     * @param color The color of the block.
     */
    public Block(Rectangle area, Color color) {
        this.area = area;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.area;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint != null) {
            // Check for collision with the left or right sides of the block
            if (area.getLeft().pointOnLine(collisionPoint) || area.getRight().pointOnLine(collisionPoint)) {
                currentVelocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
            }
            // Check for collision with the top or bottom sides of the block
            if (area.getTop().pointOnLine(collisionPoint) || area.getDown().pointOnLine(collisionPoint)) {
                currentVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            }

        }
        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }
        return currentVelocity;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle((int) area.getUpperLeft().getX(), (int) area.getUpperLeft().getY(),
                (int) area.getWidth(), (int) area.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) area.getUpperLeft().getX(), (int) area.getUpperLeft().getY(),
                (int) area.getWidth(), (int) area.getHeight());
    }

    @Override
    public void timePassed() {
    }

    @Override
    public void addToGame(Game game) {
        game.addCollidable(this);
        game.addSprite(this);
    }

    @Override
    public String myName() {
        return "Collidable.Block";
    }

    /**
     * Checks if the color of the given ball matches the color of this object.
     *
     * @param ball the ball whose color is to be checked.
     * @return true if the color of the ball matches the color of this object, false otherwise.
     */
    public boolean ballColorMatch(Ball ball) {
        return (ball.getColor() == this.color);
    }

    /**
     * Removes this object from the specified game.
     *
     * @param game the game from which this object is to be removed.
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Notifies all registered hit listeners about a hit event.
     *
     * @param hitter the ball that caused the hit event.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * Returns the color of this block.
     *
     * @return the color of this block.
     */
    public Color getColor() {
        return this.color;
    }
}
