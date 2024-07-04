//209852706 Maya Diamant
package Collidable;

import Geometry.Point;
import Geometry.Rectangle;
import Movement.Ball;
import Movement.Velocity;

/**
 * The gui.Collidable.gui.Collidable interface represents objects that can be collided with in the game.
 *
 * @author Maya Diamant usamaya@gmail.com
 * @version 1
 * @since 2024-07-04
 */
public interface Collidable {

    /**
     * Returns the "collision shape" of the object, which is a rectangle.
     *
     * @return The rectangle representing the collision shape of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that it has been collided with at the specified collision point,
     * with the given velocity. The method returns the new velocity expected after the hit,
     * based on the force the object inflicted.
     *
     * @param collisionPoint  The point at which the collision occurred.
     * @param currentVelocity The current velocity of the object before the collision.
     * @param hitter The ball that hit the collidable
     * @return The new velocity expected after the collision.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
    /**
     * Returns the name of this collidable object.
     *
     * @return The name of the collidable object.
     */
    String myName();
}
