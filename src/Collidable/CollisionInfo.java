//209852706 Maya Diamant
package Collidable;
import Geometry.Point;

/**
 * The Collidable.CollisionInfo class holds information about a collision,
 * including the point at which the collision occurs and the collidable
 * object involved in the collision.
 *
 * @author Maya Diamant usamaya@gmail.com
 * @version 1
 * @since 2024-07-04
 */
public class CollisionInfo {
    private final Point collisionPoint;
    private final Collidable collisionObject;

    /**
     * Constructs a Collidable.CollisionInfo object with the specified collision point and collidable object.
     *
     * @param collisionPoint  The point at which the collision occurs.
     * @param collisionObject The collidable object involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Returns the point at which the collision occurs.
     *
     * @return The collision point.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return The collidable object.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
